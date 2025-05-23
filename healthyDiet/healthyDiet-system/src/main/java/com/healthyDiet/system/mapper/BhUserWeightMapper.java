package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhUserWeight;

/**
 * 体重记录Mapper接口
 * 
 * @author bohe
 * @date 2024-03-04
 */
public interface BhUserWeightMapper 
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
     * 删除体重记录
     * 
     * @param id 体重记录主键
     * @return 结果
     */
    public int deleteBhUserWeightById(Long id);

    /**
     * 批量删除体重记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhUserWeightByIds(Long[] ids);
}
