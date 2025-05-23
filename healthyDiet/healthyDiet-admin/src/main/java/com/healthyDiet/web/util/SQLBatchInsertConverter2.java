package com.healthyDiet.web.util;

/**
 * @version: java version 1.8
 * @Author: Mr Yuan
 * @description:
 * @date: 2024-04-06 15:52
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SQLBatchInsertConverter2 {
    public static void main(String[] args) {
        String inputFile = "H:\\tr_trademark_recordals.sql";
        String outputFile = "H:\\tr_trademark_recordals_new2000.sql";

        try {
            convertToBatchInsert(inputFile, outputFile);
            System.out.println("Conversion completed successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void convertToBatchInsert(String inputFile, String outputFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            String line;
            List<String> batchValues = new ArrayList<>();
            int count = 0;
            int i = 0;
            int batchSize = 2000; // 每次写入的批量大小
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    // 检查是否为批量插入语句
                    if (line.contains("INSERT INTO") && line.contains("VALUES")) {
                        // 提取插入值部分并去除末尾的分号
                        String valuesPart = line.substring(line.indexOf("VALUES") + 6, line.length() - 1);
                        batchValues.add(valuesPart.trim());
                    }
                }

                // 达到指定批量大小时写入文件并执行垃圾回收
                if (++count % batchSize == 0) {
                    writeBatchInsert(writer, batchValues);
                    i++;
                    System.out.println("=========CGCG===  " + i + "  ===次");
                    batchValues.clear();
                    System.gc(); // 执行垃圾回收
                }
            }

            // 将剩余数据写入文件
            if (!batchValues.isEmpty()) {
                writeBatchInsert(writer, batchValues);
            }
        }
    }

    private static void writeBatchInsert(PrintWriter writer, List<String> batchValues) {
        writer.println("INSERT INTO `tr_trademark_recordals` VALUES ");
        for (int i = 0; i < batchValues.size(); i++) {
            writer.print(batchValues.get(i));
            if (i < batchValues.size() - 1) {
                writer.println(",");
            } else {
                writer.println(";");
            }
        }
    }
}