package com.healthyDiet.web.util;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-13 15:33
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 饮食计划生成异常
 * 继承自Exception表示需要显式处理的受检异常
 */
public class DietException extends Exception {
    // 错误代码枚举
    public enum ErrorCode {
        INSUFFICIENT_FOODS(1001, "可用食物不足"),
        CALORIE_OVERFLOW(1002, "热量计算超标"),
        NUTRIENT_IMBALANCE(1003, "营养配比失衡"),
        NUTRIENT(1004, "无法生成符合要求的饮食计划");

        private final int code;
        private final String message;

        ErrorCode(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    private final ErrorCode errorCode;
    private final Map<String, Object> context;

    // 基础构造器
    public DietException(ErrorCode errorCode) {
        super(errorCode.message);
        this.errorCode = errorCode;
        this.context = new HashMap<>();
    }


    // 带上下文的构造器
    public DietException(ErrorCode errorCode, Map<String, Object> context) {
        super(errorCode.message);
        this.errorCode = errorCode;
        this.context = new HashMap<>(context);
    }

    // 获取错误代码
    public int getErrorCode() {
        return errorCode.code;
    }

    // 获取上下文信息
    public Map<String, Object> getContext() {
        return Collections.unmodifiableMap(context);
    }
}