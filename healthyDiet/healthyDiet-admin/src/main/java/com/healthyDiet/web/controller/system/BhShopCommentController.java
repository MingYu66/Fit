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
import com.healthyDiet.system.domain.BhShopComment;
import com.healthyDiet.system.service.IBhShopCommentService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 商品评论Controller
 * 
 * @author ruoyi
 * @date 2025-04-18
 */
@RestController
@RequestMapping("/system/comment")
public class BhShopCommentController extends BaseController
{
    @Autowired
    private IBhShopCommentService bhShopCommentService;

    /**
     * 查询商品评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhShopComment bhShopComment)
    {
        startPage();
        List<BhShopComment> list = bhShopCommentService.selectBhShopCommentList(bhShopComment);
        return getDataTable(list);
    }

    /**
     * 导出商品评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:comment:export')")
    @Log(title = "商品评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhShopComment bhShopComment)
    {
        List<BhShopComment> list = bhShopCommentService.selectBhShopCommentList(bhShopComment);
        ExcelUtil<BhShopComment> util = new ExcelUtil<BhShopComment>(BhShopComment.class);
        util.exportExcel(response, list, "商品评论数据");
    }

    /**
     * 获取商品评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhShopCommentService.selectBhShopCommentById(id));
    }

    /**
     * 新增商品评论
     */
    @PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "商品评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhShopComment bhShopComment)
    {
        return toAjax(bhShopCommentService.insertBhShopComment(bhShopComment));
    }

    /**
     * 修改商品评论
     */
    @PreAuthorize("@ss.hasPermi('system:comment:edit')")
    @Log(title = "商品评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhShopComment bhShopComment)
    {
        return toAjax(bhShopCommentService.updateBhShopComment(bhShopComment));
    }

    /**
     * 删除商品评论
     */
    @PreAuthorize("@ss.hasPermi('system:comment:remove')")
    @Log(title = "商品评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhShopCommentService.deleteBhShopCommentByIds(ids));
    }
}
