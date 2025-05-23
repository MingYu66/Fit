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
import com.healthyDiet.system.domain.BhArtice;
import com.healthyDiet.system.service.IBhArticeService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 社区Controller
 * 
 * @author Mr Yuan
 * @date 2023-05-31
 */
@RestController
@RequestMapping("/system/artice")
public class BhArticeController extends BaseController
{
    @Autowired
    private IBhArticeService bhArticeService;

    /**
     * 查询社区列表
     */
    @PreAuthorize("@ss.hasPermi('system:artice:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhArtice bhArtice)
    {
        startPage();
        List<BhArtice> list = bhArticeService.selectBhArticeList(bhArtice);
        return getDataTable(list);
    }

    /**
     * 导出社区列表
     */
    @PreAuthorize("@ss.hasPermi('system:artice:export')")
    @Log(title = "社区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhArtice bhArtice)
    {
        List<BhArtice> list = bhArticeService.selectBhArticeList(bhArtice);
        ExcelUtil<BhArtice> util = new ExcelUtil<BhArtice>(BhArtice.class);
        util.exportExcel(response, list, "社区数据");
    }

    /**
     * 获取社区详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:artice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhArticeService.selectBhArticeById(id));
    }

    /**
     * 新增社区
     */
    @PreAuthorize("@ss.hasPermi('system:artice:add')")
    @Log(title = "社区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhArtice bhArtice)
    {
        return toAjax(bhArticeService.insertBhArtice(bhArtice));
    }

    /**
     * 修改社区
     */
    @PreAuthorize("@ss.hasPermi('system:artice:edit')")
    @Log(title = "社区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhArtice bhArtice)
    {
        return toAjax(bhArticeService.updateBhArtice(bhArtice));
    }

    /**
     * 删除社区
     */
    @PreAuthorize("@ss.hasPermi('system:artice:remove')")
    @Log(title = "社区", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhArticeService.deleteBhArticeByIds(ids));
    }
}
