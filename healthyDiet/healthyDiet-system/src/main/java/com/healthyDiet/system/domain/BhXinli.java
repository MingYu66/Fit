package com.healthyDiet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

/**
 * 心理疏导库对象 bh_xinli
 * 
 * @author MrYuan
 * @date 2024-05-08
 */
public class BhXinli extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 视频 */
    @Excel(name = "视频")
    private String mp4;

    /** 封面 */
    @Excel(name = "封面")
    private String img;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMp4(String mp4) 
    {
        this.mp4 = mp4;
    }

    public String getMp4() 
    {
        return mp4;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mp4", getMp4())
            .append("img", getImg())
            .append("title", getTitle())
            .append("createTime", getCreateTime())
            .toString();
    }
}
