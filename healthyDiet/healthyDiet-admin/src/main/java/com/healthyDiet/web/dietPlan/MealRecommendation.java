package com.healthyDiet.web.dietPlan;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 15:36
 */

/**
 * 单餐推荐结果（包含多个食物项）
 */
public class MealRecommendation {
    // 不可修改的食物项列表（保证线程安全）
    private final List<FoodItem> items;

    // 构造方法（防御性复制）
    public MealRecommendation(List<FoodItem> items) {
        this.items = Collections.unmodifiableList(
                items != null ? items : Collections.emptyList()
        );
    }

    // 获取所有食物项（不可修改视图）
    public List<FoodItem> getItems() {
        return items;
    }

    // 获取总热量（单位：千卡）
    public double getTotalCalories() {
        return items.stream()
                .mapToDouble(FoodItem::getTotalCalory)
                .sum();
    }

    // 获取总碳水（单位：克）
    public double getTotalCarbohydrate() {
        return items.stream()
                .mapToDouble(item -> new Double(item.getFood().getCarbohydrate()) * item.getGrams() / 100)
                .sum();
    }

    // 获取总蛋白质（单位：克）
    public double getTotalProtein() {
        return items.stream()
                .mapToDouble(item -> new Double(item.getFood().getProtein()) * item.getGrams() / 100)
                .sum();
    }

    // 获取总脂肪（单位：克）
    public double getTotalFat() {
        return items.stream()
                .mapToDouble(item -> new Double(item.getFood().getFat() ) * item.getGrams() / 100)
                .sum();
    }

    // 检查是否满足营养目标（允许±5%误差）
    public boolean isMetTarget(MealTarget target) {
        return isWithinRange(getTotalCalories(), target.getCalory(), 0.05) &&
                isWithinRange(getTotalCarbohydrate(), target.getCarb(), 0.10) &&
                isWithinRange(getTotalProtein(), target.getProtein(), 0.10) &&
                isWithinRange(getTotalFat(), target.getFat(), 0.15);
    }

    private boolean isWithinRange(double actual, double target, double tolerance) {
        double min = target * (1 - tolerance);
        double max = target * (1 + tolerance);
        return actual >= min && actual <= max;
    }

    // 获取食物名称列表（用于展示）
    public List<String> getFoodNames() {
        return items.stream()
                .map(item -> item.getFood().getName())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format(
                "MealRecommendation{热量=%.1f千卡, 碳水=%.1fg, 蛋白质=%.1fg, 脂肪=%.1fg, 食物=%s}",
                getTotalCalories(),
                getTotalCarbohydrate(),
                getTotalProtein(),
                getTotalFat(),
                getFoodNames()
        );
    }
}