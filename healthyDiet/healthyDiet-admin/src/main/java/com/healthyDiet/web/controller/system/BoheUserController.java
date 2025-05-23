package com.healthyDiet.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.healthyDiet.common.annotation.Anonymous;
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
import com.healthyDiet.system.domain.BoheUser;
import com.healthyDiet.system.service.IBoheUserService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 薄荷用户管理Controller
 *
 * @author MrYuan
 * @date 2023-05-16
 */
@RestController
@RequestMapping("/system/boheUser")
public class BoheUserController extends BaseController {
    @Autowired
    private IBoheUserService boheUserService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:boheUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(BoheUser boheUser) {
        startPage();
        List<BoheUser> list = boheUserService.selectBoheUserList(boheUser);
        return getDataTable(list);
    }

    /**
     * 导出薄荷用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:boheUser:export')")
    @Log(title = "薄荷用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BoheUser boheUser) {
        List<BoheUser> list = boheUserService.selectBoheUserList(boheUser);
        ExcelUtil<BoheUser> util = new ExcelUtil<BoheUser>(BoheUser.class);
        util.exportExcel(response, list, "薄荷用户管理数据");
    }

    /**
     * 获取薄荷用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:boheUser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(boheUserService.selectBoheUserById(id));
    }

    /**
     * 新增薄荷用户管理
     */

    @Anonymous
    @Log(title = "薄荷用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BoheUser boheUser) {
        BoheUser old = new BoheUser();
        old.setUsername(boheUser.getUsername());

        if (boheUserService.selectBoheUserList(old).size() != 0) {
            return AjaxResult.error("用户名已被注册");
        }
        return toAjax(boheUserService.insertBoheUser(boheUser));

    }

    /**
     * 获取用户数据分析
     * @param boheUser
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:boheUser:edit')")
    @Log(title = "获取用户数据分析", businessType = BusinessType.INSERT)
    @PostMapping("/selectUserType")
    public AjaxResult selectUserType() {
        return AjaxResult.success(boheUserService.selectUserType());

    }


    /**
     * 修改薄荷用户管理
     */
    @PreAuthorize("@ss.hasPermi('system:boheUser:edit')")
    @Log(title = "薄荷用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BoheUser boheUser) {
        return toAjax(boheUserService.updateBoheUser(boheUser));
    }

    /**
     * 删除薄荷用户管理
     */
    @PreAuthorize("@ss.hasPermi('system:boheUser:remove')")
    @Log(title = "薄荷用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(boheUserService.deleteBoheUserByIds(ids));
    }
}
