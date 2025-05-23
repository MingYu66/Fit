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
import com.healthyDiet.system.domain.BhCategories;
import com.healthyDiet.system.service.IBhCategoriesService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 分类管理Controller
 * 
 * @author ruoyi
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/system/categories")
public class BhCategoriesController extends BaseController
{
    @Autowired
    private IBhCategoriesService bhCategoriesService;

    /**
     * 查询分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:categories:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhCategories bhCategories)
    {
        startPage();
        List<BhCategories> list = bhCategoriesService.selectBhCategoriesList(bhCategories);
        return getDataTable(list);
    }

    /**
     * 导出分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:categories:export')")
    @Log(title = "分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhCategories bhCategories)
    {
        List<BhCategories> list = bhCategoriesService.selectBhCategoriesList(bhCategories);
        ExcelUtil<BhCategories> util = new ExcelUtil<BhCategories>(BhCategories.class);
        util.exportExcel(response, list, "分类管理数据");
    }

    /**
     * 获取分类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:categories:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhCategoriesService.selectBhCategoriesById(id));
    }

    /**
     * 新增分类管理
     */
    @PreAuthorize("@ss.hasPermi('system:categories:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhCategories bhCategories)
    {
        return toAjax(bhCategoriesService.insertBhCategories(bhCategories));
    }

    /**
     * 修改分类管理
     */
    @PreAuthorize("@ss.hasPermi('system:categories:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhCategories bhCategories)
    {
        return toAjax(bhCategoriesService.updateBhCategories(bhCategories));
    }

    /**
     * 删除分类管理
     */
    @PreAuthorize("@ss.hasPermi('system:categories:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhCategoriesService.deleteBhCategoriesByIds(ids));
    }






}
