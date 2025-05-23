package com.healthyDiet.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.healthyDiet.system.service.IBhLoveFoodService;
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
import com.healthyDiet.system.domain.BhFood;
import com.healthyDiet.system.service.IBhFoodService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 食物管理Controller
 * 
 * @author ruoyi
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/system/food")
public class BhFoodController extends BaseController
{
    @Autowired
    private IBhFoodService bhFoodService;
    @Autowired
    private IBhLoveFoodService bhLoveFoodService;

    /**
     * 查询食物管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:food:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhFood bhFood)
    {
        startPage();
        List<BhFood> list = bhFoodService.selectBhFoodList(bhFood);
        return getDataTable(list);
    }

    /**
     * 导出食物管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:food:export')")
    @Log(title = "食物管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhFood bhFood)
    {
        List<BhFood> list = bhFoodService.selectBhFoodList(bhFood);
        ExcelUtil<BhFood> util = new ExcelUtil<BhFood>(BhFood.class);
        util.exportExcel(response, list, "食物管理数据");
    }

    /**
     * 获取食物管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:food:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhFoodService.selectBhFoodById(id));
    }

    /**
     * 新增食物管理
     */
    @PreAuthorize("@ss.hasPermi('system:food:add')")
    @Log(title = "食物管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhFood bhFood)
    {
        return toAjax(bhFoodService.insertBhFood(bhFood));
    }

    /**
     * 修改食物管理
     */
    @PreAuthorize("@ss.hasPermi('system:food:edit')")
    @Log(title = "食物管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhFood bhFood)
    {
        return toAjax(bhFoodService.updateBhFood(bhFood));
    }

    /**
     * 删除食物管理
     */
    @PreAuthorize("@ss.hasPermi('system:food:remove')")
    @Log(title = "食物管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhFoodService.deleteBhFoodByIds(ids));
    }






}
