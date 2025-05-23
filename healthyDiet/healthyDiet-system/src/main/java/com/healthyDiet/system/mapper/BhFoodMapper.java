package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhFood;

/**
 * 食物管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-15
 */
public interface BhFoodMapper 
{
    /**
     * 查询食物管理
     * 
     * @param id 食物管理主键
     * @return 食物管理
     */
    public BhFood selectBhFoodById(Long id);

    /**
     * 查询食物管理列表
     * 
     * @param bhFood 食物管理
     * @return 食物管理集合
     */
    public List<BhFood> selectBhFoodList(BhFood bhFood);

    public List<BhFood> selectBhAIFoodList(BhFood bhFood);


    /**
     * 新增食物管理
     * 
     * @param bhFood 食物管理
     * @return 结果
     */
    public int insertBhFood(BhFood bhFood);

    /**
     * 修改食物管理
     * 
     * @param bhFood 食物管理
     * @return 结果
     */
    public int updateBhFood(BhFood bhFood);

    /**
     * 删除食物管理
     * 
     * @param id 食物管理主键
     * @return 结果
     */
    public int deleteBhFoodById(Long id);

    /**
     * 批量删除食物管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhFoodByIds(Long[] ids);
}
