package com.healthyDiet.system.service.impl;

import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhCaloriesMapper;
import com.healthyDiet.system.domain.BhCalories;
import com.healthyDiet.system.service.IBhCaloriesService;

/**
 * 燃烧我的卡路里Service业务层处理
 * 
 * @author MrYuan
 * @date 2023-05-17
 */
@Service
public class BhCaloriesServiceImpl implements IBhCaloriesService 
{
    @Autowired
    private BhCaloriesMapper bhCaloriesMapper;

    /**
     * 查询燃烧我的卡路里
     * 
     * @param uid 燃烧我的卡路里主键
     * @return 燃烧我的卡路里
     */
    @Override
    public BhCalories selectBhCaloriesByUid(Long uid)
    {
        return bhCaloriesMapper.selectBhCaloriesByUid(uid);
    }

    /**
     * 查询燃烧我的卡路里列表
     * 
     * @param bhCalories 燃烧我的卡路里
     * @return 燃烧我的卡路里
     */
    @Override
    public List<BhCalories> selectBhCaloriesList(BhCalories bhCalories)
    {
        return bhCaloriesMapper.selectBhCaloriesList(bhCalories);
    }

    /**
     * 新增燃烧我的卡路里
     * 
     * @param bhCalories 燃烧我的卡路里
     * @return 结果
     */
    @Override
    public int insertBhCalories(BhCalories bhCalories)
    {
        bhCalories.setCreateTime(DateUtils.getNowDate());
        return bhCaloriesMapper.insertBhCalories(bhCalories);
    }

    /**
     * 修改燃烧我的卡路里
     * 
     * @param bhCalories 燃烧我的卡路里
     * @return 结果
     */
    @Override
    public int updateBhCalories(BhCalories bhCalories)
    {
        return bhCaloriesMapper.updateBhCalories(bhCalories);
    }

    /**
     * 批量删除燃烧我的卡路里
     * 
     * @param uids 需要删除的燃烧我的卡路里主键
     * @return 结果
     */
    @Override
    public int deleteBhCaloriesByUids(Long[] uids)
    {
        return bhCaloriesMapper.deleteBhCaloriesByUids(uids);
    }

    /**
     * 删除燃烧我的卡路里信息
     * 
     * @param uid 燃烧我的卡路里主键
     * @return 结果
     */
    @Override
    public int deleteBhCaloriesByUid(Long uid)
    {
        return bhCaloriesMapper.deleteBhCaloriesByUid(uid);
    }

    @Override
    public int deleteBhCaloriesByOne(BhCalories bhCalories) {
        return bhCaloriesMapper.deleteBhCaloriesByOne(bhCalories);
    }

    @Override
    public List<BhCalories> selectBhCaloriesListByWeight(BhCalories bhCalories) {
        return bhCaloriesMapper.selectBhCaloriesListByWeight(bhCalories);
    }
}
