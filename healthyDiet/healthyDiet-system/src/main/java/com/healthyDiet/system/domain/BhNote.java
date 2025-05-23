package com.healthyDiet.system.domain;

import com.healthyDiet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;

/**
 * 便签对象 bh_note
 * 
 * @author ruoyi
 * @date 2025-03-26
 */
public class BhNote extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 便签编号 */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 便签内容 */
    @Excel(name = "便签内容")
    private String content;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 心情 */
    @Excel(name = "心情")
    private String emoji;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setEmoji(String emoji) 
    {
        this.emoji = emoji;
    }

    public String getEmoji() 
    {
        return emoji;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("title", getTitle())
            .append("createTime", getCreateTime())
            .append("emoji", getEmoji())
            .toString();
    }
}
