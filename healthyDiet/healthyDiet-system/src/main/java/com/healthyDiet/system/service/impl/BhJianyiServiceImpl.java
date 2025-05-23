package com.healthyDiet.system.service.impl;

import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhJianyiMapper;
import com.healthyDiet.system.domain.BhJianyi;
import com.healthyDiet.system.service.IBhJianyiService;

/**
 * 建议反馈Service业务层处理
 * 
 * @author bohe
 * @date 2024-03-04
 */
@Service
public class BhJianyiServiceImpl implements IBhJianyiService 
{
    @Autowired
    private BhJianyiMapper bhJianyiMapper;

    /**
     * 查询建议反馈
     * 
     * @param id 建议反馈主键
     * @return 建议反馈
     */
    @Override
    public BhJianyi selectBhJianyiById(Long id)
    {
        return bhJianyiMapper.selectBhJianyiById(id);
    }

    /**
     * 查询建议反馈列表
     * 
     * @param bhJianyi 建议反馈
     * @return 建议反馈
     */
    @Override
    public List<BhJianyi> selectBhJianyiList(BhJianyi bhJianyi)
    {
        return bhJianyiMapper.selectBhJianyiList(bhJianyi);
    }

    /**
     * 新增建议反馈
     * 
     * @param bhJianyi 建议反馈
     * @return 结果
     */
    @Override
    public int insertBhJianyi(BhJianyi bhJianyi)
    {
        bhJianyi.setCreateTime(DateUtils.getNowDate());
        return bhJianyiMapper.insertBhJianyi(bhJianyi);
    }

    /**
     * 修改建议反馈
     * 
     * @param bhJianyi 建议反馈
     * @return 结果
     */
    @Override
    public int updateBhJianyi(BhJianyi bhJianyi)
    {
        return bhJianyiMapper.updateBhJianyi(bhJianyi);
    }

    /**
     * 批量删除建议反馈
     * 
     * @param ids 需要删除的建议反馈主键
     * @return 结果
     */
    @Override
    public int deleteBhJianyiByIds(Long[] ids)
    {
        return bhJianyiMapper.deleteBhJianyiByIds(ids);
    }

    /**
     * 删除建议反馈信息
     * 
     * @param id 建议反馈主键
     * @return 结果
     */
    @Override
    public int deleteBhJianyiById(Long id)
    {
        return bhJianyiMapper.deleteBhJianyiById(id);
    }
}
