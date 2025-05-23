package com.healthyDiet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

/**
 * 推荐食物详情对象 bh_menu_info
 * 
 * @author bohe
 * @date 2024-03-17
 */
public class BhMenuInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 食物编号 */
    @Excel(name = "食物编号")
    private Long foodId;

    /** 食谱编号 */
    @Excel(name = "食谱编号")
    private Long menuId;

    /** 餐食时间 */
    @Excel(name = "餐食时间")
    private Long type;

    private BhFood food;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFoodId(Long foodId) 
    {
        this.foodId = foodId;
    }

    public Long getFoodId() 
    {
        return foodId;
    }
    public void setMenuId(Long menuId) 
    {
        this.menuId = menuId;
    }

    public Long getMenuId() 
    {
        return menuId;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    public BhFood getFood() {
        return food;
    }

    public void setFood(BhFood food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("foodId", getFoodId())
            .append("menuId", getMenuId())
            .append("type", getType())
            .toString();
    }
}
