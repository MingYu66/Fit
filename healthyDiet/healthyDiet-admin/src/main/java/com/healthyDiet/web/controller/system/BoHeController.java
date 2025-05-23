package com.healthyDiet.web.controller.system;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.healthyDiet.common.annotation.Log;
import com.healthyDiet.common.core.controller.BaseController;
import com.healthyDiet.common.core.domain.AjaxResult;
import com.healthyDiet.common.core.page.TableDataInfo;
import com.healthyDiet.common.enums.BusinessType;
import com.healthyDiet.system.domain.*;
import com.healthyDiet.system.service.*;
import com.healthyDiet.system.service.impl.DeepSeekService;
import com.healthyDiet.web.controller.healthData.BloodPressureEntry;
import com.healthyDiet.web.controller.healthData.DataEntry;
import com.healthyDiet.web.controller.healthData.HealthDataSimulator;
import com.healthyDiet.web.dietPlan.DietPlan;
import com.healthyDiet.web.dietPlan.MealRecommendation;
import com.healthyDiet.web.dietPlan.MealTime;
import com.healthyDiet.web.util.AiFile;
import com.healthyDiet.web.util.DietException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.healthyDiet.web.dietPlan.DietRecommender;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.healthyDiet.web.controller.healthData.HealthDataSimulator.getCurrentMinuteIndex;
import static com.healthyDiet.web.util.FoodLoveRecommend.getLoveFoodRecommd;
import static com.healthyDiet.web.util.FoodRecommend.getFoodRecommd;
import static com.healthyDiet.web.util.Sample.AIBurdenSheet;
import static com.healthyDiet.web.util.SampleFood.DetectLabelPro;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

@PermitAll
@RestController
@RequestMapping(value = "bohe")
public class BoHeController extends BaseController {

    @Autowired
    private IBoheUserService boheUserService;
    @Autowired
    private IBhCategoriesService bhCategoriesService;

    @Autowired
    private IBhFoodService bhFoodService;
    @Autowired
    private IBhCaloriesService bhCaloriesService;
    @Autowired
    private IBhMoveService bhMoveService;
    @Autowired
    private IBhLoveFoodService loveFoodService;

    @Autowired
    private IBhALoveService aLoveService;
    @Autowired
    private IBhArticeService articeService;

    @Autowired
    private IBhFoodmenuService iBhFoodmenuService;

    @Autowired
    private IBhUserWeightService weightService;
    @Autowired
    private IBoheWenzhangService wenzhangService;

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private IBhJianyiService jianyiService;
    @Autowired
    private IBhXinliService bhXinliService;
    @Autowired
    private IBhNoteService bhNoteService;

    @Autowired
    private IBhOrderService bhOrderService;
    @Autowired
    private IBhShopService bhShopService;

    @Autowired
    private IBhShopCommentService shopCommentService;
    @Autowired
    private IBhShopLoveService shopLoveService;
    @Autowired
    private DeepSeekService deepSeekService;


    /**
     * 小程序用户登录接口
     *
     * @param boheUser
     * @return
     */
    @PermitAll
    @PostMapping("/boheLogin")
    public AjaxResult boheLogin(@RequestBody BoheUser boheUser) {
        System.out.println(boheUser);
        BoheUser boheUser1 = new BoheUser();
        boheUser1.setUsername(boheUser.getUsername());
        List<BoheUser> boheUsers = boheUserService.selectBoheUserList(boheUser1);
        if (boheUsers.size() == 0) {
            return AjaxResult.error(" 账号不正确 ");
        }
        boheUser1 = boheUsers.get(0);
        if (boheUser1.getPassword().equals(boheUser.getPassword())) {
            return success(boheUser1);
        }
        return AjaxResult.error("账号或密码错误！");

    }

    /**
     * 用户注册
     */
    @PermitAll
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BoheUser boheUser) {
        BoheUser old = new BoheUser();
        old.setUsername(boheUser.getUsername());
        if (boheUserService.selectBoheUserList(old).size() != 0) {
            return AjaxResult.error("用户名已被注册");
        }
        int i = boheUserService.insertBoheUser(boheUser);
        if (i == 1) {
            return AjaxResult.success(boheUser);
        }
        return AjaxResult.error("注册失败");
    }


    /**
     * 添加摄入和运动消耗
     *
     * @param calories
     * @return
     */
    @PermitAll
    @PostMapping(value = "addCalory")
    public AjaxResult addCalory(@RequestBody BhCalories calories) {


        if (calories.getUid() != null && calories.getUid() > 0) {
            BhCalories old = new BhCalories();

            old.setcId(calories.getcId());
            old.setCreateTime(calories.getCreateTime());
            old.setUid(calories.getUid());
            old.setcType(calories.getcType());
            //检测一下今天是否 同样的食物并且同一类型（早中晚餐）
            if(calories.getcType()!=4){
                List<BhCalories> oldList = bhCaloriesService.selectBhCaloriesList(old);
                if (oldList.size() > 0) {
                    // 数组结果大于0说明添加过，那就取第一个更新一下 摄入热量 和 食物重量
                    oldList.get(0).setcValue(calories.getcValue() + oldList.get(0).getcValue());
                    oldList.get(0).setcNum(calories.getcNum() + oldList.get(0).getcNum());
                    return toAjax(bhCaloriesService.updateBhCalories(oldList.get(0)));
                }
            }

            return toAjax(bhCaloriesService.insertBhCalories(calories));


        }

        return error(String.valueOf(calories));


    }

    /**
     * 新增建议反馈
     */
    @PermitAll()
    @PostMapping("addJianyi")
    public AjaxResult addJianyi(@RequestBody BhJianyi bhJianyi) {
        return toAjax(jianyiService.insertBhJianyi(bhJianyi));
    }

    /**
     * 查询分类列表
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "categorisList")
    public AjaxResult categorisList() {
        return AjaxResult.success(bhCategoriesService.selectBhCategoriesList(new BhCategories()));
    }

    /**
     * 查询分类下所有食物
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "byCategorisIdFood")
    public AjaxResult byCategorisIdFood(@RequestBody BhFood food) {

        if (food.getPid() == null) {
            return AjaxResult.error();
        }
        return AjaxResult.success(bhFoodService.selectBhFoodList(food));
    }


    /**
     * 配料识别
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "aiBurdenSheet")
    public AjaxResult aiBurdenSheet(@RequestBody AiFile aiFile) {


        return AjaxResult.success(AIBurdenSheet(aiFile.getImg()));
    }

    /**
     * 食物识别
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "aiFood")
    public AjaxResult aiFood(@RequestBody AiFile aiFile) {
        //DetectLabelPro 腾讯识别
        List<String> list = DetectLabelPro(aiFile.getImg());
        List<BhFood> result = new ArrayList<>();
        if (list.size() > 0) {
            for (String item : list
            ) {
                BhFood food = new BhFood();
                food.setName(item);
                List<BhFood> foods = bhFoodService.selectBhAIFoodList(food);
                if (foods.size() > 0) {
                    result.addAll(foods);
                } else {
                    food.setName(food.getName() + "，暂无该食物");
                    result.add(food);

                }

            }
        }

        return AjaxResult.success(result);
    }

    /**
     * 根据协同算法 【余弦相似度】 进行 食物推荐
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "recommendFood")
    public AjaxResult recommendFood(@RequestBody BhCalories c) {
        String key = "REC-" + c.getUid();
        if (redisTemplate.hasKey(key)) {
            return AjaxResult.success(redisTemplate.opsForValue().get(key));
        }
        List<BhCalories> calories = bhCaloriesService.selectBhCaloriesList(null);
        List<Long> list = getFoodRecommd(c.getUid(), calories);
        List<BhFood> foods = new ArrayList<>();
        for (Long item : list
        ) {
            System.out.println("食物ID：" + item);
            try {
                BhFood food = bhFoodService.selectBhFoodById(item);
                System.out.println(food.getName());
                foods.add(food);
            } catch (Exception e) {
                System.out.println("这个食物不见了-.-");
            }
        }
        if (foods.size() > 0) {
            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(foods), 10, MILLISECONDS);
        } else {
            BhFood food = new BhFood();
            food.setPid(4L);
            List<BhFood> foods1 = bhFoodService.selectBhFoodList(food);
            Random random = new Random();
            List<BhFood> foods2 = bhFoodService.selectBhFoodList(food);
            foods2.add(foods1.get(random.nextInt(10)));
            foods2.add(foods1.get(random.nextInt(10)));
            foods2.add(foods1.get(random.nextInt(10)));
            foods2.add(foods1.get(random.nextInt(10)));
            foods2 = foods2.stream().distinct().collect(Collectors.toList());
            foods = foods2;
        }
        return AjaxResult.success(JSONObject.toJSONString(foods));
    }

    /**
     * 根据协同算法 【余弦相似度】 进行 运动推荐
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "recommendMove")
    public AjaxResult recommendMove(@RequestBody BhCalories c) {
        String key = "MOVE-" + c.getUid();
        if (redisTemplate.hasKey(key)) {
            return AjaxResult.success(redisTemplate.opsForValue().get(key));
        }
        BhCalories calorie = new BhCalories();
        calorie.setcType(4L);
        List<BhCalories> calories = bhCaloriesService.selectBhCaloriesList(calorie);
        System.out.println("运动数量：" + calories.size());
        List<Long> list = getFoodRecommd(c.getUid(), calories);
        System.out.println("推荐运动：" + list);
        List<BhMove> foods = new ArrayList<>();
        for (Long item : list
        ) {
            System.out.println("运动ID：" + item);
            try {
                BhMove food = bhMoveService.selectBhMoveById(item);
                System.out.println(food.getName());
                foods.add(food);
            } catch (Exception e) {
                System.out.println("这个运动不见了-.-");
            }

        }

        if (foods.size() > 0) {
            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(foods), 10, MILLISECONDS);
        } else {
            Random random = new Random();
            BhMove food = bhMoveService.selectBhMoveById(Long.parseLong(String.valueOf(random.nextInt(3))));
            foods.add(food);
        }
        return AjaxResult.success(JSONObject.toJSONString(foods));
    }


    /**
     * 根据协同算法 【余弦相似度】 进行 收藏食物推荐
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "recommendLoveFood")
    public AjaxResult recommendLoveFood(@RequestBody BhCalories c) {
        String key = "REC-" + c.getUid();
        if (redisTemplate.hasKey(key)) {
            return AjaxResult.success(redisTemplate.opsForValue().get(key));
        }
        List<BhLoveFood> loveFoods = loveFoodService.selectBhLoveFoodList(null);
        List<Long> list = getLoveFoodRecommd(c.getUid(), loveFoods);
        List<BhFood> foods = new ArrayList<>();
        for (Long item : list
        ) {
            System.out.println("食物ID：" + item);
            try {
                BhFood food = bhFoodService.selectBhFoodById(item);
                System.out.println(food.getName());
                foods.add(food);
            } catch (Exception e) {
                System.out.println("这个食物不见了-.-");
            }
        }
        if (foods.size() > 0) {
            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(foods), 10, MILLISECONDS);
        } else {
            BhFood food = new BhFood();
            food.setPid(4L);
            List<BhFood> foods1 = bhFoodService.selectBhFoodList(food);
            Random random = new Random();
            List<BhFood> foods2 = bhFoodService.selectBhFoodList(food);
            foods2.add(foods1.get(random.nextInt(10)));
            foods2.add(foods1.get(random.nextInt(10)));
            foods2.add(foods1.get(random.nextInt(10)));
            foods2.add(foods1.get(random.nextInt(10)));
            foods2 = foods2.stream().distinct().collect(Collectors.toList());
            foods = foods2;
        }
        return AjaxResult.success(JSONObject.toJSONString(foods));
    }


    /**
     * 根据当前时间获取 用户今日体重
     *
     * @param c
     * @return
     */
    @PermitAll
    @PostMapping(value = "getDayWeight")
    public AjaxResult getDayWeight(@RequestBody BhUserWeight c) {
        c.setCreateTime(new Date());
        List<BhUserWeight> userWeights = weightService.selectBhUserWeightList(c);
        return AjaxResult.success(userWeights);
    }


    /**
     * 获取用户体重记录
     *
     * @param c
     * @return
     */
    @PermitAll
    @PostMapping(value = "getUserWeight")
    public AjaxResult getUserWeight(@RequestBody BhUserWeight c) {
        List<BhUserWeight> userWeights = weightService.selectBhUserWeightList(c);
        return AjaxResult.success(userWeights);
    }

    /**
     * 删除社区评论
     *
     * @param artice
     * @return
     */
    @PermitAll
    @PostMapping(value = "deleSpack")
    public AjaxResult deleSpack(@RequestBody BhArtice artice) {

        return AjaxResult.success(articeService.deleteBhArticeById(artice.getId()));
    }


    /**
     * 提交用户体重
     *
     * @param c
     * @return
     */
    @PermitAll
    @PostMapping(value = "setUserWeight")
    public AjaxResult setUserWeight(@RequestBody BhUserWeight c) {
        c.setCreateTime(new Date());
        BhUserWeight old = new BhUserWeight();
        old.setUserId(c.getUserId());
        old.setCreateTime(c.getCreateTime());
        //血糖、体重 更新记录
        List<BhUserWeight> userWeights = weightService.selectBhUserWeightList(old);
        if (userWeights.size() > 0) {
            userWeights.get(0).setWeight(c.getWeight());
            BoheUser boheUser = boheUserService.selectBoheUserById(c.getUserId());
            boheUser.setWeight(c.getWeight());
            boheUserService.updateBoheUser(boheUser);
            weightService.updateBhUserWeight(userWeights.get(0));
            return AjaxResult.warn("更新成功");
        }
        weightService.insertBhUserWeight(c);
        return AjaxResult.warn("打卡成功");
    }


    /**
     * 获取健康文章
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "getWenZhang")
    public AjaxResult getWenZhang() {
        List<BoheWenzhang> userWeights = wenzhangService.selectBoheWenzhangList(null);
        return AjaxResult.success(userWeights);
    }


    /**
     * 获取健康文章
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "getWenZhangById")
    public AjaxResult getWenZhangById(@RequestBody BoheWenzhang wenzhang) {

        return AjaxResult.success(wenzhangService.selectBoheWenzhangById(wenzhang.getId()));
    }


    /**
     * 查询所有 运动项目
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "movesList")
    public AjaxResult movesList() {

        return AjaxResult.success(bhMoveService.selectBhMoveList(null));
    }

    /**
     * 查询所有 运动项目
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "calories")
    public AjaxResult calories(@RequestBody BhCalories bhCalories) throws ParseException {
//        LocalDate date = LocalDate.now();
//
//        // 格式化日期
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String formattedDate = date.format(formatter);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        bhCalories.setCreateTime(dateFormat.parse(formattedDate));

        List<BhCalories> calories = bhCaloriesService.selectBhCaloriesList(bhCalories);

        List<BhCalories> eatList = bhCaloriesService.selectBhCaloriesListByWeight(bhCalories);


        Set<BhFood> foodSet = new HashSet<>();
        BhFood bhFood = new BhFood();
        if (eatList.size() > 0) {
            for (BhCalories item : calories
            ) {
                BhFood food = bhFoodService.selectBhFoodById(item.getcId());
//                System.out.println("吃了：" + item.getcWeight() + " g" + "，每100g含量 ：" + food.getCalcium());
                try {
                    food.setLactoflavin(new BigDecimal(food.getLactoflavin()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setFiberDietary(new BigDecimal(food.getFiberDietary()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());

                    food.setProtein(new BigDecimal(food.getProtein()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setCalcium(new BigDecimal(food.getCalcium()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setCarbohydrate(new BigDecimal(food.getCarbohydrate()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setCholesterol(new BigDecimal(food.getCholesterol()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setCopper(new BigDecimal(food.getCopper()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setFat(new BigDecimal(food.getFat()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setZinc(new BigDecimal(food.getZinc()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setGi(new BigDecimal(food.getGi()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setGl(new BigDecimal(food.getGl()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setIron(new BigDecimal(food.getIron()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setKalium(new BigDecimal(food.getKalium()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setNiacin(new BigDecimal(food.getNiacin()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setVitaminC(new BigDecimal(food.getVitaminC()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setThiamine(new BigDecimal(food.getThiamine()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setVitaminE(new BigDecimal(food.getVitaminE()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setVitaminA(new BigDecimal(food.getVitaminA()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setNatrium(new BigDecimal(food.getNatrium()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setVitaminB6(new BigDecimal(food.getVitaminB6()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
                    food.setSugar(new BigDecimal(food.getSugar()).multiply(new BigDecimal(item.getcWeight() / 100L)).toString());
//                System.out.println("摄入含量：" + food.getCalcium());
                    foodSet.add(food);
                } catch (Exception e) {
                    System.out.println("没有该食物：" + item.getcId() + " - - " + item.getcName());
                }


            }
        }
        bhFood = bhFood.getFood(bhFood);
        for (BhFood food : foodSet
        ) {

            bhFood.setLactoflavin(new BigDecimal(food.getLactoflavin()).add(new BigDecimal(bhFood.getLactoflavin())).toString());
            bhFood.setFiberDietary(new BigDecimal(food.getFiberDietary()).add(new BigDecimal(bhFood.getFiberDietary())).toString());

            bhFood.setProtein(new BigDecimal(food.getProtein()).add(new BigDecimal(bhFood.getProtein())).toString());
            bhFood.setCalcium(new BigDecimal(food.getCalcium()).add(new BigDecimal(bhFood.getCalcium())).toString());
            bhFood.setCarbohydrate(new BigDecimal(food.getCarbohydrate()).add(new BigDecimal(bhFood.getCarbohydrate())).toString());
            bhFood.setCholesterol(new BigDecimal(food.getCholesterol()).add(new BigDecimal(bhFood.getCholesterol())).toString());
            bhFood.setCopper(new BigDecimal(food.getCopper()).add(new BigDecimal(bhFood.getCopper())).toString());
            bhFood.setFat(new BigDecimal(food.getFat()).add(new BigDecimal(bhFood.getFat())).toString());
            bhFood.setZinc(new BigDecimal(food.getZinc()).add(new BigDecimal(bhFood.getZinc())).toString());
            bhFood.setGi(new BigDecimal(food.getGi()).add(new BigDecimal(bhFood.getGi())).toString());
            bhFood.setGl(new BigDecimal(food.getGl()).add(new BigDecimal(bhFood.getGl())).toString());
            bhFood.setIron(new BigDecimal(food.getIron()).add(new BigDecimal(bhFood.getIron())).toString());
            bhFood.setKalium(new BigDecimal(food.getKalium()).add(new BigDecimal(bhFood.getKalium())).toString());
            bhFood.setNiacin(new BigDecimal(food.getNiacin()).add(new BigDecimal(bhFood.getNiacin())).toString());
            bhFood.setVitaminC(new BigDecimal(food.getVitaminC()).add(new BigDecimal(bhFood.getVitaminC())).toString());
            bhFood.setThiamine(new BigDecimal(food.getThiamine()).add(new BigDecimal(bhFood.getThiamine())).toString());
            bhFood.setVitaminE(new BigDecimal(food.getVitaminE()).add(new BigDecimal(bhFood.getVitaminE())).toString());
            bhFood.setVitaminA(new BigDecimal(food.getVitaminA()).add(new BigDecimal(bhFood.getVitaminA())).toString());
            bhFood.setNatrium(new BigDecimal(food.getNatrium()).add(new BigDecimal(bhFood.getNatrium())).toString());
            bhFood.setVitaminB6(new BigDecimal(food.getVitaminB6()).add(new BigDecimal(bhFood.getVitaminB6())).toString());
            bhFood.setSugar(new BigDecimal(food.getSugar()).add(new BigDecimal(bhFood.getSugar())).toString());
        }


        return AjaxResult.success(bhCaloriesService.selectBhCaloriesList(bhCalories)).put("food", bhFood);
    }


    /**
     * 查询分类及所属食物列表
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "categorisAndFood")
    public AjaxResult categorisAndFood() {


        List<BhCategories> categories = bhCategoriesService.selectBhCategoriesList(new BhCategories());
        for (BhCategories item : categories
        ) {
            BhFood bhFood = new BhFood();
            bhFood.setPid(item.getId());
            item.setProducts(bhFoodService.selectBhFoodList(bhFood));
            bhFood = null;
        }

        return AjaxResult.success(categories);
    }

    /**
     * 删除 记录的 食物 或者 运动
     *
     * @param bhCalories
     * @return
     * @throws ParseException
     */
    @PermitAll
    @PostMapping(value = "delCalories")
    public AjaxResult delCalories(@RequestBody BhCalories bhCalories) throws ParseException {
        System.out.println(bhCalories);
        if (bhCalories.getcType() == null || bhCalories.getcId() == null || bhCalories.getUid() == null || bhCalories.getcNum() == null || bhCalories.getCreateTime() == null) {
            return AjaxResult.error();
        }
        return AjaxResult.success(bhCaloriesService.deleteBhCaloriesByOne(bhCalories));
    }

    /**
     * 查询所有收藏食物
     *
     * @param loveFood
     * @return
     * @throws ParseException
     */
    @PermitAll
    @PostMapping(value = "loveFoods")
    public AjaxResult loveFoods(@RequestBody BhLoveFood loveFood) throws ParseException {


        return AjaxResult.success(loveFoodService.selectBhLoveList(loveFood.getUid()));
    }

    /**
     * 添加收藏食物
     *
     * @param loveFood
     * @return
     * @throws ParseException
     */
    @PermitAll
    @PostMapping(value = "addloveFood")
    public AjaxResult addloveFood(@RequestBody BhLoveFood loveFood) {
        return AjaxResult.success(loveFoodService.insertBhLoveFood(loveFood));
    }

    /**
     * 删除 收藏的食物
     *
     * @param loveFood
     * @return
     */
    @PermitAll
    @PostMapping(value = "deleteBhLove")
    public AjaxResult deleteBhLove(@RequestBody BhLoveFood loveFood) {
        return AjaxResult.success(loveFoodService.deleteBhLove(loveFood));
    }


    /**
     * 检测是否收藏当前食物 1是 0否
     *
     * @param loveFood
     * @return
     */
    @PermitAll
    @PostMapping(value = "loveStatus")
    public AjaxResult loveStatus(@RequestBody BhLoveFood loveFood) {
        return AjaxResult.success(loveFoodService.selectBhLoveFoodList(loveFood).size() > 0 ? 1 : 0);
    }


    /**
     * 获取当前用户信息
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "userInfo")
    public AjaxResult userInfo(@RequestBody BoheUser user) {
        return AjaxResult.success(boheUserService.selectBoheUserById(user.getId()));
    }


    /**
     * 获取当前用户信息
     *
     * @param
     * @return
     */
    @PermitAll
    @PostMapping(value = "updateUserInfo")
    public AjaxResult updateUserInfo(@RequestBody BoheUser user) {
        return AjaxResult.success(boheUserService.updateBoheUser(user));
    }


    /**
     * 查询社区列表
     */
    @PostMapping("/Articelist")
    public List<BhArtice> Articelist(@RequestBody BhArtice bhArtice) {
        List<BhArtice> list = articeService.selectBhArticeList(bhArtice);
        for (BhArtice item : list
        ) {
            BhALove love = new BhALove();
            love.setUid(bhArtice.getUserid());
            love.setAid(item.getId());
            item.setStatus(aLoveService.selectBhALoveList(love).size() > 0);
        }
        return list;
    }


    /**
     * 新增社区
     */
    @PostMapping("/bhArticeadd")
    public AjaxResult bhArticeadd(@RequestBody BhArtice bhArtice) {
        return toAjax(articeService.insertBhArtice(bhArtice));
    }

    /**
     * 推荐食谱列表
     */
    @PostMapping("/bhMenuList")
    public AjaxResult bhMenuList(@RequestBody BhFoodmenu foodmenu) {
        return AjaxResult.success(iBhFoodmenuService.selectBhFoodmenuList(foodmenu));
    }

    /**
     * 食谱详情
     */
    @PostMapping("/bhMenuInfo")
    public AjaxResult bhMenuInfo(@RequestBody BhFoodmenu bhFoodmenu) {
        return AjaxResult.success(iBhFoodmenuService.selectBhFoodmenuById(bhFoodmenu.getId()));
    }


    /**
     * 新增点赞记录
     */
    @PostMapping("/bhALoveadd")
    public AjaxResult bhALoveadd(@RequestBody BhALove bhALove) {
        System.out.println(bhALove);

        try {
            aLoveService.insertBhALove(bhALove);
        } catch (Exception e) {
            aLoveService.deleteBhALoveByUid(bhALove);
        }


        BhArtice a = articeService.selectBhArticeById(bhALove.getAid());
        BhALove love = new BhALove();
        love.setUid(bhALove.getUid());
        love.setAid(a.getId());
        a.setStatus(aLoveService.selectBhALoveList(love).size() > 0);
        return AjaxResult.success(a);
    }


    /**
     * 导出心理疏导库列表
     *
     * @return
     */
    @PostMapping("/xinliList")
    public AjaxResult xinliList() {
        List<BhXinli> list = bhXinliService.selectBhXinliList(new BhXinli());
        return AjaxResult.success(list);
    }

    /**
     * 获取心理疏导库详细信息
     */
    @GetMapping(value = "/getXinLi")
    public AjaxResult getXinLi(BhXinli xinli) {
        return success(bhXinliService.selectBhXinliById(xinli.getId()));
    }


    /**
     * 查询便签列表
     */
    @GetMapping("/listNote")
    public TableDataInfo listNote(BhNote bhNote) {
//        startPage();
        List<BhNote> list = bhNoteService.selectBhNoteList(bhNote);
        return getDataTable(list);
    }

    /**
     * 新增便签
     */
    @PostMapping("addNote")
    public AjaxResult addNote(@RequestBody BhNote bhNote) {
        return toAjax(bhNoteService.insertBhNote(bhNote));
    }

    /**
     * 获取便签详细信息
     */
    @PostMapping(value = "getNote")
    public AjaxResult getInfo(@RequestBody BhNote bhNote) {
        return success(bhNoteService.selectBhNoteById(bhNote.getId()));
    }

    /**
     * 修改便签
     */
    @PostMapping("editNote")
    public AjaxResult editNote(@RequestBody BhNote bhNote) {
        return toAjax(bhNoteService.updateBhNote(bhNote));
    }

    /**
     * 删除便签
     */
    @DeleteMapping("/delNote")
    public AjaxResult delNote(@RequestBody BhNote bhNote) {
        return toAjax(bhNoteService.deleteBhNoteById(bhNote.getId()));
    }

    /**
     * 食物推荐
     */
    @PostMapping("getDietRecommender")
    public AjaxResult getDietRecommender(@RequestBody BoheUser user) throws DietException {
        user = boheUserService.selectBoheUserById(user.getId());
        List<BhFood> foods = bhFoodService.selectBhFoodList(null);
        Map<MealTime, MealRecommendation> plan = DietRecommender.generatePlan(user.getCalory(), foods);

        return success(plan);
    }

    /**
     * 查询商品列表
     */
    @PostMapping("/getShopList")
    public AjaxResult shopList(@RequestBody BhShop bhShop) {
        List<BhShop> list = bhShopService.selectBhShopList(bhShop);
        if (!list.isEmpty()) {
            for (BhShop shop : list) {
                BhShopLove shopLove = new BhShopLove();
                shopLove.setShopId(shop.getId());
                shopLove.setUid(bhShop.getUserId());
                shop.setLove(!shopLoveService.selectBhShopLoveList(shopLove).isEmpty());
            }
        }
        return success(list);
    }

    /**
     * 查询商品
     */
    @PostMapping("/getShopById")
    public AjaxResult getShopById(@RequestBody BhShop bhShop) {

        bhShop = bhShopService.selectBhShopById(bhShop.getId());
        if (null != bhShop) {
            BhShopLove shopLove = new BhShopLove();
            shopLove.setShopId(bhShop.getId());
            shopLove.setUid(bhShop.getUserId());
            bhShop.setLove(!shopLoveService.selectBhShopLoveList(shopLove).isEmpty());
            BhShopComment shopComment = new BhShopComment();
            shopComment.setShopId(bhShop.getId());

            bhShop.setShopComment(shopCommentService.selectBhShopCommentList(shopComment));
        }
        return success(bhShop);
    }


    /**
     * 查询订单列表
     */
    @GetMapping("/getOrderList")
    public AjaxResult orderList(BhOrder bhOrder) {
        List<BhOrder> list = bhOrderService.selectBhOrderList(bhOrder);
        if (!list.isEmpty()) {
            for (BhOrder order : list) {
                BhShopComment comment = new BhShopComment();
                comment.setUserId(bhOrder.getUserId());
                comment.setOrderId(order.getId());
                //检测用户是否已经评论该订单
                order.setComment(!shopCommentService.selectBhShopCommentList(comment).isEmpty());
            }
        }

        return success(list);
    }


    /**
     * 创建订单
     */
    @PostMapping("/createorder")
    public AjaxResult createorder(@RequestBody BhOrder bhOrder) {
        if (null == bhOrder.getUserId() || null == bhOrder.getShopId()) {
            return AjaxResult.error("请稍后再试");
        }

        if (bhOrderService.createOrder(bhOrder) == 1) {
            return success("购买成功");
        } else {
            return error("积分不足");
        }
    }


    /**
     * 查询所有收藏商品
     *
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "loveShops")
    public AjaxResult loveShops(@RequestBody BhShopLove shopLove) throws ParseException {


        return AjaxResult.success(shopLoveService.selectBhShopLoveList(shopLove));
    }

    /**
     * 添加/取消收藏商品
     *
     * @param shopLove
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "addloveShop")
    public AjaxResult addloveShop(@RequestBody BhShopLove shopLove) {
        if (null == shopLove.getShopId() || null == shopLove.getUid()) {
            return AjaxResult.error("请登录");
        }
        return AjaxResult.success(shopLoveService.insertBhShopLove(shopLove));
    }


    /**
     * 添加评论
     */
    @PostMapping("/createShopComment")
    public AjaxResult createShopComment(@RequestBody BhShopComment shopComment) {
        if (null == shopComment.getShopId() || null == shopComment.getOrderId() || null == shopComment.getUserId()) {
            return error("评论异常");
        }
        //判断用户是否购买过订单
        BhOrder o = new BhOrder();
        o.setShopId(shopComment.getShopId());
        o.setUserId(shopComment.getUserId());
        if (bhOrderService.selectBhOrderList(o).isEmpty()) {
            return error("下单后才能评论哦！");
        }
        //判断用户是否已经评论过该订单
        BhShopComment comment = new BhShopComment();
        comment.setOrderId(shopComment.getOrderId());
        comment.setUserId(shopComment.getUserId());
        if (!shopCommentService.selectBhShopCommentList(comment).isEmpty()) {
            return error("一个订单只能评论一次！");
        }
        shopCommentService.insertBhShopComment(shopComment);
        return success("提交成功");
    }


    @GetMapping("/gethealthDatas")
    public AjaxResult gethealthDatas() throws Exception {
        // 当前分钟索引（假设从0点开始计时）
        int current = getCurrentMinuteIndex();
        int start = current - 14;
        int end = current + 1;

        // 四类健康数据列表
        List<DataEntry> hearts = new ArrayList<>();
        List<BloodPressureEntry> bloods = new ArrayList<>();
        List<DataEntry> sleeps = new ArrayList<>();
        List<DataEntry> steps = new ArrayList<>();

        // 创建 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 读取缓存或生成数据：心率
        if (Boolean.TRUE.equals(redisTemplate.hasKey("hearts"))) {
            String json = redisTemplate.opsForValue().get("hearts").toString();
            hearts = mapper.readValue(json, new TypeReference<List<DataEntry>>() {}).subList(start, end);
        } else {
            List<DataEntry> list = HealthDataSimulator.generateHeartRateData(true);
            redisTemplate.opsForValue().set("hearts", JSONObject.toJSONString(list), 30, TimeUnit.MINUTES);
            hearts = list.subList(start, end);
        }

        // 读取缓存或生成数据：血压
        if (Boolean.TRUE.equals(redisTemplate.hasKey("xueya"))) {
            String json = redisTemplate.opsForValue().get("xueya").toString();
            bloods = mapper.readValue(json, new TypeReference<List<BloodPressureEntry>>() {}).subList(start, end);
        } else {
            List<BloodPressureEntry> list = HealthDataSimulator.generateBloodPressureData();
            redisTemplate.opsForValue().set("xueya", JSONObject.toJSONString(list), 30, TimeUnit.MINUTES);
            bloods = list.subList(start, end);
        }

        // 读取缓存或生成数据：睡眠质量
        if (Boolean.TRUE.equals(redisTemplate.hasKey("shuimian"))) {
            String json = redisTemplate.opsForValue().get("shuimian").toString();
            sleeps = mapper.readValue(json, new TypeReference<List<DataEntry>>() {});
        } else {
            List<DataEntry> list = HealthDataSimulator.generateSleepQualityData();
            redisTemplate.opsForValue().set("shuimian", JSONObject.toJSONString(list), 30, TimeUnit.MINUTES);
            sleeps = list;
        }

        // 读取缓存或生成数据：步数
        if (Boolean.TRUE.equals(redisTemplate.hasKey("bushu"))) {
            String json = redisTemplate.opsForValue().get("bushu").toString();
            steps = mapper.readValue(json, new TypeReference<List<DataEntry>>() {}).subList(start, end);
        } else {
            List<DataEntry> list = HealthDataSimulator.generateStepData();
            redisTemplate.opsForValue().set("bushu", JSONObject.toJSONString(list), 30, TimeUnit.MINUTES);
            steps = list.subList(start, end);
        }

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("hearts", hearts);
        stringObjectMap.put("bloods", bloods);

        if (Boolean.TRUE.equals(redisTemplate.hasKey("deepseekInfo"))) {
            String json = redisTemplate.opsForValue().get("deepseekInfo").toString();
            stringObjectMap.put("msg",json);
        } else {
            String msg =  deepSeekService.getDeepseepHealtyh(0L,stringObjectMap.toString());
            stringObjectMap.put("msg",msg);
            redisTemplate.opsForValue().set("deepseekInfo", JSONObject.toJSONString(msg), 2, TimeUnit.MINUTES);

        }


        stringObjectMap.put("sleeps", sleeps);
        stringObjectMap.put("steps", steps);



        return success(stringObjectMap);
    }

}
