package com.healthyDiet.web.controller.healthData;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2025-04-18 19:14
 */
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Data
@NoArgsConstructor
public class DataEntry {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private int value;



    @JsonCreator
    public DataEntry(@JsonProperty("timestamp") LocalDateTime time,
                     @JsonProperty("value") int value) {
        this.timestamp = time;
        this.value = value;
    }

}


