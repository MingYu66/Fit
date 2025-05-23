package com.healthyDiet.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.healthyDiet.common.annotation.Excel;
import com.healthyDiet.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 食物管理对象 bh_food
 * 
 * @author ruoyi
 * @date 2023-05-15
 */
public class BhFood extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 食物名称 */
    @Excel(name = "食物名称")
    private String name;

    /** 缩略图 */
    @Excel(name = "缩略图")
    private String thumbImageUrl;

    /** 是否是液体 */
    @Excel(name = "是否是液体")
    private String isLiquid;

    /** 健康度/推荐食用等级 */
    @Excel(name = "健康度/推荐食用等级")
    private Long healthLight;

    /** 重量/每100g */
    @Excel(name = "重量/每100g")
    private String weight;

    /** 卡路里 */
    @Excel(name = "卡路里")
    private String calory;

    /** 脂肪 */
    @Excel(name = "脂肪")
    private String fat;

    /** 蛋白质 */
    @Excel(name = "蛋白质")
    private String protein;

    /** 膳食纤维 */
    @Excel(name = "膳食纤维")
    private String fiberDietary;

    /** 碳水化合物 */
    @Excel(name = "碳水化合物")
    private String carbohydrate;

    /** 维生素A */
    @Excel(name = "维生素A")
    private String vitaminA;

    /** 维生素B1 */
    @Excel(name = "维生素B1")
    private String thiamine;

    /** 维生素B2 */
    @Excel(name = "维生素B2")
    private String lactoflavin;

    /** 维生素C */
    @Excel(name = "维生素C")
    private String vitaminC;

    /** 维生素E */
    @Excel(name = "维生素E")
    private String vitaminE;

    /** 烟酸 */
    @Excel(name = "烟酸")
    private String niacin;

    /** 钠 */
    @Excel(name = "钠")
    private String natrium;

    /** 钙 */
    @Excel(name = "钙")
    private String calcium;

    /** 铁 */
    @Excel(name = "铁")
    private String iron;

    /** 钾 */
    @Excel(name = "钾")
    private String kalium;

    /** 碘 */
    @Excel(name = "碘")
    private String iodine;

    /** 锌 */
    @Excel(name = "锌")
    private String zinc;

    /** 硒 */
    @Excel(name = "硒")
    private String selenium;

    /** 镁
  */
    @Excel(name = "镁")
    private String magnesium;

    /** 铜 */
    @Excel(name = "铜")
    private String copper;

    /** 锰 */
    @Excel(name = "锰")
    private String manganese;

    /** 胆固醇 */
    @Excel(name = "胆固醇")
    private String cholesterol;

    /** 叶酸 */
    @Excel(name = "叶酸")
    private String folacin;

    /** 饱和脂肪 */
    @Excel(name = "饱和脂肪")
    private String saturatedFat;

    /** 糖 */
    @Excel(name = "糖")
    private String sugar;

    /** 维生素B6 */
    @Excel(name = "维生素B6")
    private String vitaminB6;

    /** 磷 */
    @Excel(name = "磷")
    private String phosphor;

    /** GI */
    @Excel(name = "GI")
    private String gi;

    /** GL */
    @Excel(name = "GL")
    private String gl;

    /** 分类id */
    @Excel(name = "分类id")
    private Long pid;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal price;
    private int recentUseCount; // 近期使用计数

    public int getRecentUseCount() {
        return recentUseCount;
    }

    public void setRecentUseCount(int recentUseCount) {
        this.recentUseCount = recentUseCount;
    }

    /** 食物内容 */
    @Excel(name = "食物内容")
    private String descInfo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setThumbImageUrl(String thumbImageUrl) 
    {
        this.thumbImageUrl = thumbImageUrl;
    }

    public String getThumbImageUrl() 
    {

        String url = "http://localhost:8081";
        try{
            if(thumbImageUrl.contains("http")){
                return thumbImageUrl;
            }
        }catch (Exception e){

            return thumbImageUrl;
        }
        return url + thumbImageUrl;

    }
    public void setIsLiquid(String isLiquid) 
    {
        this.isLiquid = isLiquid;
    }

    public String getIsLiquid() 
    {
        return isLiquid;
    }
    public void setHealthLight(Long healthLight) 
    {
        this.healthLight = healthLight;
    }

    public Long getHealthLight() 
    {
        return healthLight;
    }
    public void setWeight(String weight) 
    {
        this.weight = weight;
    }

    public String getWeight() 
    {
        return weight;
    }
    public void setCalory(String calory) 
    {
        this.calory = calory;
    }

    public String getCalory() 
    {
        return calory;
    }
    public void setFat(String fat) 
    {
        this.fat = fat;
    }

    public String getFat() 
    {
        return fat;
    }
    public void setProtein(String protein) 
    {
        this.protein = protein;
    }

    public String getProtein() 
    {
        return protein;
    }
    public void setFiberDietary(String fiberDietary) 
    {
        this.fiberDietary = fiberDietary;
    }

    public String getFiberDietary() 
    {
        return fiberDietary;
    }
    public void setCarbohydrate(String carbohydrate) 
    {
        this.carbohydrate = carbohydrate;
    }

    public String getCarbohydrate() 
    {
        return carbohydrate;
    }
    public void setVitaminA(String vitaminA) 
    {
        this.vitaminA = vitaminA;
    }

    public String getVitaminA() 
    {
        return vitaminA;
    }
    public void setThiamine(String thiamine) 
    {
        this.thiamine = thiamine;
    }

    public String getThiamine() 
    {
        return thiamine;
    }
    public void setLactoflavin(String lactoflavin) 
    {
        this.lactoflavin = lactoflavin;
    }

    public String getLactoflavin() 
    {
        return lactoflavin;
    }
    public void setVitaminC(String vitaminC) 
    {
        this.vitaminC = vitaminC;
    }

    public String getVitaminC() 
    {
        return vitaminC;
    }
    public void setVitaminE(String vitaminE) 
    {
        this.vitaminE = vitaminE;
    }

    public String getVitaminE() 
    {
        return vitaminE;
    }
    public void setNiacin(String niacin) 
    {
        this.niacin = niacin;
    }

    public String getNiacin() 
    {
        return niacin;
    }
    public void setNatrium(String natrium) 
    {
        this.natrium = natrium;
    }

    public String getNatrium() 
    {
        return natrium;
    }
    public void setCalcium(String calcium) 
    {
        this.calcium = calcium;
    }

    public String getCalcium() 
    {
        return calcium;
    }
    public void setIron(String iron) 
    {
        this.iron = iron;
    }

    public String getIron() 
    {
        return iron;
    }
    public void setKalium(String kalium) 
    {
        this.kalium = kalium;
    }

    public String getKalium() 
    {
        return kalium;
    }
    public void setIodine(String iodine) 
    {
        this.iodine = iodine;
    }

    public String getIodine() 
    {
        return iodine;
    }
    public void setZinc(String zinc) 
    {
        this.zinc = zinc;
    }

    public String getZinc() 
    {
        return zinc;
    }
    public void setSelenium(String selenium) 
    {
        this.selenium = selenium;
    }

    public String getSelenium() 
    {
        return selenium;
    }
    public void setMagnesium(String magnesium) 
    {
        this.magnesium = magnesium;
    }

    public String getMagnesium() 
    {
        return magnesium;
    }
    public void setCopper(String copper) 
    {
        this.copper = copper;
    }

    public String getCopper() 
    {
        return copper;
    }
    public void setManganese(String manganese) 
    {
        this.manganese = manganese;
    }

    public String getManganese() 
    {
        return manganese;
    }
    public void setCholesterol(String cholesterol) 
    {
        this.cholesterol = cholesterol;
    }

    public String getCholesterol() 
    {
        return cholesterol;
    }
    public void setFolacin(String folacin) 
    {
        this.folacin = folacin;
    }

    public String getFolacin() 
    {
        return folacin;
    }
    public void setSaturatedFat(String saturatedFat) 
    {
        this.saturatedFat = saturatedFat;
    }

    public String getSaturatedFat() 
    {
        return saturatedFat;
    }
    public void setSugar(String sugar) 
    {
        this.sugar = sugar;
    }

    public String getSugar() 
    {
        return sugar;
    }
    public void setVitaminB6(String vitaminB6) 
    {
        this.vitaminB6 = vitaminB6;
    }

    public String getVitaminB6() 
    {
        return vitaminB6;
    }
    public void setPhosphor(String phosphor) 
    {
        this.phosphor = phosphor;
    }

    public String getPhosphor() 
    {
        return phosphor;
    }
    public void setGi(String gi) 
    {
        this.gi = gi;
    }

    public String getGi() 
    {
        return gi;
    }
    public void setGl(String gl) 
    {
        this.gl = gl;
    }

    public String getGl() 
    {
        return gl;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("thumbImageUrl", getThumbImageUrl())
            .append("isLiquid", getIsLiquid())
            .append("healthLight", getHealthLight())
            .append("weight", getWeight())
            .append("calory", getCalory())
            .append("fat", getFat())
            .append("protein", getProtein())
            .append("fiberDietary", getFiberDietary())
            .append("carbohydrate", getCarbohydrate())
            .append("vitaminA", getVitaminA())
            .append("thiamine", getThiamine())
            .append("lactoflavin", getLactoflavin())
            .append("vitaminC", getVitaminC())
            .append("vitaminE", getVitaminE())
            .append("niacin", getNiacin())
            .append("natrium", getNatrium())
            .append("calcium", getCalcium())
            .append("iron", getIron())
            .append("kalium", getKalium())
            .append("iodine", getIodine())
            .append("zinc", getZinc())
            .append("selenium", getSelenium())
            .append("magnesium", getMagnesium())
            .append("copper", getCopper())
            .append("manganese", getManganese())
            .append("cholesterol", getCholesterol())
            .append("folacin", getFolacin())
            .append("saturatedFat", getSaturatedFat())
            .append("sugar", getSugar())
            .append("vitaminB6", getVitaminB6())
            .append("phosphor", getPhosphor())
            .append("gi", getGi())
            .append("gl", getGl())
            .append("pid", getPid())
            .toString();
    }

    private String thumb_image_url;
    private String is_liquid;
    private Long health_light;
    private String fiber_dietary;
    private String vitamin_a;
    private String vitamin_c;
    private String vitamin_e;
    private String saturated_fat;
    private String vitamin_b6;

    public void setIs_liquid(String is_liquid) {
        this.is_liquid = is_liquid;
        this.isLiquid = is_liquid;
    }

    public void setHealth_light(Long health_light) {
        this.health_light = health_light;
        this.healthLight = health_light;
    }

    public void setFiber_dietary(String fiber_dietary) {
        this.fiber_dietary = fiber_dietary;
        this.fiberDietary = fiber_dietary;
    }

    public void setVitamin_a(String vitamin_a) {
        this.vitamin_a = vitamin_a;
        this.vitaminA = vitamin_a;
    }

    public void setVitamin_c(String vitamin_c) {
        this.vitamin_c = vitamin_c;
        this.vitaminC = vitamin_c;
    }

    public void setVitamin_e(String vitamin_e) {
        this.vitamin_e = vitamin_e;
        this.vitaminE = vitamin_e;
    }

    public void setSaturated_fat(String saturated_fat) {
        this.saturated_fat = saturated_fat;
        this.saturatedFat = saturated_fat;
    }

    public void setVitamin_b6(String vitamin_b6) {
        this.vitamin_b6 = vitamin_b6;
        this.vitaminB6 = vitamin_b6;
    }

    public void setThumb_image_url(String thumb_image_url) {
        this.thumb_image_url = thumb_image_url;
        this.thumbImageUrl = thumb_image_url;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    public BhFood getFood(BhFood food) {
        food.thumbImageUrl = "0";
        food.weight = "0";
        food.calory = "0";
        food.fat = "0";
        food.protein = "0";
        food.fiberDietary = "0";
        food.carbohydrate = "0";
        food.vitaminA = "0";
        food.thiamine = "0";
        food.lactoflavin = "0";
        food.vitaminC = "0";
        food.vitaminE = "0";
        food.niacin = "0";
        food.natrium = "0";
        food.calcium = "0";
        food.iron = "0";
        food.kalium = "0";
        food.iodine = "0";
        food.zinc = "0";
        food.selenium = "0";
        food.magnesium = "0";
        food.copper = "0";
        food.manganese = "0";
        food.cholesterol = "0";
        food.folacin = "0";
        food.saturatedFat = "0";
        food.sugar = "0";
        food.vitaminB6 = "0";
        food.phosphor = "0";
        food.gi = "0";
        food.gl = "0";
        return this;
    }




}
