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
import com.healthyDiet.system.domain.BhMove;
import com.healthyDiet.system.service.IBhMoveService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 运动项目管理Controller
 * 
 * @author MrYuan
 * @date 2023-05-16
 */
@RestController
@RequestMapping("/system/bhmove")
public class BhMoveController extends BaseController
{
    @Autowired
    private IBhMoveService bhMoveService;

    /**
     * 查询运动项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:bhmove:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhMove bhMove)
    {
        startPage();
        List<BhMove> list = bhMoveService.selectBhMoveList(bhMove);
        return getDataTable(list);
    }

    /**
     * 导出运动项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:bhmove:export')")
    @Log(title = "运动项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhMove bhMove)
    {
        List<BhMove> list = bhMoveService.selectBhMoveList(bhMove);
        ExcelUtil<BhMove> util = new ExcelUtil<BhMove>(BhMove.class);
        util.exportExcel(response, list, "运动项目管理数据");
    }

    /**
     * 获取运动项目管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bhmove:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhMoveService.selectBhMoveById(id));
    }

    /**
     * 新增运动项目管理
     */
    @PreAuthorize("@ss.hasPermi('system:bhmove:add')")
    @Log(title = "运动项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhMove bhMove)
    {
        return toAjax(bhMoveService.insertBhMove(bhMove));
    }

    /**
     * 修改运动项目管理
     */
    @PreAuthorize("@ss.hasPermi('system:bhmove:edit')")
    @Log(title = "运动项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhMove bhMove)
    {
        return toAjax(bhMoveService.updateBhMove(bhMove));
    }

    /**
     * 删除运动项目管理
     */
    @PreAuthorize("@ss.hasPermi('system:bhmove:remove')")
    @Log(title = "运动项目管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhMoveService.deleteBhMoveByIds(ids));
    }
}
