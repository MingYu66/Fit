package com.healthyDiet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

/**
 * 薄荷用户管理对象 bohe_user
 *
 * @author MrYuan
 * @date 2024-03-05
 */
public class BoheUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户编号 */
    private Long id;

    /** 账号 */
    @Excel(name = "账号")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 体重 */
    @Excel(name = "体重")
    private Long weight;

    /** 身高 */
    @Excel(name = "身高")
    private Long height;

    /** 每日应摄入卡路里 */
    @Excel(name = "每日应摄入卡路里")
    private Long calory;

    /** 症状类型 */
    @Excel(name = "症状类型")
    private String type;

    private Integer money;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }
    public void setAge(Long age)
    {
        this.age = age;
    }

    public Long getAge()
    {
        return age;
    }
    public void setWeight(Long weight)
    {
        this.weight = weight;
    }

    public Long getWeight()
    {
        return weight;
    }
    public void setHeight(Long height)
    {
        this.height = height;
    }

    public Long getHeight()
    {
        return height;
    }
    public void setCalory(Long calory)
    {
        this.calory = calory;
    }

    public Long getCalory()
    {
        return calory;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("username", getUsername())
                .append("password", getPassword())
                .append("name", getName())
                .append("sex", getSex())
                .append("age", getAge())
                .append("weight", getWeight())
                .append("height", getHeight())
                .append("calory", getCalory())
                .append("type", getType())
                .toString();
    }
}
