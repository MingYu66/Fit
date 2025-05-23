package com.healthyDiet.system.domain;

import com.healthyDiet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;

/**
 * 订单对象 bh_order
 * 
 * @author MrYuan
 * @date 2025-04-17
 */
public class BhOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNumber;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 积分 */
    @Excel(name = "积分")
    private Long price;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long shopId;

    private BhShop shop;

    private Boolean isComment;

    public Boolean getComment() {
        return isComment;
    }

    public void setComment(Boolean comment) {
        isComment = comment;
    }

    public BhShop getShop() {
        return shop;
    }

    public void setShop(BhShop shop) {
        this.shop = shop;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderNumber(String orderNumber) 
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() 
    {
        return orderNumber;
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
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setShopId(Long shopId) 
    {
        this.shopId = shopId;
    }

    public Long getShopId() 
    {
        return shopId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNumber", getOrderNumber())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("price", getPrice())
            .append("shopId", getShopId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
