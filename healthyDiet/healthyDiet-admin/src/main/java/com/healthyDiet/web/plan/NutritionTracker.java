package com.healthyDiet.web.plan;

import com.healthyDiet.web.dietPlan.FoodItem;
import com.healthyDiet.web.dietPlan.MealTarget;
import lombok.Data;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 17:36
 */
@Data
public class NutritionTracker{
    private final MealTarget original;
    private double remainingCalory;
    private double remainingProtein;
    private double remainingCarb;
    private double remainingFat;

    public NutritionTracker(MealTarget target) {
        this.original = target;
        this.remainingCalory = target.getCalory();
        this.remainingProtein = target.getProtein();
        this.remainingCarb = target.getCarb();
        this.remainingFat = target.getFat();
    }

    public void consume(FoodItem item) {
        remainingCalory -= item.getTotalCalory();
        remainingProtein -= item.getTotalProtein();
        remainingCarb -= item.getTotalCarb();
        remainingFat -= item.getTotalFat();
    }

    public boolean isSatisfied() {
        return remainingCalory <= 0
                && remainingProtein <= 0
                && remainingCarb <= 0
                && remainingFat <= 0;
    }

    NutrientPriority getMostDeficient() {
        double max = Math.max(Math.max(
                remainingProtein/original.getProtein(),
                remainingCarb/original.getCarb()
        ), remainingFat/original.getFat());

        if (max == remainingProtein/original.getProtein()) return NutrientPriority.PROTEIN;
        if (max == remainingCarb/original.getCarb()) return NutrientPriority.CARB;
        return NutrientPriority.FAT;
    }

    enum NutrientPriority { PROTEIN, CARB, FAT, CALORY }

}
