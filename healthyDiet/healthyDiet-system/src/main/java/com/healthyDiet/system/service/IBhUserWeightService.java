package com.healthyDiet.system.service;

import com.healthyDiet.system.domain.BhUserWeight;

import java.util.List;


/**
 * 体重记录Service接口
 * 
 * @author bohe
 * @date 2024-03-04
 */
public interface IBhUserWeightService 
{
    /**
     * 查询体重记录
     * 
     * @param id 体重记录主键
     * @return 体重记录
     */
    public BhUserWeight selectBhUserWeightById(Long id);

    /**
     * 查询体重记录列表
     * 
     * @param bhUserWeight 体重记录
     * @return 体重记录集合
     */
    public List<BhUserWeight> selectBhUserWeightList(BhUserWeight bhUserWeight);

    /**
     * 新增体重记录
     * 
     * @param bhUserWeight 体重记录
     * @return 结果
     */
    public int insertBhUserWeight(BhUserWeight bhUserWeight);

    /**
     * 修改体重记录
     * 
     * @param bhUserWeight 体重记录
     * @return 结果
     */
    public int updateBhUserWeight(BhUserWeight bhUserWeight);

    /**
     * 批量删除体重记录
     * 
     * @param ids 需要删除的体重记录主键集合
     * @return 结果
     */
    public int deleteBhUserWeightByIds(Long[] ids);

    /**
     * 删除体重记录信息
     * 
     * @param id 体重记录主键
     * @return 结果
     */
    public int deleteBhUserWeightById(Long id);
}
