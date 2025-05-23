package com.healthyDiet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

/**
 * 社区对象 bh_artice
 *
 * @author Mr Yuan
 * @date 2023-05-31
 */
public class BhArtice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long userid;
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    /** 板块 */
    @Excel(name = "板块")
    private Long type;
    /** ID */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long uid;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long count;

    /** 图片 */
    @Excel(name = "图片")
    private String imgs;

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setUid(Long uid)
    {
        this.uid = uid;
    }

    public Long getUid()
    {
        return uid;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setCount(Long count)
    {
        this.count = count;
    }

    public Long getCount()
    {
        return count;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("uid", getUid())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("count", getCount())
            .toString();
    }
}
