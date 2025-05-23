package com.healthyDiet.system.service.impl;

import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhXinliMapper;
import com.healthyDiet.system.domain.BhXinli;
import com.healthyDiet.system.service.IBhXinliService;

/**
 * 心理疏导库Service业务层处理
 * 
 * @author MrYuan
 * @date 2024-05-08
 */
@Service
public class BhXinliServiceImpl implements IBhXinliService 
{
    @Autowired
    private BhXinliMapper bhXinliMapper;

    /**
     * 查询心理疏导库
     * 
     * @param id 心理疏导库主键
     * @return 心理疏导库
     */
    @Override
    public BhXinli selectBhXinliById(Long id)
    {
        return bhXinliMapper.selectBhXinliById(id);
    }

    /**
     * 查询心理疏导库列表
     * 
     * @param bhXinli 心理疏导库
     * @return 心理疏导库
     */
    @Override
    public List<BhXinli> selectBhXinliList(BhXinli bhXinli)
    {
        return bhXinliMapper.selectBhXinliList(bhXinli);
    }

    /**
     * 新增心理疏导库
     * 
     * @param bhXinli 心理疏导库
     * @return 结果
     */
    @Override
    public int insertBhXinli(BhXinli bhXinli)
    {
        bhXinli.setCreateTime(DateUtils.getNowDate());
        return bhXinliMapper.insertBhXinli(bhXinli);
    }

    /**
     * 修改心理疏导库
     * 
     * @param bhXinli 心理疏导库
     * @return 结果
     */
    @Override
    public int updateBhXinli(BhXinli bhXinli)
    {
        return bhXinliMapper.updateBhXinli(bhXinli);
    }

    /**
     * 批量删除心理疏导库
     * 
     * @param ids 需要删除的心理疏导库主键
     * @return 结果
     */
    @Override
    public int deleteBhXinliByIds(Long[] ids)
    {
        return bhXinliMapper.deleteBhXinliByIds(ids);
    }

    /**
     * 删除心理疏导库信息
     * 
     * @param id 心理疏导库主键
     * @return 结果
     */
    @Override
    public int deleteBhXinliById(Long id)
    {
        return bhXinliMapper.deleteBhXinliById(id);
    }
}
