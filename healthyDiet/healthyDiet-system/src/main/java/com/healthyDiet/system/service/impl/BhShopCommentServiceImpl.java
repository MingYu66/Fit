package com.healthyDiet.system.service.impl;

import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhShopCommentMapper;
import com.healthyDiet.system.domain.BhShopComment;
import com.healthyDiet.system.service.IBhShopCommentService;

/**
 * 商品评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-18
 */
@Service
public class BhShopCommentServiceImpl implements IBhShopCommentService 
{
    @Autowired
    private BhShopCommentMapper bhShopCommentMapper;

    /**
     * 查询商品评论
     * 
     * @param id 商品评论主键
     * @return 商品评论
     */
    @Override
    public BhShopComment selectBhShopCommentById(Long id)
    {
        return bhShopCommentMapper.selectBhShopCommentById(id);
    }

    /**
     * 查询商品评论列表
     * 
     * @param bhShopComment 商品评论
     * @return 商品评论
     */
    @Override
    public List<BhShopComment> selectBhShopCommentList(BhShopComment bhShopComment)
    {
        return bhShopCommentMapper.selectBhShopCommentList(bhShopComment);
    }

    /**
     * 新增商品评论
     * 
     * @param bhShopComment 商品评论
     * @return 结果
     */
    @Override
    public int insertBhShopComment(BhShopComment bhShopComment)
    {
        bhShopComment.setCreateTime(DateUtils.getNowDate());
        return bhShopCommentMapper.insertBhShopComment(bhShopComment);
    }

    /**
     * 修改商品评论
     * 
     * @param bhShopComment 商品评论
     * @return 结果
     */
    @Override
    public int updateBhShopComment(BhShopComment bhShopComment)
    {
        return bhShopCommentMapper.updateBhShopComment(bhShopComment);
    }

    /**
     * 批量删除商品评论
     * 
     * @param ids 需要删除的商品评论主键
     * @return 结果
     */
    @Override
    public int deleteBhShopCommentByIds(Long[] ids)
    {
        return bhShopCommentMapper.deleteBhShopCommentByIds(ids);
    }

    /**
     * 删除商品评论信息
     * 
     * @param id 商品评论主键
     * @return 结果
     */
    @Override
    public int deleteBhShopCommentById(Long id)
    {
        return bhShopCommentMapper.deleteBhShopCommentById(id);
    }
}
