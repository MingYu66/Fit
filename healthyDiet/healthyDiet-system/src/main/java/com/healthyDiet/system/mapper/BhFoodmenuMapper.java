package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhFoodmenu;
import com.healthyDiet.system.domain.BhMenuInfo;

/**
 * 推荐食谱Mapper接口
 * 
 * @author bohe
 * @date 2024-03-17
 */
public interface BhFoodmenuMapper 
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
     * 删除推荐食谱
     * 
     * @param id 推荐食谱主键
     * @return 结果
     */
    public int deleteBhFoodmenuById(Long id);

    /**
     * 批量删除推荐食谱
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhFoodmenuByIds(Long[] ids);

    /**
     * 批量删除推荐食物详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhMenuInfoByMenuIds(Long[] ids);
    
    /**
     * 批量新增推荐食物详情
     * 
     * @param bhMenuInfoList 推荐食物详情列表
     * @return 结果
     */
    public int batchBhMenuInfo(List<BhMenuInfo> bhMenuInfoList);
    

    /**
     * 通过推荐食谱主键删除推荐食物详情信息
     * 
     * @param id 推荐食谱ID
     * @return 结果
     */
    public int deleteBhMenuInfoByMenuId(Long id);
}
