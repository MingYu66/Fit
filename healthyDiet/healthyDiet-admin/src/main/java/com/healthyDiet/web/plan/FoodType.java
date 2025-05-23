package com.healthyDiet.web.plan;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 17:42
 */
public enum FoodType {
    STAPLE(1),    // 主食
    PROTEIN(2),    // 蛋白质
    VEGETABLE(3),  // 蔬菜
    FRUIT(4);     // 水果

    private final Integer pid;
    FoodType(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return pid;
    }
}
