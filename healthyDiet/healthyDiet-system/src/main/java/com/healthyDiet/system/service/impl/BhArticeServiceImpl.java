package com.healthyDiet.system.service.impl;

import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhArticeMapper;
import com.healthyDiet.system.domain.BhArtice;
import com.healthyDiet.system.service.IBhArticeService;

/**
 * 社区Service业务层处理
 * 
 * @author Mr Yuan
 * @date 2023-05-31
 */
@Service
public class BhArticeServiceImpl implements IBhArticeService 
{
    @Autowired
    private BhArticeMapper bhArticeMapper;

    /**
     * 查询社区
     * 
     * @param id 社区主键
     * @return 社区
     */
    @Override
    public BhArtice selectBhArticeById(Long id)
    {
        return bhArticeMapper.selectBhArticeById(id);
    }

    /**
     * 查询社区列表
     * 
     * @param bhArtice 社区
     * @return 社区
     */
    @Override
    public List<BhArtice> selectBhArticeList(BhArtice bhArtice)
    {
        return bhArticeMapper.selectBhArticeList(bhArtice);
    }

    /**
     * 新增社区
     * 
     * @param bhArtice 社区
     * @return 结果
     */
    @Override
    public int insertBhArtice(BhArtice bhArtice)
    {
        bhArtice.setCreateTime(DateUtils.getNowDate());
        return bhArticeMapper.insertBhArtice(bhArtice);
    }

    /**
     * 修改社区
     * 
     * @param bhArtice 社区
     * @return 结果
     */
    @Override
    public int updateBhArtice(BhArtice bhArtice)
    {
        return bhArticeMapper.updateBhArtice(bhArtice);
    }

    /**
     * 批量删除社区
     * 
     * @param ids 需要删除的社区主键
     * @return 结果
     */
    @Override
    public int deleteBhArticeByIds(Long[] ids)
    {
        return bhArticeMapper.deleteBhArticeByIds(ids);
    }

    /**
     * 删除社区信息
     * 
     * @param id 社区主键
     * @return 结果
     */
    @Override
    public int deleteBhArticeById(Long id)
    {
        return bhArticeMapper.deleteBhArticeById(id);
    }
}
