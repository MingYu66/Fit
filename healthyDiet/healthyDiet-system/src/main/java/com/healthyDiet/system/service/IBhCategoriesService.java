package com.healthyDiet.system.service;

import java.util.List;
import com.healthyDiet.system.domain.BhCategories;

/**
 * 分类管理Service接口
 * 
 * @author ruoyi
 * @date 2023-05-15
 */
public interface IBhCategoriesService 
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
     * 批量删除分类管理
     * 
     * @param ids 需要删除的分类管理主键集合
     * @return 结果
     */
    public int deleteBhCategoriesByIds(Long[] ids);

    /**
     * 删除分类管理信息
     * 
     * @param id 分类管理主键
     * @return 结果
     */
    public int deleteBhCategoriesById(Long id);
}
