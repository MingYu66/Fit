package com.healthyDiet.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhFoodMapper;
import com.healthyDiet.system.domain.BhFood;
import com.healthyDiet.system.service.IBhFoodService;

/**
 * 食物管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-15
 */
@Service
public class BhFoodServiceImpl implements IBhFoodService 
{
    @Autowired
    private BhFoodMapper bhFoodMapper;

    /**
     * 查询食物管理
     * 
     * @param id 食物管理主键
     * @return 食物管理
     */
    @Override
    public BhFood selectBhFoodById(Long id)
    {
        return bhFoodMapper.selectBhFoodById(id);
    }

    /**
     * 查询食物管理列表
     * 
     * @param bhFood 食物管理
     * @return 食物管理
     */
    @Override
    public List<BhFood> selectBhFoodList(BhFood bhFood)
    {
        return bhFoodMapper.selectBhFoodList(bhFood);
    }

    @Override
    public List<BhFood> selectBhAIFoodList(BhFood bhFood)
    {
        return bhFoodMapper.selectBhAIFoodList(bhFood);
    }

    /**
     * 新增食物管理
     * 
     * @param bhFood 食物管理
     * @return 结果
     */
    @Override
    public int insertBhFood(BhFood bhFood)
    {
        return bhFoodMapper.insertBhFood(bhFood);
    }

    /**
     * 修改食物管理
     * 
     * @param bhFood 食物管理
     * @return 结果
     */
    @Override
    public int updateBhFood(BhFood bhFood)
    {
        return bhFoodMapper.updateBhFood(bhFood);
    }

    /**
     * 批量删除食物管理
     * 
     * @param ids 需要删除的食物管理主键
     * @return 结果
     */
    @Override
    public int deleteBhFoodByIds(Long[] ids)
    {
        return bhFoodMapper.deleteBhFoodByIds(ids);
    }

    /**
     * 删除食物管理信息
     * 
     * @param id 食物管理主键
     * @return 结果
     */
    @Override
    public int deleteBhFoodById(Long id)
    {
        return bhFoodMapper.deleteBhFoodById(id);
    }



}
