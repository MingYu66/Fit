package com.healthyDiet.system.service;

import java.util.List;
import com.healthyDiet.system.domain.BhLoveFood;

/**
 * 收藏食物Service接口
 * 
 * @author MrYuan
 * @date 2023-05-18
 */
public interface IBhLoveFoodService 
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
     * 批量删除收藏食物
     * 
     * @param uids 需要删除的收藏食物主键集合
     * @return 结果
     */
    public int deleteBhLoveFoodByUids(Long[] uids);

    /**
     * 删除收藏食物信息
     * 
     * @param uid 收藏食物主键
     * @return 结果
     */
    public int deleteBhLoveFoodByUid(Long uid);
}
