package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhCategories;
import com.healthyDiet.system.domain.BhFood;

/**
 * 分类管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-15
 */
public interface BhCategoriesMapper 
{
    /**
     * 查询分类管理
     * 
     * @param id 分类管理主键
     * @return 分类管理
     */
    public BhCategories selectBhCategoriesById(Long id);

    /**
     * 查询分类管理列表
     * 
     * @param bhCategories 分类管理
     * @return 分类管理集合
     */
    public List<BhCategories> selectBhCategoriesList(BhCategories bhCategories);

    /**
     * 新增分类管理
     * 
     * @param bhCategories 分类管理
     * @return 结果
     */
    public int insertBhCategories(BhCategories bhCategories);

    /**
     * 修改分类管理
     * 
     * @param bhCategories 分类管理
     * @return 结果
     */
    public int updateBhCategories(BhCategories bhCategories);

    /**
     * 删除分类管理
     * 
     * @param id 分类管理主键
     * @return 结果
     */
    public int deleteBhCategoriesById(Long id);

    /**
     * 批量删除分类管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhCategoriesByIds(Long[] ids);

    /**
     * 批量删除食物管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhFoodByPids(Long[] ids);
    
    /**
     * 批量新增食物管理
     * 
     * @param bhFoodList 食物管理列表
     * @return 结果
     */
    public int batchBhFood(List<BhFood> bhFoodList);
    

    /**
     * 通过分类管理主键删除食物管理信息
     * 
     * @param id 分类管理ID
     * @return 结果
     */
    public int deleteBhFoodByPid(Long id);
}
