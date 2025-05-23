package com.healthyDiet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

/**
 * 运动项目管理对象 bh_move
 * 
 * @author MrYuan
 * @date 2023-05-16
 */
public class BhMove extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 缩略图 */
    @Excel(name = "缩略图")
    private String img;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String name;

    /** 卡路里 */
    @Excel(name = "卡路里")
    private Long calory;

    /** 计算单位 分钟 */
    @Excel(name = "计算单位 分钟")
    private Long weight;

    /** $column.columnComment */
    private Long type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        try{
            if(img.contains("http")){
                return img;
            }
        }catch (Exception e){
            return img;
        }
        String url = "http://localhost:8081";
        return url + img;


    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCalory(Long calory) 
    {
        this.calory = calory;
    }

    public Long getCalory() 
    {
        return calory;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("img", getImg())
            .append("name", getName())
            .append("calory", getCalory())
            .append("weight", getWeight())
            .append("type", getType())
            .toString();
    }
}
