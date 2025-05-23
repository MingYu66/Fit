package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhShop;

/**
 * 商品Mapper接口
 * 
 * @author ruoyi
 * @date 2025-04-17
 */
public interface BhShopMapper 
{
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    public BhShop selectBhShopById(Long id);

    /**
     * 查询商品列表
     * 
     * @param bhShop 商品
     * @return 商品集合
     */
    public List<BhShop> selectBhShopList(BhShop bhShop);

    /**
     * 新增商品
     * 
     * @param bhShop 商品
     * @return 结果
     */
    public int insertBhShop(BhShop bhShop);

    /**
     * 修改商品
     * 
     * @param bhShop 商品
     * @return 结果
     */
    public int updateBhShop(BhShop bhShop);

    /**
     * 删除商品
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteBhShopById(Long id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhShopByIds(Long[] ids);
}
