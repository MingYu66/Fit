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
import com.healthyDiet.system.domain.BhFoodmenu;
import com.healthyDiet.system.service.IBhFoodmenuService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 推荐食谱Controller
 * 
 * @author bohe
 * @date 2024-03-17
 */
@RestController
@RequestMapping("/system/foodmenu")
public class BhFoodmenuController extends BaseController
{
    @Autowired
    private IBhFoodmenuService bhFoodmenuService;

    /**
     * 查询推荐食谱列表
     */
    @PreAuthorize("@ss.hasPermi('system:foodmenu:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhFoodmenu bhFoodmenu)
    {
        startPage();
        List<BhFoodmenu> list = bhFoodmenuService.selectBhFoodmenuList(bhFoodmenu);
        return getDataTable(list);
    }

    /**
     * 导出推荐食谱列表
     */
    @PreAuthorize("@ss.hasPermi('system:foodmenu:export')")
    @Log(title = "推荐食谱", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhFoodmenu bhFoodmenu)
    {
        List<BhFoodmenu> list = bhFoodmenuService.selectBhFoodmenuList(bhFoodmenu);
        ExcelUtil<BhFoodmenu> util = new ExcelUtil<BhFoodmenu>(BhFoodmenu.class);
        util.exportExcel(response, list, "推荐食谱数据");
    }

    /**
     * 获取推荐食谱详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:foodmenu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhFoodmenuService.selectBhFoodmenuById(id));
    }

    /**
     * 新增推荐食谱
     */
    @PreAuthorize("@ss.hasPermi('system:foodmenu:add')")
    @Log(title = "推荐食谱", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhFoodmenu bhFoodmenu)
    {
        return toAjax(bhFoodmenuService.insertBhFoodmenu(bhFoodmenu));
    }

    /**
     * 修改推荐食谱
     */
    @PreAuthorize("@ss.hasPermi('system:foodmenu:edit')")
    @Log(title = "推荐食谱", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhFoodmenu bhFoodmenu)
    {
        return toAjax(bhFoodmenuService.updateBhFoodmenu(bhFoodmenu));
    }

    /**
     * 删除推荐食谱
     */
    @PreAuthorize("@ss.hasPermi('system:foodmenu:remove')")
    @Log(title = "推荐食谱", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhFoodmenuService.deleteBhFoodmenuByIds(ids));
    }
}
