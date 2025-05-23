package com.healthyDiet.system.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.healthyDiet.common.core.domain.AjaxResult;
import com.healthyDiet.common.utils.DateUtils;
import com.healthyDiet.system.domain.BhShop;
import com.healthyDiet.system.domain.BoheUser;
import com.healthyDiet.system.service.IBhShopService;
import com.healthyDiet.system.service.IBoheUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhOrderMapper;
import com.healthyDiet.system.domain.BhOrder;
import com.healthyDiet.system.service.IBhOrderService;

/**
 * 订单Service业务层处理
 * 
 * @author MrYuan
 * @date 2025-04-17
 */
@Service
public class BhOrderServiceImpl implements IBhOrderService 
{
    @Autowired
    private BhOrderMapper bhOrderMapper;

    @Autowired
    private IBhShopService bhShopService;
    @Autowired
    private IBoheUserService boheUserService;


    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public BhOrder selectBhOrderById(Long id)
    {
        return bhOrderMapper.selectBhOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param bhOrder 订单
     * @return 订单
     */
    @Override
    public List<BhOrder> selectBhOrderList(BhOrder bhOrder)
    {
        List<BhOrder> list = bhOrderMapper.selectBhOrderList(bhOrder);
        if(!list.isEmpty()){
            for(BhOrder order:list){
                order.setShop(bhShopService.selectBhShopById(order.getShopId()));
            }
        }
        return list;
    }

    /**
     * 新增订单
     * 
     * @param bhOrder 订单
     * @return 结果
     */
    @Override
    public int insertBhOrder(BhOrder bhOrder)
    {
        bhOrder.setOrderNumber(generate());
        bhOrder.setCreateTime(DateUtils.getNowDate());
        return bhOrderMapper.insertBhOrder(bhOrder);
    }

    @Override
    public Long createOrder(BhOrder bhOrder) {
        BoheUser boheUser = boheUserService.selectBoheUserById(bhOrder.getUserId());
        BhShop shop = bhShopService.selectBhShopById(bhOrder.getShopId());
        if(boheUser.getMoney() == 0 || (boheUser.getMoney()-shop.getPrice() < 0)){
           return 0L;
        }
        bhOrder.setUserName(boheUser.getUsername());
        bhOrder.setPrice(shop.getPrice());
        bhOrder.setOrderNumber(generate());
        bhOrder.setCreateTime(DateUtils.getNowDate());
        bhOrderMapper.insertBhOrder(bhOrder);
        boheUser.setMoney(boheUser.getMoney() - Integer.parseInt(bhOrder.getPrice().toString()));
        boheUserService.updateBoheUser(boheUser);
        return 1L;
    }

    // 时间格式（精确到毫秒）
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public static String generate() {
        // 时间戳部分（17位）
        String timestamp = LocalDateTime.now().format(formatter);
        // 随机数部分（4位）
        int random = ThreadLocalRandom.current().nextInt(1000, 9999);
        return timestamp + random;
    }
    /**
     * 修改订单
     * 
     * @param bhOrder 订单
     * @return 结果
     */
    @Override
    public int updateBhOrder(BhOrder bhOrder)
    {
        bhOrder.setUpdateTime(DateUtils.getNowDate());
        return bhOrderMapper.updateBhOrder(bhOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteBhOrderByIds(Long[] ids)
    {
        return bhOrderMapper.deleteBhOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteBhOrderById(Long id)
    {
        return bhOrderMapper.deleteBhOrderById(id);
    }
}
