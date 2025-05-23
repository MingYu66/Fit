package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhALove;

/**
 * 点赞记录Mapper接口
 * 
 * @author MrYuan
 * @date 2023-05-31
 */
public interface BhALoveMapper 
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
     * 删除点赞记录
     * 
     * @param uid 点赞记录主键
     * @return 结果
     */
    public int deleteBhALoveByUid(BhALove bhALove);

    /**
     * 批量删除点赞记录
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhALoveByUids(Long[] uids);
}
