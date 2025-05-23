package com.healthyDiet.web.dietPlan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 15:22
 */
public class NutritionTarget {
    // 按中国居民膳食指南比例
    private static final double BREAKFAST_RATIO = 0.3; //早餐比例0.3
    private static final double LUNCH_RATIO = 0.4;//午餐比例0.4
    private static final double DINNER_RATIO = 0.3;//晚餐比例0.3

    // 三大营养素供能比例
    private static final double CARB_RATIO = 0.5;//碳水占比
    private static final double PROTEIN_RATIO = 0.3;//蛋白占比
    private static final double FAT_RATIO = 0.2;//脂肪占比

    public static Map<MealTime, MealTarget> calculateTarget(double dailyCalory) {
        Map<MealTime, MealTarget> targets = new HashMap<>();

        // 计算每日营养总量（按供能比例）
        double carbGram = (dailyCalory * CARB_RATIO) / 4;  // 碳水4千卡/g
        double proteinGram = (dailyCalory * PROTEIN_RATIO) / 4; //蛋白质
        double fatGram = (dailyCalory * FAT_RATIO) / 9;     // 脂肪9千卡/g

        // 分配三餐
        targets.put(MealTime.MORNING, createMealTarget(BREAKFAST_RATIO, dailyCalory, carbGram, proteinGram, fatGram,0));
        targets.put(MealTime.NOON, createMealTarget(LUNCH_RATIO, dailyCalory, carbGram, proteinGram, fatGram,1));
        targets.put(MealTime.EVENING, createMealTarget(DINNER_RATIO, dailyCalory, carbGram, proteinGram, fatGram,2));

        return targets;
    }

    private static MealTarget createMealTarget(double ratio, double dailyCalory,
                                               double totalCarb, double totalProtein, double totalFat,int type) {
        return new MealTarget(
                dailyCalory * ratio,
                totalCarb * ratio,
                totalProtein * ratio,
                totalFat * ratio,type
        );
    }






}