package com.healthyDiet.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhMoveMapper;
import com.healthyDiet.system.domain.BhMove;
import com.healthyDiet.system.service.IBhMoveService;

/**
 * 运动项目管理Service业务层处理
 * 
 * @author MrYuan
 * @date 2023-05-16
 */
@Service
public class BhMoveServiceImpl implements IBhMoveService 
{
    @Autowired
    private BhMoveMapper bhMoveMapper;

    /**
     * 查询运动项目管理
     * 
     * @param id 运动项目管理主键
     * @return 运动项目管理
     */
    @Override
    public BhMove selectBhMoveById(Long id)
    {
        return bhMoveMapper.selectBhMoveById(id);
    }

    /**
     * 查询运动项目管理列表
     * 
     * @param bhMove 运动项目管理
     * @return 运动项目管理
     */
    @Override
    public List<BhMove> selectBhMoveList(BhMove bhMove)
    {
        return bhMoveMapper.selectBhMoveList(bhMove);
    }

    /**
     * 新增运动项目管理
     * 
     * @param bhMove 运动项目管理
     * @return 结果
     */
    @Override
    public int insertBhMove(BhMove bhMove)
    {
        return bhMoveMapper.insertBhMove(bhMove);
    }

    /**
     * 修改运动项目管理
     * 
     * @param bhMove 运动项目管理
     * @return 结果
     */
    @Override
    public int updateBhMove(BhMove bhMove)
    {
        return bhMoveMapper.updateBhMove(bhMove);
    }

    /**
     * 批量删除运动项目管理
     * 
     * @param ids 需要删除的运动项目管理主键
     * @return 结果
     */
    @Override
    public int deleteBhMoveByIds(Long[] ids)
    {
        return bhMoveMapper.deleteBhMoveByIds(ids);
    }

    /**
     * 删除运动项目管理信息
     * 
     * @param id 运动项目管理主键
     * @return 结果
     */
    @Override
    public int deleteBhMoveById(Long id)
    {
        return bhMoveMapper.deleteBhMoveById(id);
    }
}
