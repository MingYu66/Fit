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
import com.healthyDiet.system.domain.BhALove;
import com.healthyDiet.system.service.IBhALoveService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 点赞记录Controller
 * 
 * @author MrYuan
 * @date 2023-05-31
 */
@RestController
@RequestMapping("/system/love")
public class BhALoveController extends BaseController
{
    @Autowired
    private IBhALoveService bhALoveService;

    /**
     * 查询点赞记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:love:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhALove bhALove)
    {
        startPage();
        List<BhALove> list = bhALoveService.selectBhALoveList(bhALove);
        return getDataTable(list);
    }

    /**
     * 导出点赞记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:love:export')")
    @Log(title = "点赞记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhALove bhALove)
    {
        List<BhALove> list = bhALoveService.selectBhALoveList(bhALove);
        ExcelUtil<BhALove> util = new ExcelUtil<BhALove>(BhALove.class);
        util.exportExcel(response, list, "点赞记录数据");
    }

    /**
     * 获取点赞记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:love:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") Long uid)
    {
        return success(bhALoveService.selectBhALoveByUid(uid));
    }

    /**
     * 新增点赞记录
     */
    @PreAuthorize("@ss.hasPermi('system:love:add')")
    @Log(title = "点赞记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhALove bhALove)
    {
        return toAjax(bhALoveService.insertBhALove(bhALove));
    }

    /**
     * 修改点赞记录
     */
    @PreAuthorize("@ss.hasPermi('system:love:edit')")
    @Log(title = "点赞记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhALove bhALove)
    {
        return toAjax(bhALoveService.updateBhALove(bhALove));
    }

    /**
     * 删除点赞记录
     */
    @PreAuthorize("@ss.hasPermi('system:love:remove')")
    @Log(title = "点赞记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable Long[] uids)
    {
        return toAjax(bhALoveService.deleteBhALoveByUids(uids));
    }
}
