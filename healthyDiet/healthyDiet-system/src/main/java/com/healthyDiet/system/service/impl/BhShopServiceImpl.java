package com.healthyDiet.system.service.impl;

import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhShopMapper;
import com.healthyDiet.system.domain.BhShop;
import com.healthyDiet.system.service.IBhShopService;

/**
 * 商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-17
 */
@Service
public class BhShopServiceImpl implements IBhShopService 
{
    @Autowired
    private BhShopMapper bhShopMapper;

    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    @Override
    public BhShop selectBhShopById(Long id)
    {
        return bhShopMapper.selectBhShopById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param bhShop 商品
     * @return 商品
     */
    @Override
    public List<BhShop> selectBhShopList(BhShop bhShop)
    {
        return bhShopMapper.selectBhShopList(bhShop);
    }

    /**
     * 新增商品
     * 
     * @param bhShop 商品
     * @return 结果
     */
    @Override
    public int insertBhShop(BhShop bhShop)
    {
        bhShop.setCreateTime(DateUtils.getNowDate());
        return bhShopMapper.insertBhShop(bhShop);
    }

    /**
     * 修改商品
     * 
     * @param bhShop 商品
     * @return 结果
     */
    @Override
    public int updateBhShop(BhShop bhShop)
    {
        bhShop.setUpdateTime(DateUtils.getNowDate());
        return bhShopMapper.updateBhShop(bhShop);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteBhShopByIds(Long[] ids)
    {
        return bhShopMapper.deleteBhShopByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    @Override
    public int deleteBhShopById(Long id)
    {
        return bhShopMapper.deleteBhShopById(id);
    }
}
