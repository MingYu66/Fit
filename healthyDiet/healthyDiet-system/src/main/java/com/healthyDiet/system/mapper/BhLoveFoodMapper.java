package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhLoveFood;

/**
 * 收藏食物Mapper接口
 * 
 * @author MrYuan
 * @date 2023-05-18
 */
public interface BhLoveFoodMapper 
{
    /**
     * 查询收藏食物
     * 
     * @param uid 收藏食物主键
     * @return 收藏食物
     */
    public BhLoveFood selectBhLoveFoodByUid(Long uid);
    public List<BhLoveFood>  selectBhLoveList(Long uid);
    public int deleteBhLove(BhLoveFood loveFood);

    /**
     * 查询收藏食物列表
     * 
     * @param bhLoveFood 收藏食物
     * @return 收藏食物集合
     */
    public List<BhLoveFood> selectBhLoveFoodList(BhLoveFood bhLoveFood);

    /**
     * 新增收藏食物
     * 
     * @param bhLoveFood 收藏食物
     * @return 结果
     */
    public int insertBhLoveFood(BhLoveFood bhLoveFood);

    /**
     * 修改收藏食物
     * 
     * @param bhLoveFood 收藏食物
     * @return 结果
     */
    public int updateBhLoveFood(BhLoveFood bhLoveFood);

    /**
     * 删除收藏食物
     * 
     * @param uid 收藏食物主键
     * @return 结果
     */
    public int deleteBhLoveFoodByUid(Long uid);

    /**
     * 批量删除收藏食物
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhLoveFoodByUids(Long[] uids);
}
