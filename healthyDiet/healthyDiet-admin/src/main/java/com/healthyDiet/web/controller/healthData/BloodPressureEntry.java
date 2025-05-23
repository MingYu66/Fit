package com.healthyDiet.web.controller.healthData;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-18 19:15
 */
@Getter
@Data
@NoArgsConstructor
public class BloodPressureEntry {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private int systolic;
    private int diastolic;

    public BloodPressureEntry(@JsonProperty("timestamp") LocalDateTime timestamp,
                              @JsonProperty("systolic") int systolic, @JsonProperty("diastolic")  int diastolic) {
        this.timestamp = timestamp;
        this.systolic = systolic;
        this.diastolic = diastolic;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getSystolic() { return systolic; }
    public int getDiastolic() { return diastolic; }
}
