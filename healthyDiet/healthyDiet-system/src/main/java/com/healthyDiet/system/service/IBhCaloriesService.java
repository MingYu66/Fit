package com.healthyDiet.system.service;

import java.util.List;
import com.healthyDiet.system.domain.BhCalories;

/**
 * 燃烧我的卡路里Service接口
 * 
 * @author MrYuan
 * @date 2023-05-17
 */
public interface IBhCaloriesService 
{
    /**
     * 查询燃烧我的卡路里
     * 
     * @param uid 燃烧我的卡路里主键
     * @return 燃烧我的卡路里
     */
    public BhCalories selectBhCaloriesByUid(Long uid);

    /**
     * 查询燃烧我的卡路里列表
     * 
     * @param bhCalories 燃烧我的卡路里
     * @return 燃烧我的卡路里集合
     */
    public List<BhCalories> selectBhCaloriesList(BhCalories bhCalories);

    /**
     * 新增燃烧我的卡路里
     * 
     * @param bhCalories 燃烧我的卡路里
     * @return 结果
     */
    public int insertBhCalories(BhCalories bhCalories);

    /**
     * 修改燃烧我的卡路里
     * 
     * @param bhCalories 燃烧我的卡路里
     * @return 结果
     */
    public int updateBhCalories(BhCalories bhCalories);

    /**
     * 批量删除燃烧我的卡路里
     * 
     * @param uids 需要删除的燃烧我的卡路里主键集合
     * @return 结果
     */
    public int deleteBhCaloriesByUids(Long[] uids);

    /**
     * 删除燃烧我的卡路里信息
     * 
     * @param uid 燃烧我的卡路里主键
     * @return 结果
     */
    public int deleteBhCaloriesByUid(Long uid);
    public int deleteBhCaloriesByOne(BhCalories bhCalories);

    List<BhCalories> selectBhCaloriesListByWeight(BhCalories bhCalories);
}
