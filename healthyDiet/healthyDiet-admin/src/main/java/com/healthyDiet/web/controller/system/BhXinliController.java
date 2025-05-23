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
import com.healthyDiet.system.domain.BhXinli;
import com.healthyDiet.system.service.IBhXinliService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 心理疏导库Controller
 * 
 * @author MrYuan
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/xinli")
public class BhXinliController extends BaseController
{
    @Autowired
    private IBhXinliService bhXinliService;

    /**
     * 查询心理疏导库列表
     */
    @PreAuthorize("@ss.hasPermi('system:xinli:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhXinli bhXinli)
    {
        startPage();
        List<BhXinli> list = bhXinliService.selectBhXinliList(bhXinli);
        return getDataTable(list);
    }

    /**
     * 导出心理疏导库列表
     */
    @PreAuthorize("@ss.hasPermi('system:xinli:export')")
    @Log(title = "心理疏导库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhXinli bhXinli)
    {
        List<BhXinli> list = bhXinliService.selectBhXinliList(bhXinli);
        ExcelUtil<BhXinli> util = new ExcelUtil<BhXinli>(BhXinli.class);
        util.exportExcel(response, list, "心理疏导库数据");
    }

    /**
     * 获取心理疏导库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:xinli:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhXinliService.selectBhXinliById(id));
    }

    /**
     * 新增心理疏导库
     */
    @PreAuthorize("@ss.hasPermi('system:xinli:add')")
    @Log(title = "心理疏导库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhXinli bhXinli)
    {
        return toAjax(bhXinliService.insertBhXinli(bhXinli));
    }

    /**
     * 修改心理疏导库
     */
    @PreAuthorize("@ss.hasPermi('system:xinli:edit')")
    @Log(title = "心理疏导库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhXinli bhXinli)
    {
        return toAjax(bhXinliService.updateBhXinli(bhXinli));
    }

    /**
     * 删除心理疏导库
     */
    @PreAuthorize("@ss.hasPermi('system:xinli:remove')")
    @Log(title = "心理疏导库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhXinliService.deleteBhXinliByIds(ids));
    }
}
