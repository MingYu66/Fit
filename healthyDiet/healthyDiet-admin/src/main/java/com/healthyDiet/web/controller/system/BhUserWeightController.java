package com.healthyDiet.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.healthyDiet.common.annotation.Log;
import com.healthyDiet.common.core.controller.BaseController;
import com.healthyDiet.common.core.domain.AjaxResult;
import com.healthyDiet.common.enums.BusinessType;
import com.healthyDiet.system.domain.BhUserWeight;
import com.healthyDiet.system.service.IBhUserWeightService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 体重/血糖Controller
 * 
 * @author bohe
 * @date 2024-03-04
 */
@RestController
@RequestMapping("/system/weight")
public class BhUserWeightController extends BaseController
{
    @Autowired
    private IBhUserWeightService bhUserWeightService;

    /**
     * 查询体重/血糖列表
     */
    @PreAuthorize("@ss.hasPermi('system:weight:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhUserWeight bhUserWeight)
    {
        startPage();
        List<BhUserWeight> list = bhUserWeightService.selectBhUserWeightList(bhUserWeight);
        return getDataTable(list);
    }



    /**
     * 获取用户体重/血糖
     * @param c
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:boheUser:edit')")
    @Log(title = "体重/血糖", businessType = BusinessType.EXPORT)
    @PostMapping(value = "/getUserWeight")
    public AjaxResult getUserWeight(@RequestBody BhUserWeight c){
        List<BhUserWeight> userWeights =  bhUserWeightService.selectBhUserWeightList(c);
        return AjaxResult.success(userWeights);
    }







    /**
     * 导出体重/血糖列表
     */
    @PreAuthorize("@ss.hasPermi('system:weight:export')")
    @Log(title = "体重/血糖", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhUserWeight bhUserWeight)
    {
        List<BhUserWeight> list = bhUserWeightService.selectBhUserWeightList(bhUserWeight);
        ExcelUtil<BhUserWeight> util = new ExcelUtil<BhUserWeight>(BhUserWeight.class);
        util.exportExcel(response, list, "体重/血糖数据");
    }

    /**
     * 获取体重/血糖详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:weight:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhUserWeightService.selectBhUserWeightById(id));
    }

    /**
     * 新增体重/血糖
     */
    @PreAuthorize("@ss.hasPermi('system:weight:add')")
    @Log(title = "体重/血糖", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhUserWeight bhUserWeight)
    {
        return toAjax(bhUserWeightService.insertBhUserWeight(bhUserWeight));
    }

    /**
     * 修改体重/血糖
     */
    @PreAuthorize("@ss.hasPermi('system:weight:edit')")
    @Log(title = "体重/血糖", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhUserWeight bhUserWeight)
    {
        return toAjax(bhUserWeightService.updateBhUserWeight(bhUserWeight));
    }

    /**
     * 删除体重/血糖
     */
    @PreAuthorize("@ss.hasPermi('system:weight:remove')")
    @Log(title = "体重/血糖", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhUserWeightService.deleteBhUserWeightByIds(ids));
    }
}
