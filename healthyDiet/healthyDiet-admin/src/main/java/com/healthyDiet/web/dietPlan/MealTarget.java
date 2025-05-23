package com.healthyDiet.web.dietPlan;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 15:37
 */
@AllArgsConstructor
@Data
public class MealTarget {
    private double calory;    // 目标热量（千卡）
    private double carb;      // 碳水目标（克）
    private double protein;   // 蛋白质目标（克）
    private double fat;       // 脂肪目标（克）
    private int type;
}