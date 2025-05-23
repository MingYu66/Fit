package com.healthyDiet.system.service.impl;

import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BoheWenzhangMapper;
import com.healthyDiet.system.domain.BoheWenzhang;
import com.healthyDiet.system.service.IBoheWenzhangService;

/**
 * 健康栏目Service业务层处理
 * 
 * @author bohe
 * @date 2024-03-04
 */
@Service
public class BoheWenzhangServiceImpl implements IBoheWenzhangService 
{
    @Autowired
    private BoheWenzhangMapper boheWenzhangMapper;

    /**
     * 查询健康栏目
     * 
     * @param id 健康栏目主键
     * @return 健康栏目
     */
    @Override
    public BoheWenzhang selectBoheWenzhangById(Long id)
    {
        return boheWenzhangMapper.selectBoheWenzhangById(id);
    }

    /**
     * 查询健康栏目列表
     * 
     * @param boheWenzhang 健康栏目
     * @return 健康栏目
     */
    @Override
    public List<BoheWenzhang> selectBoheWenzhangList(BoheWenzhang boheWenzhang)
    {
        return boheWenzhangMapper.selectBoheWenzhangList(boheWenzhang);
    }

    /**
     * 新增健康栏目
     * 
     * @param boheWenzhang 健康栏目
     * @return 结果
     */
    @Override
    public int insertBoheWenzhang(BoheWenzhang boheWenzhang)
    {
        boheWenzhang.setCreateTime(DateUtils.getNowDate());
        return boheWenzhangMapper.insertBoheWenzhang(boheWenzhang);
    }

    /**
     * 修改健康栏目
     * 
     * @param boheWenzhang 健康栏目
     * @return 结果
     */
    @Override
    public int updateBoheWenzhang(BoheWenzhang boheWenzhang)
    {
        return boheWenzhangMapper.updateBoheWenzhang(boheWenzhang);
    }

    /**
     * 批量删除健康栏目
     * 
     * @param ids 需要删除的健康栏目主键
     * @return 结果
     */
    @Override
    public int deleteBoheWenzhangByIds(Long[] ids)
    {
        return boheWenzhangMapper.deleteBoheWenzhangByIds(ids);
    }

    /**
     * 删除健康栏目信息
     * 
     * @param id 健康栏目主键
     * @return 结果
     */
    @Override
    public int deleteBoheWenzhangById(Long id)
    {
        return boheWenzhangMapper.deleteBoheWenzhangById(id);
    }
}
