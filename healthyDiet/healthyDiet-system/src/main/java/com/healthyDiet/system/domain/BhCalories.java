package com.healthyDiet.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 燃烧我的卡路里对象 bh_calories
 * 
 * @author MrYuan
 * @date 2023-05-17
 */
public class BhCalories extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Excel(name = "用户id")
    private Long uid;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String cName;

    /** 项目id */
    @Excel(name = "项目id")
    private Long cId;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private Long cType;

    /** 项目图片 */
    @Excel(name = "项目图片")
    private String cImg;

    /** 重量或时间 */
    @Excel(name = "重量或时间")
    private Long cNum;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private Long cWeight;

    /** 消耗卡路里 */
    @Excel(name = "消耗卡路里")
    private Long cValue;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUid(Long uid)
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setcName(String cName) 
    {
        this.cName = cName;
    }

    public String getcName() 
    {
        return cName;
    }
    public void setcId(Long cId) 
    {
        this.cId = cId;
    }

    public Long getcId() 
    {
        return cId;
    }
    public void setcType(Long cType) 
    {
        this.cType = cType;
    }

    public Long getcType() 
    {
        return cType;
    }
    public void setcImg(String cImg) 
    {
        this.cImg = cImg;
    }

    public String getcImg() 
    {
        return cImg;
    }
    public void setcNum(Long cNum) 
    {
        this.cNum = cNum;
    }

    public Long getcNum() 
    {
        return cNum;
    }
    public void setcWeight(Long cWeight) 
    {
        this.cWeight = cWeight;
    }

    public Long getcWeight() 
    {
        return cWeight;
    }
    public void setcValue(Long cValue) 
    {
        this.cValue = cValue;
    }

    public Long getcValue() 
    {
        return cValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("cName", getcName())
            .append("cId", getcId())
            .append("cType", getcType())
            .append("cImg", getcImg())
            .append("cNum", getcNum())
            .append("cWeight", getcWeight())
            .append("cValue", getcValue())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
