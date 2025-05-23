package com.healthyDiet.web.controller.healthData;

import java.util.ArrayList;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-18 19:19
 */
// 新增异常检测服务类
public class HealthMonitorService {
    private static final int HEART_RATE_THRESHOLD = 100; // 心率告警阈值
    private static final int BP_SYSTOLIC_THRESHOLD = 140; // 收缩压阈值
    private static final int DURATION_MINUTES = 5; // 持续时长要求

    // 检测心率异常（示例）
    public static List<Alert> checkHeartRateAlerts(List<DataEntry> heartRateData) {
        List<Alert> alerts = new ArrayList<>();
        int consecutiveHigh = 0;

        for (DataEntry entry : heartRateData) {
            if (entry.getValue() > HEART_RATE_THRESHOLD) {
                consecutiveHigh++;
                if (consecutiveHigh >= DURATION_MINUTES) {
                    alerts.add(new Alert(
                            "heart_rate",
                            "持续高心率告警：" + HEART_RATE_THRESHOLD + "+bpm超过" + DURATION_MINUTES + "分钟",
                            entry.getTimestamp()
                    ));
                    consecutiveHigh = 0; // 重置计数
                }
            } else {
                consecutiveHigh = 0;
            }
        }
        return alerts;
    }


}
