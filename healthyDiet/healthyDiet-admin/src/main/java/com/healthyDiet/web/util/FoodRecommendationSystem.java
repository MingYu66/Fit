package com.healthyDiet.web.util;

import java.util.*;

public class FoodRecommendationSystem {
    // 假设收藏表数据存储在一个名为Collection的List中，每个元素是一个数组，包含userId、foodId和id
    private List<int[]> collection;

    public FoodRecommendationSystem(List<int[]> collection) {
        this.collection = collection;
    }




    // 计算用户相似度
    private double calculateSimilarity(int[] user1, int[] user2) {
        // 创建集合用于存储用户收藏的食物
        Set<Integer> user1Foods = new HashSet<>();
        Set<Integer> user2Foods = new HashSet<>();

        // 遍历收藏表，将用户1和用户2收藏的食物分别存入集合
        for (int[] record : collection) {
            if (record[0] == user1[0]) user1Foods.add(record[1]);
            if (record[0] == user2[0]) user2Foods.add(record[1]);
        }

        // 计算用户之间共同收藏的食物数量
        Set<Integer> commonFoods = new HashSet<>(user1Foods);
        commonFoods.retainAll(user2Foods);

        // 计算余弦相似度
        double similarity = (double) commonFoods.size() / (Math.sqrt(user1Foods.size()) * Math.sqrt(user2Foods.size()));
        System.out.println(user1Foods.size() + " - " + user2Foods.size());
        System.out.println(commonFoods.size() + " / " + (Math.sqrt(user1Foods.size()) + " * " + (Math.sqrt(user2Foods.size()))) + " = " + similarity);
        return similarity;
    }

    // 为目标用户生成推荐列表
    public List<Integer> recommend(int userId, int n) {
        // 创建Map用于存储与目标用户相似度的键值对
        Map<Integer, Double> similarityMap = new HashMap<>();
        // 创建集合用于存储目标用户已收藏的食物
        Set<Integer> targetUserFoods = new HashSet<>();

        // 遍历收藏表，将目标用户收藏的食物存入集合
        for (int[] record : collection) {
            if (record[0] == userId) targetUserFoods.add(record[1]);
        }

        // 计算目标用户与其他用户的相似度
        for (int[] record : collection) {
            if (record[0] != userId) {
                double similarity = calculateSimilarity(collection.get(userId), record);
                similarityMap.put(record[0], similarity);
            }
        }
        System.out.println(similarityMap.toString());
        // 创建List用于存储推荐的食物
        List<Integer> recommendations = new ArrayList<>();
        // 遍历相似度Map，找出相似度高的用户，并根据其收藏的食物推荐给目标用户
        for (Map.Entry<Integer, Double> entry : similarityMap.entrySet()) {
            int similarUserId = entry.getKey();
            if (entry.getValue() > 0) {
                for (int[] record : collection) {
                    if (record[0] == similarUserId && !targetUserFoods.contains(record[1])) {
                        recommendations.add(record[1]);
                        if (recommendations.size() >= n) break;
                    }
                }
            }
            if (recommendations.size() >= n) break;
        }

        return recommendations;
    }

    // 示例调用
    public static void main(String[] args) {
        // 假设收藏表数据存储在一个名为Collection的List中，每个元素是一个数组，包含userId、foodId和id
        List<int[]> collection = new ArrayList<>();
        collection.add(new int[]{1, 101, 1});
        collection.add(new int[]{1, 102, 2});
        collection.add(new int[]{2, 101, 3});
        collection.add(new int[]{2, 103, 4});
        collection.add(new int[]{3, 102, 5});
        collection.add(new int[]{3, 104, 6});

        // 创建FoodRecommendationSystem实例
        FoodRecommendationSystem recommendationSystem = new FoodRecommendationSystem(collection);

        // 调用recommend方法获取推荐结果，并打印输出
        List<Integer> recommendations = recommendationSystem.recommend(1, 3);
        System.out.println("Recommended foods for user 1: " + recommendations);
    }












}
