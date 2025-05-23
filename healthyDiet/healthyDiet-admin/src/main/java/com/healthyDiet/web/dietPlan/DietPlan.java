package com.healthyDiet.web.dietPlan;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 15:23
 */
public class DietPlan {
    private Map<MealTime, MealRecommendation> recommendations = new EnumMap<>(MealTime.class);

    public void addRecommendation(MealTime time, MealRecommendation recommendation) {
        recommendations.put(time, recommendation);
    }

    public Map<MealTime, MealRecommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Map<MealTime, MealRecommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public void printPlan() {
        recommendations.forEach((time, rec) -> {
            System.out.println(time.getDisplayName() + ":");
            rec.getItems().forEach(item ->
                    System.out.printf(" - %s（%.0f克）%n",
                            item.getFood().getName(),
                            item.getGrams()));
        });
    }
}
