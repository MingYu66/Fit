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
import com.healthyDiet.system.domain.BhShopLove;
import com.healthyDiet.system.service.IBhShopLoveService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 商品收藏Controller
 * 
 * @author ruoyi
 * @date 2025-04-18
 */
@RestController
@RequestMapping("/system/shopLove")
public class BhShopLoveController extends BaseController
{
    @Autowired
    private IBhShopLoveService bhShopLoveService;

    /**
     * 查询商品收藏列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopLove:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhShopLove bhShopLove)
    {
        startPage();
        List<BhShopLove> list = bhShopLoveService.selectBhShopLoveList(bhShopLove);
        return getDataTable(list);
    }

    /**
     * 导出商品收藏列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopLove:export')")
    @Log(title = "商品收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhShopLove bhShopLove)
    {
        List<BhShopLove> list = bhShopLoveService.selectBhShopLoveList(bhShopLove);
        ExcelUtil<BhShopLove> util = new ExcelUtil<BhShopLove>(BhShopLove.class);
        util.exportExcel(response, list, "商品收藏数据");
    }

    /**
     * 获取商品收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopLove:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") Long uid)
    {
        return success(bhShopLoveService.selectBhShopLoveByUid(uid));
    }

    /**
     * 新增商品收藏
     */
    @PreAuthorize("@ss.hasPermi('system:shopLove:add')")
    @Log(title = "商品收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhShopLove bhShopLove)
    {
        return toAjax(bhShopLoveService.insertBhShopLove(bhShopLove));
    }

    /**
     * 修改商品收藏
     */
    @PreAuthorize("@ss.hasPermi('system:shopLove:edit')")
    @Log(title = "商品收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhShopLove bhShopLove)
    {
        return toAjax(bhShopLoveService.updateBhShopLove(bhShopLove));
    }

    /**
     * 删除商品收藏
     */
    @PreAuthorize("@ss.hasPermi('system:shopLove:remove')")
    @Log(title = "商品收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable Long[] uids)
    {
        return toAjax(bhShopLoveService.deleteBhShopLoveByUids(uids));
    }
}
