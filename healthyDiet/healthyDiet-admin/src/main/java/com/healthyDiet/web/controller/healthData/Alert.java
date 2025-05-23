package com.healthyDiet.web.controller.healthData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-18 19:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    private String type;
    private String message;
    private LocalDateTime timestamp;


}
