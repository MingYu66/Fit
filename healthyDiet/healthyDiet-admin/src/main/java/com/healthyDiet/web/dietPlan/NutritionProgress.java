package com.healthyDiet.web.dietPlan;

import lombok.Data;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 17:04
 */
@Data
public class NutritionProgress {
    double calory;
    double protein;
    double carb;
    double fat;

    NutritionProgress(MealTarget target) {
        this.calory = target.getCalory();
        this.protein = target.getProtein();
        this.carb = target.getCarb();
        this.fat = target.getFat();
    }

    void consume(FoodItem item) {
        this.calory -= item.getTotalCalory();
        this.protein -= item.getTotalProtein();
        this.carb -= item.getTotalCarb();
        this.fat -= item.getTotalFat();
    }

    boolean isSatisfied() {
        if(calory <= 0 && protein <= 0 && carb <= 0 && fat <= 0){
            return true;
        } else if (calory <= 0 && protein <= 0) {
            return true;
        }else if (calory <= 0 && carb <= 0) {
            return true;
        } else if (calory <=0) {
            return true;
        }
        return false;
    }
}
