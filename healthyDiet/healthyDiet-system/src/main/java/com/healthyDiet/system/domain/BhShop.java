package com.healthyDiet.system.domain;

import com.healthyDiet.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;

import java.util.List;

/**
 * 商品对象 bh_shop
 * 
 * @author ruoyi
 * @date 2025-04-17
 */
public class BhShop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long id;

    /** 商品标题 */
    @Excel(name = "商品标题")
    private String title;

    /** 价格 */
    @Excel(name = "价格")
    private Long price;

    /** 商品详情 */
    @Excel(name = "商品详情")
    private String content;

    /** 视频文件 */
    @Excel(name = "视频文件")
    private String videoFile;

    /** 轮播图 */
    @Excel(name = "轮播图")
    private String imgs;

    /** 缩略图 */
    @Excel(name = "缩略图")
    private String img;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 置顶 */
    @Excel(name = "置顶")
    private Long isTop;

    private Long userId;
    private Boolean isLove;

    private List<BhShopComment> shopComment;
    public Boolean getLove() {
        return isLove;
    }

    public List<BhShopComment> getShopComment() {
        return shopComment;
    }

    public void setShopComment(List<BhShopComment> shopComment) {
        this.shopComment = shopComment;
    }

    public void setLove(Boolean love) {
        isLove = love;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setVideoFile(String videoFile) 
    {
        this.videoFile = videoFile;
    }

    public String getVideoFile() 
    {
        return videoFile;
    }
    public void setImgs(String imgs)
    {
        this.imgs = imgs;
    }

    public String getImgs() 
    {
        return imgs;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setIsTop(Long isTop) 
    {
        this.isTop = isTop;
    }

    public Long getIsTop() 
    {
        return isTop;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("price", getPrice())
            .append("content", getContent())
            .append("videoFile", getVideoFile())
            .append("imgs", getImgs())
            .append("img", getImg())
            .append("sort", getSort())
            .append("isTop", getIsTop())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
