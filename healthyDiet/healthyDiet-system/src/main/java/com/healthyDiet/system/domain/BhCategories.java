package com.healthyDiet.system.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

/**
 * 分类管理对象 bh_categories
 * 
 * @author ruoyi
 * @date 2023-05-15
 */
public class BhCategories extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 图标 */
    @Excel(name = "图标")
    private String imageUrl;

    /** 二级分类数量 */
    private String subCategoryCount;

    /** 二级分类 */
    private String subCategories;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    private List<BhFood> products;

    public List<BhFood> getProducts() {
        return products;
    }

    public void setProducts(List<BhFood> products) {
        this.products = products;
    }

    /** 食物管理信息 */
    private List<BhFood> bhFoodList;

    public void setId(Long id) 
    {
        this.id = id;
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
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        try{
            if(imageUrl.contains("http")){
                return imageUrl;
            }
        }catch (Exception e){
            return imageUrl;
        }


        String url = "http://localhost:8081";
        return url + imageUrl;
    }
    public void setSubCategoryCount(String subCategoryCount) 
    {
        this.subCategoryCount = subCategoryCount;
    }

    public String getSubCategoryCount() 
    {
        return subCategoryCount;
    }
    public void setSubCategories(String subCategories) 
    {
        this.subCategories = subCategories;
    }

    public String getSubCategories() 
    {
        return subCategories;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public List<BhFood> getBhFoodList()
    {
        return bhFoodList;
    }

    public void setBhFoodList(List<BhFood> bhFoodList)
    {
        this.bhFoodList = bhFoodList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("imageUrl", getImageUrl())
            .append("subCategoryCount", getSubCategoryCount())
            .append("subCategories", getSubCategories())
            .append("description", getDescription())
            .append("bhFoodList", getBhFoodList())
            .toString();
    }
}
