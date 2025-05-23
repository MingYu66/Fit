package com.healthyDiet.web.dietPlan;

import lombok.Data;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 15:37
 */

public enum MealTime {
    MORNING("早餐",0),
    NOON("午餐",1),
    EVENING("晚餐",2);


    public final String displayName;
    public final int type;


    MealTime(String displayName, int type) {

        this.displayName = displayName;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }
}
