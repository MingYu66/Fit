package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhXinli;

/**
 * 心理疏导库Mapper接口
 * 
 * @author MrYuan
 * @date 2024-05-08
 */
public interface BhXinliMapper 
{
    /**
     * 查询心理疏导库
     * 
     * @param id 心理疏导库主键
     * @return 心理疏导库
     */
    public BhXinli selectBhXinliById(Long id);

    /**
     * 查询心理疏导库列表
     * 
     * @param bhXinli 心理疏导库
     * @return 心理疏导库集合
     */
    public List<BhXinli> selectBhXinliList(BhXinli bhXinli);

    /**
     * 新增心理疏导库
     * 
     * @param bhXinli 心理疏导库
     * @return 结果
     */
    public int insertBhXinli(BhXinli bhXinli);

    /**
     * 修改心理疏导库
     * 
     * @param bhXinli 心理疏导库
     * @return 结果
     */
    public int updateBhXinli(BhXinli bhXinli);

    /**
     * 删除心理疏导库
     * 
     * @param id 心理疏导库主键
     * @return 结果
     */
    public int deleteBhXinliById(Long id);

    /**
     * 批量删除心理疏导库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhXinliByIds(Long[] ids);
}
