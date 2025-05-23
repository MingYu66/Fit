package com.healthyDiet.web.plan;
import com.healthyDiet.system.domain.BhCategories;
import com.healthyDiet.system.domain.BhFood;
import com.healthyDiet.web.dietPlan.FoodItem;
import com.healthyDiet.web.dietPlan.MealRecommendation;
import com.healthyDiet.web.dietPlan.MealTarget;
import com.healthyDiet.web.util.DietException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 17:35
 */

//public class AdvancedDietRecommender {
//    private List<BhFood> allFoods;
//    private UserPreference userPreference;
//    private MealHistory mealHistory;
//
//    // 配置参数
//    private static final int MAX_ATTEMPTS = 100;
//    private static final int MAX_SINGLE_WEIGHT = 150;
//    private static final int MIN_WEIGHT = 20;
//    private static final double DIVERSITY_WEIGHT = 0.3;
//    private static final List<BhCategories> STAGE_ORDER = new ArrayList<>();
//
//    public MealRecommendation generateRecommendation(MealTarget target) throws DietException {
//        List<FoodItem> selected = new ArrayList<>();
//        NutritionTracker tracker = new NutritionTracker(target);
//        MealContext context = new MealContext(selected, tracker);
//
//        // 第一阶段：按类型顺序推荐
//        for (FoodType type : STAGE_ORDER) {
//            if (tracker.isSatisfied()) break;
//            recommendByType(context, type);
//        }
//
//        // 第二阶段：优化填充剩余配额
//        if (!tracker.isSatisfied()) {
//            fillRemainingQuota(context);
//        }
//
//        // 最终微调
//        MealRecommendation recommendation = optimizePortions(context);
//        validateRecommendation(recommendation, target);
//
//        // 更新历史记录
//        mealHistory.recordRecommendation(recommendation);
//        return recommendation;
//    }
//
//    private void recommendByType(MealContext context, FoodType type) {
//        List<BhFood> candidates = filterFoods(type, context);
//        if (candidates.isEmpty()) return;
//
//        List<ScoredFood> scoredFoods = scoreFoods(candidates, context);
//        BhFood selected = probabilisticSelect(scoredFoods);
//        if (selected != null) {
//            addFoodToRecommendation(selected, context);
//        }
//    }
//
//    private List<BhFood> filterFoods(FoodType type, MealContext context) {
//        return allFoods.stream()
//                .filter(f -> f.getPid() == type)
//                .filter(f -> !context.containsFood(f.getId()))
//                .filter(f -> userPreference.isAllowed(f))
//                .collect(Collectors.toList());
//    }
//
//    private List<ScoredFood> scoreFoods(List<BhFood> candidates, MealContext context) {
//        return candidates.stream()
//                .map(f -> new ScoredFood(f, calculateFoodScore(f, context)))
//                .sorted(Comparator.comparingDouble(ScoredFood::getScore).reversed())
//                .collect(Collectors.toList());
//    }
//
//    private double calculateFoodScore(BhFood food, MealContext context) {
//        // 基础营养分
//        double nutritionScore = calculateNutritionScore(food, context.tracker);
//
//        // 多样性分
//        double diversityScore = 1.0 / (1 + mealHistory.getRecentUsageCount(food));
//
//        // 用户偏好
//        double preference = userPreference.getPreference(food);
//
//        // 类型多样性
//        double typeDiversity = context.getTypeDiversityFactor(food.getType());
//
//        return (nutritionScore * (1 - DIVERSITY_WEIGHT))
//                + (diversityScore * DIVERSITY_WEIGHT)
//                * preference * typeDiversity;
//    }
//
//    private BhFood probabilisticSelect(List<ScoredFood> scoredFoods) {
//        if (scoredFoods.isEmpty()) return null;
//
//        double totalScore = scoredFoods.stream()
//                .mapToDouble(ScoredFood::getScore)
//                .sum();
//
//        double random = Math.random() * totalScore;
//        double accumulated = 0;
//
//        for (ScoredFood sf : scoredFoods) {
//            accumulated += sf.getScore();
//            if (accumulated >= random) {
//                return sf.getFood();
//            }
//        }
//        return scoredFoods.get(0).getFood();
//    }
//
//    private void addFoodToRecommendation(BhFood food, MealContext context) {
//        double maxGrams = calculateMaxGrams(food, context.tracker);
//        FoodItem item = new FoodItem(food, maxGrams);
//        context.addItem(item);
//    }
//
//    private double calculateMaxGrams(BhFood food, NutritionTracker tracker) {
//        double[] limits = {
//                tracker.getRemainingCalory() / food.getCalory() * 100,
//                tracker.getRemainingProtein() / food.getProtein() * 100,
//                tracker.getRemainingCarb() / food.getCarbohydrate() * 100,
//                tracker.getRemainingFat() / food.getFat() * 100
//        };
//
//        double max = Arrays.stream(limits).min().orElse(0);
//        return Math.min(Math.max(max, MIN_WEIGHT), MAX_SINGLE_WEIGHT);
//    }
//
//    private void fillRemainingQuota(MealContext context) {
//        int attempts = 0;
//        while (!context.tracker.isSatisfied() && attempts++ < MAX_ATTEMPTS) {
//            List<BhFood> candidates = allFoods.stream()
//                    .filter(f -> !context.containsFood(f.getId()))
//                    .collect(Collectors.toList());
//
//            if (candidates.isEmpty()) break;
//
//            BhFood food = selectForFilling(candidates, context);
//            if (food == null) continue;
//
//            double maxGrams = calculateMaxGrams(food, context.tracker);
//            if (maxGrams < MIN_WEIGHT) continue;
//
//            context.addItem(new FoodItem(food, maxGrams));
//        }
//    }
//
//    private BhFood selectForFilling(List<BhFood> candidates, MealContext context) {
//        // 优先选择能补足最缺乏营养的食物
//        NutrientPriority priority = context.tracker.getMostDeficient();
//
//        return candidates.stream()
//                .sorted(Comparator.comparingDouble(f -> {
//                    switch (priority) {
//                        case PROTEIN: return -f.getProtein();
//                        case CARB: return -f.getCarbohydrate();
//                        case FAT: return -f.getFat();
//                        default: return -f.getCalory();
//                    }
//                }))
//                .findFirst()
//                .orElse(null);
//    }
//
//    private MealRecommendation optimizePortions(MealContext context) {
//        // 按比例微调克数
//        double caloryRatio = context.tracker.getOriginalTarget().getCalory()
//                / context.tracker.getConsumedCalory();
//
//        List<FoodItem> optimized = context.getItems().stream()
//                .map(item -> {
//                    double newGrams = item.getGrams() * caloryRatio;
//                    return new FoodItem(item.getFood(), Math.round(newGrams * 10) / 10.0);
//                })
//                .collect(Collectors.toList());
//
//        return new MealRecommendation(optimized);
//    }
//
//    private void validateRecommendation(MealRecommendation recommendation, MealTarget target)
//            throws DietException {
//
//        NutritionTracker validator = new NutritionTracker(target);
//        recommendation.getItems().forEach(validator::consume);
//
//        if (!validator.isSatisfied()) {
//            throw new DietException("无法满足营养目标")
//                    .withContext("remaining", validator.getRemaining())
//                    .withContext("recommended", recommendation.getSummary());
//        }
//    }
//
//    // 辅助类
//    private static class MealContext {
//        private final List<FoodItem> items;
//        private final NutritionTracker tracker;
//        private final Set<Long> foodIds = new HashSet<>();
//        private final Map<Long, Integer> typeCounts = new HashMap<>();
//
//        MealContext(List<FoodItem> items, NutritionTracker tracker) {
//            this.items = new ArrayList<>(items);
//            this.tracker = tracker;
//            items.forEach(this::updateContext);
//        }
//
//        void addItem(FoodItem item) {
//            items.add(item);
//            updateContext(item);
//            tracker.consume(item);
//        }
//
//        private void updateContext(FoodItem item) {
//            foodIds.add(item.getFood().getId());
//            typeCounts.merge(item.getFood().getPid(), 1, Integer::sum);
//        }
//
//        boolean containsFood(Long foodId) {
//            return foodIds.contains(foodId);
//        }
//
//        double getTypeDiversityFactor(Long type) {
//            int count = typeCounts.getOrDefault(type, 0);
//            return 1.0 / (1 + count);
//        }
//
//        List<FoodItem> getItems() {
//            return Collections.unmodifiableList(items);
//        }
//    }
//
//    private static class ScoredFood {
//        private final BhFood food;
//        private final double score;
//
//        ScoredFood(BhFood food, double score) {
//            this.food = food;
//            this.score = score;
//        }
//
//        BhFood getFood() { return food; }
//        double getScore() { return score; }
//    }
//
//    enum NutrientPriority { PROTEIN, CARB, FAT, CALORY }
//}
