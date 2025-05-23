package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BoheUser;

/**
 * 薄荷用户管理Mapper接口
 * 
 * @author MrYuan
 * @date 2023-05-16
 */
public interface BoheUserMapper 
{
    /**
     * 查询薄荷用户管理
     * 
     * @param id 薄荷用户管理主键
     * @return 薄荷用户管理
     */
    public BoheUser selectBoheUserById(Long id);

    public List<BoheUser> selectGroupByType();



    /**
     * 查询薄荷用户管理列表
     * 
     * @param boheUser 薄荷用户管理
     * @return 薄荷用户管理集合
     */
    public List<BoheUser> selectBoheUserList(BoheUser boheUser);

    /**
     * 新增薄荷用户管理
     * 
     * @param boheUser 薄荷用户管理
     * @return 结果
     */
    public int insertBoheUser(BoheUser boheUser);

    /**
     * 修改薄荷用户管理
     * 
     * @param boheUser 薄荷用户管理
     * @return 结果
     */
    public int updateBoheUser(BoheUser boheUser);

    /**
     * 删除薄荷用户管理
     * 
     * @param id 薄荷用户管理主键
     * @return 结果
     */
    public int deleteBoheUserById(Long id);

    /**
     * 批量删除薄荷用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBoheUserByIds(Long[] ids);
}
