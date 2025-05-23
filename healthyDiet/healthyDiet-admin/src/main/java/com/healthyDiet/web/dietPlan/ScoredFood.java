package com.healthyDiet.web.dietPlan;

import com.healthyDiet.system.domain.BhFood;
import lombok.Data;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 17:54
 */
@Data
public class ScoredFood {
    final BhFood food;
    final double score;
    final double grams;

    ScoredFood(BhFood food, double score, double grams) {
        this.food = food;
        this.score = score;
        this.grams = grams;
    }
}
