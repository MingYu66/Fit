package com.healthyDiet.system.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

/**
 * 推荐食谱对象 bh_foodmenu
 * 
 * @author bohe
 * @date 2024-03-17
 */
public class BhFoodmenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 食谱编号 */
    private Long id;

    /** 食谱 */
    @Excel(name = "食谱")
    private String title;

    /** 推荐食物详情信息 */
    private List<BhMenuInfo> bhMenuInfoList;

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

    public List<BhMenuInfo> getBhMenuInfoList()
    {
        return bhMenuInfoList;
    }

    public void setBhMenuInfoList(List<BhMenuInfo> bhMenuInfoList)
    {
        this.bhMenuInfoList = bhMenuInfoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .append("bhMenuInfoList", getBhMenuInfoList())
            .toString();
    }
}
