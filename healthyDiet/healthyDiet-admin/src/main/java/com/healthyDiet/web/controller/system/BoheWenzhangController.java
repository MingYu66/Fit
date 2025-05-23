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
import com.healthyDiet.system.domain.BoheWenzhang;
import com.healthyDiet.system.service.IBoheWenzhangService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 健康栏目Controller
 * 
 * @author bohe
 * @date 2024-03-04
 */
@RestController
@RequestMapping("/system/wenzhang")
public class BoheWenzhangController extends BaseController
{
    @Autowired
    private IBoheWenzhangService boheWenzhangService;

    /**
     * 查询健康栏目列表
     */
    @PreAuthorize("@ss.hasPermi('system:wenzhang:list')")
    @GetMapping("/list")
    public TableDataInfo list(BoheWenzhang boheWenzhang)
    {
        startPage();
        List<BoheWenzhang> list = boheWenzhangService.selectBoheWenzhangList(boheWenzhang);
        return getDataTable(list);
    }




    /**
     * 导出健康栏目列表
     */
    @PreAuthorize("@ss.hasPermi('system:wenzhang:export')")
    @Log(title = "健康栏目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BoheWenzhang boheWenzhang)
    {
        List<BoheWenzhang> list = boheWenzhangService.selectBoheWenzhangList(boheWenzhang);
        ExcelUtil<BoheWenzhang> util = new ExcelUtil<BoheWenzhang>(BoheWenzhang.class);
        util.exportExcel(response, list, "健康栏目数据");
    }

    /**
     * 获取健康栏目详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:wenzhang:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(boheWenzhangService.selectBoheWenzhangById(id));
    }

    /**
     * 新增健康栏目
     */
    @PreAuthorize("@ss.hasPermi('system:wenzhang:add')")
    @Log(title = "健康栏目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BoheWenzhang boheWenzhang)
    {
        return toAjax(boheWenzhangService.insertBoheWenzhang(boheWenzhang));
    }

    /**
     * 修改健康栏目
     */
    @PreAuthorize("@ss.hasPermi('system:wenzhang:edit')")
    @Log(title = "健康栏目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BoheWenzhang boheWenzhang)
    {
        return toAjax(boheWenzhangService.updateBoheWenzhang(boheWenzhang));
    }

    /**
     * 删除健康栏目
     */
    @PreAuthorize("@ss.hasPermi('system:wenzhang:remove')")
    @Log(title = "健康栏目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(boheWenzhangService.deleteBoheWenzhangByIds(ids));
    }
}
