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
import com.healthyDiet.system.domain.BhJianyi;
import com.healthyDiet.system.service.IBhJianyiService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 建议反馈Controller
 * 
 * @author bohe
 * @date 2024-03-04
 */
@RestController
@RequestMapping("/system/jianyi")
public class BhJianyiController extends BaseController
{
    @Autowired
    private IBhJianyiService bhJianyiService;

    /**
     * 查询建议反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:jianyi:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhJianyi bhJianyi)
    {
        startPage();
        List<BhJianyi> list = bhJianyiService.selectBhJianyiList(bhJianyi);
        return getDataTable(list);
    }

    /**
     * 导出建议反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:jianyi:export')")
    @Log(title = "建议反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhJianyi bhJianyi)
    {
        List<BhJianyi> list = bhJianyiService.selectBhJianyiList(bhJianyi);
        ExcelUtil<BhJianyi> util = new ExcelUtil<BhJianyi>(BhJianyi.class);
        util.exportExcel(response, list, "建议反馈数据");
    }

    /**
     * 获取建议反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:jianyi:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhJianyiService.selectBhJianyiById(id));
    }

    /**
     * 新增建议反馈
     */
    @PreAuthorize("@ss.hasPermi('system:jianyi:add')")
    @Log(title = "建议反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhJianyi bhJianyi)
    {
        return toAjax(bhJianyiService.insertBhJianyi(bhJianyi));
    }

    /**
     * 修改建议反馈
     */
    @PreAuthorize("@ss.hasPermi('system:jianyi:edit')")
    @Log(title = "建议反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhJianyi bhJianyi)
    {
        return toAjax(bhJianyiService.updateBhJianyi(bhJianyi));
    }

    /**
     * 删除建议反馈
     */
    @PreAuthorize("@ss.hasPermi('system:jianyi:remove')")
    @Log(title = "建议反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhJianyiService.deleteBhJianyiByIds(ids));
    }
}
