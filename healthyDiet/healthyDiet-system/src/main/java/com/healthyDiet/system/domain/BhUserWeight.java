package com.healthyDiet.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 体重记录对象 bh_user_weight
 * 
 * @author bohe
 * @date 2024-03-04
 */
public class BhUserWeight extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;
    /** 创建时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /** 体重 */
    @Excel(name = "体重")
    private Long weight;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createTime", getCreateTime())
            .append("weight", getWeight())
            .append("userId", getUserId())
            .toString();
    }
}
