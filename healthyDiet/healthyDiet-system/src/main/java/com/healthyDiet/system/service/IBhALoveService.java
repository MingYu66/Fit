package com.healthyDiet.system.service;

import java.util.List;
import com.healthyDiet.system.domain.BhALove;

/**
 * 点赞记录Service接口
 * 
 * @author MrYuan
 * @date 2023-05-31
 */
public interface IBhALoveService 
{
    /**
     * 查询点赞记录
     * 
     * @param uid 点赞记录主键
     * @return 点赞记录
     */
    public BhALove selectBhALoveByUid(Long uid);

    /**
     * 查询点赞记录列表
     * 
     * @param bhALove 点赞记录
     * @return 点赞记录集合
     */
    public List<BhALove> selectBhALoveList(BhALove bhALove);

    /**
     * 新增点赞记录
     * 
     * @param bhALove 点赞记录
     * @return 结果
     */
    public int insertBhALove(BhALove bhALove);

    /**
     * 修改点赞记录
     * 
     * @param bhALove 点赞记录
     * @return 结果
     */
    public int updateBhALove(BhALove bhALove);

    /**
     * 批量删除点赞记录
     * 
     * @param uids 需要删除的点赞记录主键集合
     * @return 结果
     */
    public int deleteBhALoveByUids(Long[] uids);

    /**
     * 删除点赞记录信息
     * 
     * @param uid 点赞记录主键
     * @return 结果
     */
    public int deleteBhALoveByUid(BhALove bhALove);
}
