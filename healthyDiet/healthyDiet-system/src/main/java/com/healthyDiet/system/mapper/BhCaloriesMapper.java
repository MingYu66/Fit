package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhCalories;

/**
 * 燃烧我的卡路里Mapper接口
 * 
 * @author MrYuan
 * @date 2023-05-17
 */
public interface BhCaloriesMapper 
{


    /**
     * 删除 记录的食物 或 运动
     * @param bhCalories
     * @return
     */
    public int deleteBhCaloriesByOne(BhCalories bhCalories);

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
    public List<BhCalories> selectBhCaloriesListByWeight(BhCalories bhCalories);



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
     * 删除燃烧我的卡路里
     * 
     * @param uid 燃烧我的卡路里主键
     * @return 结果
     */
    public int deleteBhCaloriesByUid(Long uid);

    /**
     * 批量删除燃烧我的卡路里
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhCaloriesByUids(Long[] uids);
}
