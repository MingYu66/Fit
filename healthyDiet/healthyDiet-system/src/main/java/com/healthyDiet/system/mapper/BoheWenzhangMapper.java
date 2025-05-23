package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BoheWenzhang;

/**
 * 健康栏目Mapper接口
 * 
 * @author bohe
 * @date 2024-03-04
 */
public interface BoheWenzhangMapper 
{
    /**
     * 查询健康栏目
     * 
     * @param id 健康栏目主键
     * @return 健康栏目
     */
    public BoheWenzhang selectBoheWenzhangById(Long id);

    /**
     * 查询健康栏目列表
     * 
     * @param boheWenzhang 健康栏目
     * @return 健康栏目集合
     */
    public List<BoheWenzhang> selectBoheWenzhangList(BoheWenzhang boheWenzhang);

    /**
     * 新增健康栏目
     * 
     * @param boheWenzhang 健康栏目
     * @return 结果
     */
    public int insertBoheWenzhang(BoheWenzhang boheWenzhang);

    /**
     * 修改健康栏目
     * 
     * @param boheWenzhang 健康栏目
     * @return 结果
     */
    public int updateBoheWenzhang(BoheWenzhang boheWenzhang);

    /**
     * 删除健康栏目
     * 
     * @param id 健康栏目主键
     * @return 结果
     */
    public int deleteBoheWenzhangById(Long id);

    /**
     * 批量删除健康栏目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBoheWenzhangByIds(Long[] ids);
}
