package com.healthyDiet.web.controller.healthData;

import com.healthyDiet.framework.web.domain.server.Sys;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-18 19:15
 */
public class HealthDataSimulator {
    private static final Random random = new Random();
    private static final int BASE_HEART_RATE = 75;
    private static final LocalDateTime START_TIME = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

    // 通用时间戳生成器（从凌晨开始）
    private static LocalDateTime generateTimestamp(int minutesOffset) {
        return START_TIME.plusMinutes(minutesOffset);
    }

    // 生成心率数据（增强异常测试版）
    public static List<DataEntry> generateHeartRateData(boolean testMode) {
        List<DataEntry> data = new ArrayList<>();
        final int ABNORMAL_THRESHOLD = 100; // 异常阈值
        double ABNORMAL_PROBABILITY = 0.25; // 25%异常概率

        for (int i = 0; i < 1440; i++) {

            LocalDateTime timestamp = generateTimestamp(i);
            // 在下午时段（13:00-15:00）进一步提高异常概率
            if(testMode){
                int current = getCurrentMinuteIndex();
                int start = current - 29;
                int end = current + 1;
                if(i == start){
                    start = current - 29;
                }
                if (i >= start && i < end) { // 13:00-15:00
                    ABNORMAL_PROBABILITY = 0.45;
                }
            }

            // 基础波动模型（增大波动幅度）
            double phase = i / 600.0 * Math.PI; // 缩短周期为20小时
            int variation = (int) (Math.sin(phase) * 12); // 波动幅度从±8提高到±12

            // 基础值动态变化（白天更高）
            int dynamicBase = BASE_HEART_RATE + (i > 360 && i < 1080 ? 10 : 0); // 6:00-18:00基础值+10

            // 生成基准值（扩大随机范围）
            int value = dynamicBase + variation + random.nextInt(7) - 3; // 随机范围从±1扩大到±3

            // 强制注入异常（15%概率）
            if (random.nextDouble() < ABNORMAL_PROBABILITY) {
                value += random.nextInt(20) + 15; // 增加15-35的异常波动
            }

            data.add(new DataEntry(timestamp, clamp(value, 50, 150))); // 放宽上限到130
        }
        return data;
    }

    // 生成血压数据（完整24小时，每分钟一个记录）
    public static List<BloodPressureEntry> generateBloodPressureData() {
        List<BloodPressureEntry> data = new ArrayList<>();

        for (int i = 0; i < 1440; i++) {
            LocalDateTime timestamp = generateTimestamp(i);
            double phase = i / 720.0 * Math.PI;

            int systolic = 120 + (int)(Math.sin(phase) * 6) + random.nextInt(3) - 1;
            int diastolic = 80 + (int)(Math.cos(phase) * 4) + random.nextInt(2);

            data.add(new BloodPressureEntry(
                    timestamp,
                    clamp(systolic, 90, 140),
                    clamp(diastolic, 60, 90)
            ));
        }
        return data;
    }

    // 生成步数数据（完整24小时，8:00-22:00有效）
    public static List<DataEntry> generateStepData() {
        List<DataEntry> data = new ArrayList<>();
        int accumulated = 0;

        for (int i = 0; i < 1440; i++) {
            LocalDateTime timestamp = generateTimestamp(i);
            int hour = timestamp.getHour();

            // 8:00-22:00生成步数（14小时×60分钟）
            int steps = (hour >= 8 && hour < 22) ?
                    random.nextInt(20) + 5 :  // 每分钟5-25步
                    0;

            accumulated += steps;
            data.add(new DataEntry(timestamp, accumulated));
        }
        return data;
    }

    // 生成睡眠质量数据（完整24小时）
    public static List<DataEntry> generateSleepQualityData() {
        List<DataEntry> data = new ArrayList<>();

        for (int i = 0; i < 451; i++) {
            LocalDateTime timestamp = generateTimestamp(i);
            int totalMinutes = timestamp.getHour() * 60 + timestamp.getMinute();

            // 深度睡眠时段（23:00-05:00）
            int quality = (totalMinutes >= 1380 || totalMinutes < 300) ?
                    random.nextInt(5) + 95 :  // 95-99%
                    random.nextInt(15) + 85;   // 85-99%

            data.add(new DataEntry(timestamp, clamp(quality, 70, 100)));
        }
        return data;
    }

    // 数值范围限制方法
    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static void main(String[] args) {

        List<DataEntry> heartData = generateHeartRateData(true);
        generateSleepQualityData();
        generateStepData();
        generateBloodPressureData();
        int current = getCurrentMinuteIndex();
        int start = current - 29;
        int end = current + 1;


            // 正常情况
        List<Alert> alerts = HealthMonitorService.checkHeartRateAlerts(heartData.subList(start, Math.min(end, heartData.size())));


        alerts.forEach(item -> System.out.println(item.getTimestamp()+ " | "+item.getType() + " | "+ item.getMessage()));
    }


    // 获取当前时间对应的数据索引
    public static int getCurrentMinuteIndex() {
        LocalTime now = LocalTime.now();
        return now.getHour() * 60 + now.getMinute();
    }



}
