package com.healthyDiet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;

/**
 * 收藏食物对象 bh_love_food
 * 
 * @author MrYuan
 * @date 2023-05-18
 */
public class BhLoveFood extends BhFood
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long uid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long fid;

    private BhFood food;

    public BhFood getFood() {
        return food;
    }

    public void setFood(BhFood food) {
        this.food = food;
    }

    public void setUid(Long uid)
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setFid(Long fid) 
    {
        this.fid = fid;
    }

    public Long getFid() 
    {
        return fid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("fid", getFid())
            .toString();
    }
}
