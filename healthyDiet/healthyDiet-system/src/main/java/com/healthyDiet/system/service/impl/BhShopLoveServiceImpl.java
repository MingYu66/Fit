package com.healthyDiet.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhShopLoveMapper;
import com.healthyDiet.system.domain.BhShopLove;
import com.healthyDiet.system.service.IBhShopLoveService;

/**
 * 商品收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-18
 */
@Service
public class BhShopLoveServiceImpl implements IBhShopLoveService 
{
    @Autowired
    private BhShopLoveMapper bhShopLoveMapper;

    /**
     * 查询商品收藏
     * 
     * @param uid 商品收藏主键
     * @return 商品收藏
     */
    @Override
    public BhShopLove selectBhShopLoveByUid(Long uid)
    {
        return bhShopLoveMapper.selectBhShopLoveByUid(uid);
    }

    /**
     * 查询商品收藏列表
     * 
     * @param bhShopLove 商品收藏
     * @return 商品收藏
     */
    @Override
    public List<BhShopLove> selectBhShopLoveList(BhShopLove bhShopLove)
    {
        return bhShopLoveMapper.selectBhShopLoveList(bhShopLove);
    }

    /**
     * 新增商品收藏
     * 
     * @param bhShopLove 商品收藏
     * @return 结果
     */
    @Override
    public int insertBhShopLove(BhShopLove bhShopLove)
    {
        if(bhShopLoveMapper.selectBhShopLoveList(bhShopLove).isEmpty()){
            return bhShopLoveMapper.insertBhShopLove(bhShopLove);
        }else{
            return bhShopLoveMapper.deleteBhShopLoveByUidAndShopId(bhShopLove);
        }

    }

    /**
     * 修改商品收藏
     * 
     * @param bhShopLove 商品收藏
     * @return 结果
     */
    @Override
    public int updateBhShopLove(BhShopLove bhShopLove)
    {
        return bhShopLoveMapper.updateBhShopLove(bhShopLove);
    }

    /**
     * 批量删除商品收藏
     * 
     * @param uids 需要删除的商品收藏主键
     * @return 结果
     */
    @Override
    public int deleteBhShopLoveByUids(Long[] uids)
    {
        return bhShopLoveMapper.deleteBhShopLoveByUids(uids);
    }

    /**
     * 删除商品收藏信息
     * 
     * @param uid 商品收藏主键
     * @return 结果
     */
    @Override
    public int deleteBhShopLoveByUid(Long uid)
    {
        return bhShopLoveMapper.deleteBhShopLoveByUid(uid);
    }
    @Override
    public int deleteBhShopLoveByUidAndShopId(BhShopLove shopLove)
    {
        return bhShopLoveMapper.deleteBhShopLoveByUidAndShopId(shopLove);
    }
}
