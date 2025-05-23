package com.healthyDiet.system.domain;

import com.healthyDiet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;

/**
 * 商品收藏对象 bh_shop_love
 * 
 * @author ruoyi
 * @date 2025-04-18
 */
public class BhShopLove extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long uid;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long shopId;

    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
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
            .append("uid", getUid())
            .append("shopId", getShopId())
            .toString();
    }
}
