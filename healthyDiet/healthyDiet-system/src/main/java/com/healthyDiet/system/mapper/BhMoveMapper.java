package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhMove;

/**
 * 运动项目管理Mapper接口
 * 
 * @author MrYuan
 * @date 2023-05-16
 */
public interface BhMoveMapper 
{
    /**
     * 查询运动项目管理
     * 
     * @param id 运动项目管理主键
     * @return 运动项目管理
     */
    public BhMove selectBhMoveById(Long id);

    /**
     * 查询运动项目管理列表
     * 
     * @param bhMove 运动项目管理
     * @return 运动项目管理集合
     */
    public List<BhMove> selectBhMoveList(BhMove bhMove);

    /**
     * 新增运动项目管理
     * 
     * @param bhMove 运动项目管理
     * @return 结果
     */
    public int insertBhMove(BhMove bhMove);

    /**
     * 修改运动项目管理
     * 
     * @param bhMove 运动项目管理
     * @return 结果
     */
    public int updateBhMove(BhMove bhMove);

    /**
     * 删除运动项目管理
     * 
     * @param id 运动项目管理主键
     * @return 结果
     */
    public int deleteBhMoveById(Long id);

    /**
     * 批量删除运动项目管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhMoveByIds(Long[] ids);
}
