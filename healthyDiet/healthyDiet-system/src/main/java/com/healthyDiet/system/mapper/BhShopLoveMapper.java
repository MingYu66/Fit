package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhShopLove;

/**
 * 商品收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2025-04-18
 */
public interface BhShopLoveMapper 
{
    /**
     * 查询商品收藏
     * 
     * @param uid 商品收藏主键
     * @return 商品收藏
     */
    public BhShopLove selectBhShopLoveByUid(Long uid);

    /**
     * 查询商品收藏列表
     * 
     * @param bhShopLove 商品收藏
     * @return 商品收藏集合
     */
    public List<BhShopLove> selectBhShopLoveList(BhShopLove bhShopLove);

    /**
     * 新增商品收藏
     * 
     * @param bhShopLove 商品收藏
     * @return 结果
     */
    public int insertBhShopLove(BhShopLove bhShopLove);

    /**
     * 修改商品收藏
     * 
     * @param bhShopLove 商品收藏
     * @return 结果
     */
    public int updateBhShopLove(BhShopLove bhShopLove);

    /**
     * 删除商品收藏
     * 
     * @param uid 商品收藏主键
     * @return 结果
     */
    public int deleteBhShopLoveByUid(Long uid);
    public int deleteBhShopLoveByUidAndShopId(BhShopLove bhShopLove);


    /**
     * 批量删除商品收藏
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhShopLoveByUids(Long[] uids);
}
