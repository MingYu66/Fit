package com.healthyDiet.web.dietPlan;

import com.healthyDiet.framework.web.domain.server.Sys;
import com.healthyDiet.system.domain.BhFood;
import com.healthyDiet.web.plan.NutritionTracker;
import com.healthyDiet.web.util.DietException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static com.healthyDiet.web.util.DietException.ErrorCode.NUTRIENT_IMBALANCE;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-12 15:22
 */
@Slf4j
public class DietRecommender {
    private static List<BhFood> allFoods;

    private static  int MAX_SINGLE_WEIGHT = 150; // 单次最大添加量
    private static  int MIN_WEIGHT = 50;         // 最小添加量
    /**
     * 每餐最大推荐次数
     */
    private static final int MAX_ATTEMPTS = 100;

    private static  int MAX_RETRY = 0;
    /**
     * 算法入口
     * @param 热量值 dailyCalory
     * @return
     * @throws DietException
     */
    public static Map<MealTime, MealRecommendation> generatePlan(double dailyCalory,List<BhFood> foods) throws DietException {
        allFoods = foods;
        //首先根据比例计算出 每餐应摄入的 蛋白质、热量、碳水、脂肪是多少
        Map<MealTime, MealTarget> targets = NutritionTarget.calculateTarget(dailyCalory);
        DietPlan plan = new DietPlan();
//        开始计算三餐的推荐  MealTime是三餐的包装类
        for (MealTime mealTime : MealTime.values()) {
            MAX_RETRY = 0;
            //target 包含当前餐次的参数  蛋白质、热量、碳水、脂肪
            MealTarget target = targets.get(mealTime);
            target.setType(mealTime.getType());
            log.info("开始推荐{}，推荐热量：{}，推荐脂肪：{}，推荐蛋白质{}，推荐碳水：{}",mealTime.getDisplayName(),target.getCalory(),target.getFat(),target.getCarb(),target.getProtein());
            MealRecommendation recommendation = generateMealRecommendation(target);

            plan.addRecommendation(mealTime, recommendation);
        }

        return plan.getRecommendations();
    }

    private static MealRecommendation generateMealRecommendation(MealTarget target) throws DietException {
        //选择的食物
        List<FoodItem> selected = new ArrayList<>();
        //营养进度
        NutritionProgress progress = new NutritionProgress(target);
        // 记录已选食物类型避免重复
        Map<Long, Integer> selectedTypeCounts = new HashMap<>();
        // 记录已选食物ID避免重复
        Set<Long> selectedFoodIds = new HashSet<>();
        //当前餐次热量
        double remainingCalory = target.getCalory();
        //

        // 根据食物健康等级排序 1-4 （1最健康）
        List<BhFood> candidates = allFoods.stream()
                .sorted(Comparator.comparingLong(BhFood::getHealthLight))
                .collect(Collectors.toList());

        int attempts = 0;
        Random random = new Random();

        while (!progress.isSatisfied() && attempts++ < MAX_ATTEMPTS) {
            List<ScoredFood> scoredFoods = new ArrayList<>();

            // 1. 计算所有候选食物的得分
            for (BhFood food : candidates) {
                calculatMinWeight(food);
                if (selectedFoodIds.contains(food.getPid())) continue;

                // 类型检查（假设每个食物有getType()方法）
                Long type = food.getPid();
                int typeCount = selectedTypeCounts.getOrDefault(type, 0);

                // 类型限制：同类型不超过2种
                if (typeCount >= 2) continue;

                // 计算最大克数（带营养约束）
                // 计算最大允许克数
                double[] limits = calculateMaxGrams(food, progress);
                //找出最小的克重，默认0
                double maxGrams = Arrays.stream(limits).min().orElse(0);
                //对比计算的是否大于规定的最小克重(20g),不能超过最大（150g）克重
                maxGrams = Math.min(Math.max(maxGrams, MIN_WEIGHT), MAX_SINGLE_WEIGHT);
                //如果克重小于最小克重，
                if (maxGrams < MIN_WEIGHT) continue;

                // 2. 动态评分（带历史衰减）
                double score = calculateFoodScore(food, progress, typeCount,target,selectedTypeCounts);

                // 3. 加入概率扰动（增加随机性）
                double noise = 1 + (random.nextDouble() - 0.5) * 0.2; // ±10%扰动
                scoredFoods.add(new ScoredFood(food, score * noise, maxGrams));
            }

            // 4. 轮盘赌选择（而非选最高分）
            if (!scoredFoods.isEmpty()) {
                ScoredFood selectedFood = rouletteWheelSelect(scoredFoods);
                FoodItem item = new FoodItem(selectedFood.food, selectedFood.grams);
                if(target.getType()==2 && item.getFood().getPid()==10){

                }else{
                    selected.add(item);
                    progress.consume(item);
                    selectedFoodIds.add(selectedFood.food.getPid());
                    selectedTypeCounts.merge(selectedFood.food.getPid(), 1, Integer::sum);
                }

            } else {
                // 放宽类型限制
                selectedTypeCounts.replaceAll((k, v) -> 0);
            }
        }
        // 5. 最终优化调整
        selected = adjustPortions(selected, target);



        selected.stream().forEach(foodItem -> System.out.println(foodItem.getFood().getName()+ " -- " + foodItem.getGrams() + "g" + " ， 热量：" + foodItem.getTotalCalory() + " 脂肪：" + foodItem.getTotalFat()) );
        // 最终营养校验
        if (!isNutritionBalanced(selected, target)) {

            if(MAX_RETRY > 10){
                throw new DietException(NUTRIENT_IMBALANCE);
            }else {
                MAX_RETRY ++;
                generateMealRecommendation(target);
            }

        }

        return new MealRecommendation(selected);
    }

    /**
     * 主食类	1
     * 肉蛋类	2
     * 大豆及制品	3
     * 蔬菜菌藻类	4
     * 水果类	5
     * 奶类	6
     * 油脂类	7
     * 坚果类	8
     * 调味品	9
     * 饮料类	10
     * 零食及冷饮	11
     * 其它	12
     * @param food
     */
    private static void calculatMinWeight(BhFood food){
        if(food.getPid() == 1){
            MIN_WEIGHT = 100;
            MAX_SINGLE_WEIGHT = 200;
        }else if(food.getPid() == 2){
            MIN_WEIGHT = 30;
            MAX_SINGLE_WEIGHT = 100;
        }else if(food.getPid() == 3){
            MIN_WEIGHT = 20;MAX_SINGLE_WEIGHT = 50;
        }else if(food.getPid() == 4){
            MIN_WEIGHT = 200;MAX_SINGLE_WEIGHT = 250;
        }else if(food.getPid() == 5){
            MIN_WEIGHT = 100;MAX_SINGLE_WEIGHT = 150;
        }else if(food.getPid() == 6){
            MIN_WEIGHT = 200;MAX_SINGLE_WEIGHT = 250;
        }else if(food.getPid() == 7){
            MIN_WEIGHT = 5;MAX_SINGLE_WEIGHT = 10;
        }else if(food.getPid() == 8){
            MIN_WEIGHT = 10;MAX_SINGLE_WEIGHT = 30;
        }else if(food.getPid() == 9){
            MIN_WEIGHT = 5;MAX_SINGLE_WEIGHT = 10;
        }else if(food.getPid() == 10){
            MIN_WEIGHT = 100;MAX_SINGLE_WEIGHT = 200;
        }else if(food.getPid() == 11){
            MIN_WEIGHT = 20;MAX_SINGLE_WEIGHT = 50;
        }else if(food.getPid() == 12){
            MIN_WEIGHT = 50;MAX_SINGLE_WEIGHT = 100;
        }else if(food.getPid() == 13){
            MIN_WEIGHT = 50;MAX_SINGLE_WEIGHT = 100;
        }

    }
    // 新的评分计算方法
    private static double calculateFoodScore(BhFood food, NutritionProgress progress, int typeCount, MealTarget target, Map<Long, Integer> selectedTypeCounts) {
        // 1. 营养需求权重
        double proteinWeight = progress.getProtein() / target.getProtein();
        double carbWeight = progress.getCarb() / target.getCarb();
        double fatWeight = progress.getFat() / target.getFat();

        // 2. 营养密度分
        double nutritionScore =
                new Double(food.getProtein()) * proteinWeight * 0.5
                        + new Double(food.getCarbohydrate()) * carbWeight * 0.3
                        + new Double(food.getFat()) * fatWeight * 0.1
                        + (5 - food.getHealthLight()) * 0.1; // 健康等级越高分越高

        // 3. 多样性控制
        double diversityPenalty = typeCount * 0.2; // 同类越多分越低
//        double freshnessBonus = 1.0 / (1 + mealHistory.getRecentUseCount(food.getPid())); // 历史衰减

        double typeLimitFactor = calculateTypeLimitFactor2(selectedTypeCounts, food.getPid());

        return (nutritionScore - diversityPenalty) * typeLimitFactor;
    }


    // 辅助方法：轮盘赌选择
    private static ScoredFood rouletteWheelSelect(List<ScoredFood> candidates) {
        double total = candidates.stream().mapToDouble(s -> s.score).sum();
        double randomValue = Math.random() * total;
        double accumulate = 0;

        for (ScoredFood sf : candidates) {
            accumulate += sf.score;
            if (accumulate >= randomValue) {
                return sf;
            }
        }
        return candidates.get(0);
    }

    // 克数调整方法
    private static List<FoodItem> adjustPortions(List<FoodItem> items, MealTarget target) {
        NutritionTracker tracker = new NutritionTracker(target);
        items.forEach(tracker::consume);

        // 按营养缺口优化
        while (!tracker.isSatisfied()) {
            double needProtein = tracker.getRemainingProtein();
            double needCarb = tracker.getRemainingCarb();
            double needFat = tracker.getRemainingFat();

            for (FoodItem item : items) {
                BhFood food = item.getFood();
                // 按需增加克数
                double delta = Math.min(
                        10, // 每次最多增加10克
                        Math.max(
                                needProtein / new Double(food.getProtein()) * 100,
                                Math.max(
                                        needCarb / new Double(food.getCarbohydrate()) * 100,
                                        needFat / new Double(food.getFat()) * 100
                                )
                        )
                );

                item.setGrams(item.getGrams() + delta);




                tracker.consume(item);

                if (tracker.isSatisfied()) break;
            }
        }
        return items;
    }

    // 多维克数计算
    private static double[] calculateMaxGrams(BhFood food, NutritionProgress progress) {
        return new double[]{
                (progress.calory / new Double(food.getCalory())) * 100,
                (progress.protein / new Double(food.getProtein())) * 100,
                (progress.carb / new Double( food.getCarbohydrate())) * 100,
                (progress.fat / new Double(food.getFat())) * 100
        };
    }



    /**
     * 类型限制因子
     * selectedTypes 选中的食物类型列表
     * currentType  当前食物类型
     */
    private static double calculateTypeLimitFactor2(Map<Long, Integer> selectedTypeCounts, Long type) {
        try{
            Integer num = selectedTypeCounts.get(type);
            //如果当前食物类型出现过，则将类型限制分数权重降为 0.5，默认为1
            return num > 1 ? 0.5 : 1.0;
        }catch (Exception e){
            return 1.0;
        }

    }


    /**
     * 营养校验（差值计算）
     * @param items 当前餐次推荐的食物列表
     * @param target 按照饮食摄入推荐三餐中每餐应该摄入的营养数据
     *               计算当前餐次推荐的食物的营养参数总量和既定营养指标做对比
     * @return
     */
    private static boolean isNutritionBalanced(List<FoodItem> items, MealTarget target) {
        //热量差值
        final double CALORY_TOLERANCE = 0.4;  // 10%
        //营养差值
        final double NUTRIENT_TOLERANCE = 0.3; // 20%

        //热量总数
        double totalCalory = sumCalory(items);
        //总碳水
        double totalCarb = sumCarb(items);
        //总蛋白
        double totalProtein = sumProtein(items);
        //总脂肪
        double totalFat = sumFat(items);
        log.info("---热量总数{}，总碳水{}总蛋白{}总脂肪{}",totalCalory,totalCarb,totalProtein,totalFat);
        log.info("推荐热量总数{}，总碳水{}总蛋白{}总脂肪{}",target.getCalory(),target.getCarb(),target.getProtein(),target.getFat());

        // （总热量 - 推荐热量） = 热量差值
        // 如果热量差值 小于 推荐的热量的百分之十，则符合标准。
        // 比如 早餐既定推荐的热量是 100千卡，但是当前算法推荐的食物热量总数为 95，则热量差值为5，小于既定热量的10%，则符合标准
        // 如果 早餐既定推荐的热量是 100千卡，但是当前算法推荐的食物热量总数为 85，则热量差值为15，大于既定热量的10%，则不符合标准
        System.out.println(Math.abs(totalCalory - target.getCalory()) < target.getCalory() * CALORY_TOLERANCE);
//        System.out.println(Math.abs(totalCarb - target.getCarb()) < target.getCarb() * NUTRIENT_TOLERANCE);
//        System.out.println(Math.abs(totalProtein - target.getProtein()) < target.getProtein() * NUTRIENT_TOLERANCE);
//        System.out.println(Math.abs(totalFat - target.getFat()) < target.getFat() * NUTRIENT_TOLERANCE);
        return Math.abs(totalCalory - target.getCalory()) < target.getCalory() * CALORY_TOLERANCE;
//        return Math.abs(totalCalory - target.getCalory()) < target.getCalory() * CALORY_TOLERANCE
//                && Math.abs(totalCarb - target.getCarb()) < target.getCarb() * NUTRIENT_TOLERANCE
//                && Math.abs(totalProtein - target.getProtein()) < target.getProtein() * NUTRIENT_TOLERANCE
//                && Math.abs(totalFat - target.getFat()) < target.getFat() * NUTRIENT_TOLERANCE;
    }

    private static double sumCalory(List<FoodItem> foodItems){
       return foodItems.stream().mapToDouble(FoodItem::getTotalCalory).sum();
    }
    private static double sumCarb(List<FoodItem> foodItems){
        return foodItems.stream().mapToDouble(FoodItem::getTotalCarb).sum();
    }
    private static double sumProtein(List<FoodItem> foodItems){
        return foodItems.stream().mapToDouble(FoodItem::getTotalProtein).sum();
    }
    private static double sumFat(List<FoodItem> foodItems){
        return foodItems.stream().mapToDouble(FoodItem::getTotalFat).sum();
    }
}