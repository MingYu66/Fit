package com.healthyDiet.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BoheUserMapper;
import com.healthyDiet.system.domain.BoheUser;
import com.healthyDiet.system.service.IBoheUserService;

/**
 * 薄荷用户管理Service业务层处理
 * 
 * @author MrYuan
 * @date 2023-05-16
 */
@Service
public class BoheUserServiceImpl implements IBoheUserService 
{
    @Autowired
    private BoheUserMapper boheUserMapper;

    /**
     * 查询薄荷用户管理
     * 
     * @param id 薄荷用户管理主键
     * @return 薄荷用户管理
     */
    @Override
    public BoheUser selectBoheUserById(Long id)
    {
        return boheUserMapper.selectBoheUserById(id);
    }

    /**
     * 查询薄荷用户管理列表
     * 
     * @param boheUser 薄荷用户管理
     * @return 薄荷用户管理
     */
    @Override
    public List<BoheUser> selectBoheUserList(BoheUser boheUser)
    {
        return boheUserMapper.selectBoheUserList(boheUser);
    }

    /**
     * 新增薄荷用户管理
     * 
     * @param boheUser 薄荷用户管理
     * @return 结果
     */
    @Override
    public int insertBoheUser(BoheUser boheUser)
    {
//        661 + 9.6 * 体重 + 1.72 * 身高 - 4.7 * 年龄
        boheUser.setCalory((long) (661 + 9.6*boheUser.getWeight() + 1.72*boheUser.getHeight() - 4.7*boheUser.getAge()));
        return boheUserMapper.insertBoheUser(boheUser);
    }

    /**
     * 修改薄荷用户管理
     * 
     * @param boheUser 薄荷用户管理
     * @return 结果
     */
    @Override
    public int updateBoheUser(BoheUser boheUser)
    {

        boheUser.setCalory((long) (661 + 9.6*boheUser.getWeight() + 1.72*boheUser.getHeight() -4.7*boheUser.getAge()));
        return boheUserMapper.updateBoheUser(boheUser);
    }

    /**
     * 批量删除薄荷用户管理
     * 
     * @param ids 需要删除的薄荷用户管理主键
     * @return 结果
     */
    @Override
    public int deleteBoheUserByIds(Long[] ids)
    {
        return boheUserMapper.deleteBoheUserByIds(ids);
    }

    /**
     * 删除薄荷用户管理信息
     * 
     * @param id 薄荷用户管理主键
     * @return 结果
     */
    @Override
    public int deleteBoheUserById(Long id)
    {
        return boheUserMapper.deleteBoheUserById(id);
    }



    @Override
    public List<BoheUser> selectUserType()
    {
        return boheUserMapper.selectGroupByType();
    }
}
