package com.healthyDiet.system.domain;

import com.healthyDiet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;

/**
 * 商品评论对象 bh_shop_comment
 * 
 * @author ruoyi
 * @date 2025-04-18
 */
public class BhShopComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论编号 */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private Long orderId;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long shopId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setShopId(Long shopId) 
    {
        this.shopId = shopId;
    }

    public Long getShopId() 
    {
        return shopId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("shopId", getShopId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
