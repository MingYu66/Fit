package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhOrder;

/**
 * 订单Mapper接口
 * 
 * @author MrYuan
 * @date 2025-04-17
 */
public interface BhOrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public BhOrder selectBhOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param bhOrder 订单
     * @return 订单集合
     */
    public List<BhOrder> selectBhOrderList(BhOrder bhOrder);

    /**
     * 新增订单
     * 
     * @param bhOrder 订单
     * @return 结果
     */
    public int insertBhOrder(BhOrder bhOrder);

    /**
     * 修改订单
     * 
     * @param bhOrder 订单
     * @return 结果
     */
    public int updateBhOrder(BhOrder bhOrder);

    /**
     * 删除订单
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteBhOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhOrderByIds(Long[] ids);
}
