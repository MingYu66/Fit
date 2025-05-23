package com.healthyDiet.system.service;

import java.util.List;
import com.healthyDiet.system.domain.BhFoodmenu;

/**
 * 推荐食谱Service接口
 * 
 * @author bohe
 * @date 2024-03-17
 */
public interface IBhFoodmenuService 
{
    /**
     * 查询推荐食谱
     * 
     * @param id 推荐食谱主键
     * @return 推荐食谱
     */
    public BhFoodmenu selectBhFoodmenuById(Long id);

    /**
     * 查询推荐食谱列表
     * 
     * @param bhFoodmenu 推荐食谱
     * @return 推荐食谱集合
     */
    public List<BhFoodmenu> selectBhFoodmenuList(BhFoodmenu bhFoodmenu);

    /**
     * 新增推荐食谱
     * 
     * @param bhFoodmenu 推荐食谱
     * @return 结果
     */
    public int insertBhFoodmenu(BhFoodmenu bhFoodmenu);

    /**
     * 修改推荐食谱
     * 
     * @param bhFoodmenu 推荐食谱
     * @return 结果
     */
    public int updateBhFoodmenu(BhFoodmenu bhFoodmenu);

    /**
     * 批量删除推荐食谱
     * 
     * @param ids 需要删除的推荐食谱主键集合
     * @return 结果
     */
    public int deleteBhFoodmenuByIds(Long[] ids);

    /**
     * 删除推荐食谱信息
     * 
     * @param id 推荐食谱主键
     * @return 结果
     */
    public int deleteBhFoodmenuById(Long id);
}
