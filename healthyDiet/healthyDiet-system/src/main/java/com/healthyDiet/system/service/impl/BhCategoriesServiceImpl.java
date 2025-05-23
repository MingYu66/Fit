package com.healthyDiet.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.healthyDiet.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.healthyDiet.system.domain.BhFood;
import com.healthyDiet.system.mapper.BhCategoriesMapper;
import com.healthyDiet.system.domain.BhCategories;
import com.healthyDiet.system.service.IBhCategoriesService;

/**
 * 分类管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-15
 */
@Service
public class BhCategoriesServiceImpl implements IBhCategoriesService 
{
    @Autowired
    private BhCategoriesMapper bhCategoriesMapper;

    /**
     * 查询分类管理
     * 
     * @param id 分类管理主键
     * @return 分类管理
     */
    @Override
    public BhCategories selectBhCategoriesById(Long id)
    {
        return bhCategoriesMapper.selectBhCategoriesById(id);
    }

    /**
     * 查询分类管理列表
     * 
     * @param bhCategories 分类管理
     * @return 分类管理
     */
    @Override
    public List<BhCategories> selectBhCategoriesList(BhCategories bhCategories)
    {
        return bhCategoriesMapper.selectBhCategoriesList(bhCategories);
    }

    /**
     * 新增分类管理
     * 
     * @param bhCategories 分类管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBhCategories(BhCategories bhCategories)
    {
        int rows = bhCategoriesMapper.insertBhCategories(bhCategories);
        insertBhFood(bhCategories);
        return rows;
    }

    /**
     * 修改分类管理
     * 
     * @param bhCategories 分类管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBhCategories(BhCategories bhCategories)
    {
        bhCategoriesMapper.deleteBhFoodByPid(bhCategories.getId());
        insertBhFood(bhCategories);
        return bhCategoriesMapper.updateBhCategories(bhCategories);
    }

    /**
     * 批量删除分类管理
     * 
     * @param ids 需要删除的分类管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBhCategoriesByIds(Long[] ids)
    {
        bhCategoriesMapper.deleteBhFoodByPids(ids);
        return bhCategoriesMapper.deleteBhCategoriesByIds(ids);
    }

    /**
     * 删除分类管理信息
     * 
     * @param id 分类管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBhCategoriesById(Long id)
    {
        bhCategoriesMapper.deleteBhFoodByPid(id);
        return bhCategoriesMapper.deleteBhCategoriesById(id);
    }

    /**
     * 新增食物管理信息
     * 
     * @param bhCategories 分类管理对象
     */
    public void insertBhFood(BhCategories bhCategories)
    {
        List<BhFood> bhFoodList = bhCategories.getBhFoodList();
        Long id = bhCategories.getId();
        if (StringUtils.isNotNull(bhFoodList))
        {
            List<BhFood> list = new ArrayList<BhFood>();
            for (BhFood bhFood : bhFoodList)
            {
                bhFood.setPid(id);
                list.add(bhFood);
            }
            if (list.size() > 0)
            {
                bhCategoriesMapper.batchBhFood(list);
            }
        }
    }
}
