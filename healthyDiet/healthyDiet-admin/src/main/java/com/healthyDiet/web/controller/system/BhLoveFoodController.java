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
import com.healthyDiet.system.domain.BhLoveFood;
import com.healthyDiet.system.service.IBhLoveFoodService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 收藏食物Controller
 * 
 * @author MrYuan
 * @date 2023-05-18
 */
@RestController
@RequestMapping("/system/lovefood")
public class BhLoveFoodController extends BaseController
{
    @Autowired
    private IBhLoveFoodService bhLoveFoodService;

    /**
     * 查询收藏食物列表
     */
    @PreAuthorize("@ss.hasPermi('system:lovefood:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhLoveFood bhLoveFood)
    {
        startPage();
        List<BhLoveFood> list = bhLoveFoodService.selectBhLoveFoodList(bhLoveFood);
        return getDataTable(list);
    }

    /**
     * 导出收藏食物列表
     */
    @PreAuthorize("@ss.hasPermi('system:lovefood:export')")
    @Log(title = "收藏食物", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhLoveFood bhLoveFood)
    {
        List<BhLoveFood> list = bhLoveFoodService.selectBhLoveFoodList(bhLoveFood);
        ExcelUtil<BhLoveFood> util = new ExcelUtil<BhLoveFood>(BhLoveFood.class);
        util.exportExcel(response, list, "收藏食物数据");
    }

    /**
     * 获取收藏食物详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:lovefood:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") Long uid)
    {
        return success(bhLoveFoodService.selectBhLoveFoodByUid(uid));
    }

    /**
     * 新增收藏食物
     */
    @PreAuthorize("@ss.hasPermi('system:lovefood:add')")
    @Log(title = "收藏食物", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhLoveFood bhLoveFood)
    {
        return toAjax(bhLoveFoodService.insertBhLoveFood(bhLoveFood));
    }

    /**
     * 修改收藏食物
     */
    @PreAuthorize("@ss.hasPermi('system:lovefood:edit')")
    @Log(title = "收藏食物", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhLoveFood bhLoveFood)
    {
        return toAjax(bhLoveFoodService.updateBhLoveFood(bhLoveFood));
    }

    /**
     * 删除收藏食物
     */
    @PreAuthorize("@ss.hasPermi('system:lovefood:remove')")
    @Log(title = "收藏食物", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable Long[] uids)
    {
        return toAjax(bhLoveFoodService.deleteBhLoveFoodByUids(uids));
    }
}
