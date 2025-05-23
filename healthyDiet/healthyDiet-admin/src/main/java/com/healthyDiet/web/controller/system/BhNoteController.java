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
import com.healthyDiet.system.domain.BhNote;
import com.healthyDiet.system.service.IBhNoteService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 便签Controller
 * 
 * @author ruoyi
 * @date 2025-03-26
 */
@RestController
@RequestMapping("/system/note")
public class BhNoteController extends BaseController
{
    @Autowired
    private IBhNoteService bhNoteService;

    /**
     * 查询便签列表
     */
    @PreAuthorize("@ss.hasPermi('system:note:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhNote bhNote)
    {
        startPage();
        List<BhNote> list = bhNoteService.selectBhNoteList(bhNote);
        return getDataTable(list);
    }

    /**
     * 导出便签列表
     */
    @PreAuthorize("@ss.hasPermi('system:note:export')")
    @Log(title = "便签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhNote bhNote)
    {
        List<BhNote> list = bhNoteService.selectBhNoteList(bhNote);
        ExcelUtil<BhNote> util = new ExcelUtil<BhNote>(BhNote.class);
        util.exportExcel(response, list, "便签数据");
    }

    /**
     * 获取便签详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:note:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhNoteService.selectBhNoteById(id));
    }

    /**
     * 新增便签
     */
    @PreAuthorize("@ss.hasPermi('system:note:add')")
    @Log(title = "便签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhNote bhNote)
    {
        return toAjax(bhNoteService.insertBhNote(bhNote));
    }

    /**
     * 修改便签
     */
    @PreAuthorize("@ss.hasPermi('system:note:edit')")
    @Log(title = "便签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhNote bhNote)
    {
        return toAjax(bhNoteService.updateBhNote(bhNote));
    }

    /**
     * 删除便签
     */
    @PreAuthorize("@ss.hasPermi('system:note:remove')")
    @Log(title = "便签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhNoteService.deleteBhNoteByIds(ids));
    }
}
