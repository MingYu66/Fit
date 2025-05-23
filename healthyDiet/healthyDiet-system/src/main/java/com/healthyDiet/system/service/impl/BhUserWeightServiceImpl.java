package com.healthyDiet.system.service.impl;

import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import com.healthyDiet.system.domain.BhUserWeight;
import com.healthyDiet.system.mapper.BhUserWeightMapper;
import com.healthyDiet.system.service.IBhUserWeightService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 体重记录Service业务层处理
 * 
 * @author bohe
 * @date 2024-03-04
 */
@Service
public class BhUserWeightServiceImpl implements IBhUserWeightService
{
    @Resource
    private BhUserWeightMapper bhUserWeightMapper;

    /**
     * 查询体重记录
     * 
     * @param id 体重记录主键
     * @return 体重记录
     */
    @Override
    public BhUserWeight selectBhUserWeightById(Long id)
    {
        return bhUserWeightMapper.selectBhUserWeightById(id);
    }

    /**
     * 查询体重记录列表
     * 
     * @param bhUserWeight 体重记录
     * @return 体重记录
     */
    @Override
    public List<BhUserWeight> selectBhUserWeightList(BhUserWeight bhUserWeight)
    {
        return bhUserWeightMapper.selectBhUserWeightList(bhUserWeight);
    }

    /**
     * 新增体重记录
     * 
     * @param bhUserWeight 体重记录
     * @return 结果
     */
    @Override
    public int insertBhUserWeight(BhUserWeight bhUserWeight)
    {
        bhUserWeight.setCreateTime(DateUtils.getNowDate());
        return bhUserWeightMapper.insertBhUserWeight(bhUserWeight);
    }

    /**
     * 修改体重记录
     * 
     * @param bhUserWeight 体重记录
     * @return 结果
     */
    @Override
    public int updateBhUserWeight(BhUserWeight bhUserWeight)
    {
        return bhUserWeightMapper.updateBhUserWeight(bhUserWeight);
    }

    /**
     * 批量删除体重记录
     * 
     * @param ids 需要删除的体重记录主键
     * @return 结果
     */
    @Override
    public int deleteBhUserWeightByIds(Long[] ids)
    {
        return bhUserWeightMapper.deleteBhUserWeightByIds(ids);
    }

    /**
     * 删除体重记录信息
     * 
     * @param id 体重记录主键
     * @return 结果
     */
    @Override
    public int deleteBhUserWeightById(Long id)
    {
        return bhUserWeightMapper.deleteBhUserWeightById(id);
    }
}
