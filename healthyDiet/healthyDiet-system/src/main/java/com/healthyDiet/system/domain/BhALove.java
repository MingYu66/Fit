package com.healthyDiet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

/**
 * 点赞记录对象 bh_a_love
 * 
 * @author MrYuan
 * @date 2023-05-31
 */
public class BhALove extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 点赞用户ID */
    @Excel(name = "点赞用户ID")
    private Long uid;

    /** 点赞文章 */
    @Excel(name = "点赞文章")
    private Long aid;

    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setAid(Long aid) 
    {
        this.aid = aid;
    }

    public Long getAid() 
    {
        return aid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("aid", getAid())
            .toString();
    }
}
