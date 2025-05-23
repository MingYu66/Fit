package com.healthyDiet.web.dietPlan;

import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.system.domain.BhFood;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 15:31
 */
@Data
@AllArgsConstructor
public class FoodItem {
    private BhFood food;
    private double grams;  // 克数




    public double getTotalCalory() {
        return new Double(food.getCalory()) * grams / 100;
    }
    public double getTotalFat() {
        return new Double(food.getFat()) * grams / 100;
    }
    public double getTotalCarb() {
        return new Double(food.getCarbohydrate()) * grams / 100;
    }
    public double getTotalProtein() {
        return new Double(food.getProtein()) * grams / 100;
    }
}