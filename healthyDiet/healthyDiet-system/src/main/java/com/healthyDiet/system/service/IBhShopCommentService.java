package com.healthyDiet.system.service;

import java.util.List;
import com.healthyDiet.system.domain.BhShopComment;

/**
 * 商品评论Service接口
 * 
 * @author ruoyi
 * @date 2025-04-18
 */
public interface IBhShopCommentService 
{
    /**
     * 查询商品评论
     * 
     * @param id 商品评论主键
     * @return 商品评论
     */
    public BhShopComment selectBhShopCommentById(Long id);

    /**
     * 查询商品评论列表
     * 
     * @param bhShopComment 商品评论
     * @return 商品评论集合
     */
    public List<BhShopComment> selectBhShopCommentList(BhShopComment bhShopComment);

    /**
     * 新增商品评论
     * 
     * @param bhShopComment 商品评论
     * @return 结果
     */
    public int insertBhShopComment(BhShopComment bhShopComment);

    /**
     * 修改商品评论
     * 
     * @param bhShopComment 商品评论
     * @return 结果
     */
    public int updateBhShopComment(BhShopComment bhShopComment);

    /**
     * 批量删除商品评论
     * 
     * @param ids 需要删除的商品评论主键集合
     * @return 结果
     */
    public int deleteBhShopCommentByIds(Long[] ids);

    /**
     * 删除商品评论信息
     * 
     * @param id 商品评论主键
     * @return 结果
     */
    public int deleteBhShopCommentById(Long id);
}
