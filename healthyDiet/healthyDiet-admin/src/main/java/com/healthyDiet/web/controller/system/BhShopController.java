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
import com.healthyDiet.system.domain.BhShop;
import com.healthyDiet.system.service.IBhShopService;
import com.healthyDiet.common.utils.poi.ExcelUtil;
import com.healthyDiet.common.core.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author ruoyi
 * @date 2025-04-17
 */
@RestController
@RequestMapping("/system/shop")
public class BhShopController extends BaseController
{
    @Autowired
    private IBhShopService bhShopService;

    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:shop:list')")
    @GetMapping("/list")
    public TableDataInfo list(BhShop bhShop)
    {
        startPage();
        List<BhShop> list = bhShopService.selectBhShopList(bhShop);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:shop:export')")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BhShop bhShop)
    {
        List<BhShop> list = bhShopService.selectBhShopList(bhShop);
        ExcelUtil<BhShop> util = new ExcelUtil<BhShop>(BhShop.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shop:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bhShopService.selectBhShopById(id));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('system:shop:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BhShop bhShop)
    {
        return toAjax(bhShopService.insertBhShop(bhShop));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('system:shop:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BhShop bhShop)
    {
        return toAjax(bhShopService.updateBhShop(bhShop));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('system:shop:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bhShopService.deleteBhShopByIds(ids));
    }
}
