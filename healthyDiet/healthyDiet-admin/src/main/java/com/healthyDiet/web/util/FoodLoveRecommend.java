package com.healthyDiet.web.util;

import com.healthyDiet.system.domain.BhLoveFood;
import com.healthyDiet.system.domain.BhLoveFood;

import java.util.*;

public class FoodLoveRecommend {
    private static List<BhLoveFood> calories;

    private static List<BhLoveFood> loveFood;

    public FoodLoveRecommend(List<BhLoveFood> calories){
        this.calories = calories;
    }



    public static double sumilarityCalculation(Long userId, BhLoveFood caloriesB){

        Set<Long> ALove = new HashSet<>();
        Set<Long> BLove = new HashSet<>();
        for (BhLoveFood item: calories
             ) {
            if(item.getUid().equals(userId)) ALove.add(item.getFid());
            if(item.getUid().equals(caloriesB.getUid())) BLove.add(item.getFid());
        }

        Set<Long> aAndBLove = new HashSet<>(ALove);
        aAndBLove.retainAll(BLove);
        double d = (double)aAndBLove.size() / (Math.sqrt(ALove.size()) * Math.sqrt(BLove.size()));

        return d;

    }


    public static List<Long> recommed(Long userId, int n){

        //存储相似度数据，{用户ID：相似度值}
        Map<Long ,Double> stringDoubleMap = new HashMap<>();
        //存储当前用户都吃过那些食物
        Set<Long> userLove = new HashSet<>();

        for (BhLoveFood item: calories
             ) {
            if (item.getUid().equals(userId)) userLove.add(item.getFid());
        }
        for (BhLoveFood item:calories
             ) {
            if(!(item.getUid().equals(userId)) && !(stringDoubleMap.containsKey(item.getUid()))){
               double c =  sumilarityCalculation(userId,item);
                stringDoubleMap.put(item.getUid(),c);
            }
        }
        List<Map.Entry<Long,Double>> entryList = new ArrayList<>(stringDoubleMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Long> recommends = new ArrayList<>();
        for (Map.Entry<Long,Double> item:entryList
             ) {
            System.out.println( item.getKey() + "用户相似度："+item.getValue());
            if(item.getValue() > 0){
                Long bUid = item.getKey();
                //循环总表
                for (BhLoveFood c:calories
                     ) {
                    //如果 item 对象不是 查找用户，并且该食物 我没有吃过，那就加入我的推荐列表
                    if(c.getUid().equals(bUid) && !(userLove.contains(c.getFid()))){
                        recommends.add(c.getFid());
                        if (recommends.size() >= n) break;
                    }
                }
            }
            if (recommends.size() >= n) break;

        }
        System.out.println("Recommended foods for user : " + recommends);
        return recommends;
    }

    public static List<Long> getLoveFoodRecommd(Long userId,List<BhLoveFood> calories){

        FoodLoveRecommend foodRecommend = new FoodLoveRecommend(calories);

        return recommed(userId,4);

    }






}
