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
import com.healthyDiet.system.domain.BhCalories;
import com.healthyDiet.system.service.IBhCaloriesService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 燃烧我的卡路里Controller
 * 
 * @author MrYuan
 * @date 2023-05-17
 */
@RestController
@RequestMapping("/system/bhCalories")
public class BhCaloriesController extends BaseController
{
    @Autowired
    private IBhCaloriesService bhCaloriesService;

    /**
     * 查询燃烧我的卡路里列表
     */
    @PreAuthorize("@ss.hasPermi('system:bhCalories:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhCalories bhCalories)
    {
        startPage();
        List<BhCalories> list = bhCaloriesService.selectBhCaloriesList(bhCalories);
        return getDataTable(list);
    }

    /**
     * 导出燃烧我的卡路里列表
     */
    @PreAuthorize("@ss.hasPermi('system:bhCalories:export')")
    @Log(title = "燃烧我的卡路里", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhCalories bhCalories)
    {
        List<BhCalories> list = bhCaloriesService.selectBhCaloriesList(bhCalories);
        ExcelUtil<BhCalories> util = new ExcelUtil<BhCalories>(BhCalories.class);
        util.exportExcel(response, list, "燃烧我的卡路里数据");
    }

    /**
     * 获取燃烧我的卡路里详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bhCalories:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") Long uid)
    {
        return success(bhCaloriesService.selectBhCaloriesByUid(uid));
    }

    /**
     * 新增燃烧我的卡路里
     */
    @PreAuthorize("@ss.hasPermi('system:bhCalories:add')")
    @Log(title = "燃烧我的卡路里", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhCalories bhCalories)
    {
        return toAjax(bhCaloriesService.insertBhCalories(bhCalories));
    }

    /**
     * 修改燃烧我的卡路里
     */
    @PreAuthorize("@ss.hasPermi('system:bhCalories:edit')")
    @Log(title = "燃烧我的卡路里", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhCalories bhCalories)
    {
        return toAjax(bhCaloriesService.updateBhCalories(bhCalories));
    }

    /**
     * 删除燃烧我的卡路里
     */
    @PreAuthorize("@ss.hasPermi('system:bhCalories:remove')")
    @Log(title = "燃烧我的卡路里", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable Long[] uids)
    {
        return toAjax(bhCaloriesService.deleteBhCaloriesByUids(uids));
    }
}
