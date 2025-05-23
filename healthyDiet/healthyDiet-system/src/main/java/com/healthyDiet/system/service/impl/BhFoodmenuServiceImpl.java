package com.healthyDiet.system.service.impl;

import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import com.healthyDiet.system.mapper.BhFoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.healthyDiet.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.healthyDiet.system.domain.BhMenuInfo;
import com.healthyDiet.system.mapper.BhFoodmenuMapper;
import com.healthyDiet.system.domain.BhFoodmenu;
import com.healthyDiet.system.service.IBhFoodmenuService;

/**
 * 推荐食谱Service业务层处理
 * 
 * @author bohe
 * @date 2024-03-17
 */
@Service
public class BhFoodmenuServiceImpl implements IBhFoodmenuService 
{
    @Autowired
    private BhFoodmenuMapper bhFoodmenuMapper;

    @Autowired
    private BhFoodMapper foodMapper;


    /**
     * 查询推荐食谱
     * 
     * @param id 推荐食谱主键
     * @return 推荐食谱
     */
    @Override
    public BhFoodmenu selectBhFoodmenuById(Long id)
    {

        BhFoodmenu bhFoodmenu = bhFoodmenuMapper.selectBhFoodmenuById(id);
        if(bhFoodmenu.getBhMenuInfoList().size()>0){
            for (BhMenuInfo info : bhFoodmenu.getBhMenuInfoList()){
                info.setFood(foodMapper.selectBhFoodById(info.getFoodId()));
            }
        }
        return bhFoodmenu;
    }

    /**
     * 查询推荐食谱列表
     * 
     * @param bhFoodmenu 推荐食谱
     * @return 推荐食谱
     */
    @Override
    public List<BhFoodmenu> selectBhFoodmenuList(BhFoodmenu bhFoodmenu)
    {
        return bhFoodmenuMapper.selectBhFoodmenuList(bhFoodmenu);
    }

    /**
     * 新增推荐食谱
     * 
     * @param bhFoodmenu 推荐食谱
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBhFoodmenu(BhFoodmenu bhFoodmenu)
    {
        bhFoodmenu.setCreateTime(DateUtils.getNowDate());
        int rows = bhFoodmenuMapper.insertBhFoodmenu(bhFoodmenu);
        insertBhMenuInfo(bhFoodmenu);
        return rows;
    }

    /**
     * 修改推荐食谱
     * 
     * @param bhFoodmenu 推荐食谱
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBhFoodmenu(BhFoodmenu bhFoodmenu)
    {
        bhFoodmenuMapper.deleteBhMenuInfoByMenuId(bhFoodmenu.getId());
        insertBhMenuInfo(bhFoodmenu);
        return bhFoodmenuMapper.updateBhFoodmenu(bhFoodmenu);
    }

    /**
     * 批量删除推荐食谱
     * 
     * @param ids 需要删除的推荐食谱主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBhFoodmenuByIds(Long[] ids)
    {
        bhFoodmenuMapper.deleteBhMenuInfoByMenuIds(ids);
        return bhFoodmenuMapper.deleteBhFoodmenuByIds(ids);
    }

    /**
     * 删除推荐食谱信息
     * 
     * @param id 推荐食谱主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBhFoodmenuById(Long id)
    {
        bhFoodmenuMapper.deleteBhMenuInfoByMenuId(id);
        return bhFoodmenuMapper.deleteBhFoodmenuById(id);
    }

    /**
     * 新增推荐食物详情信息
     * 
     * @param bhFoodmenu 推荐食谱对象
     */
    public void insertBhMenuInfo(BhFoodmenu bhFoodmenu)
    {
        List<BhMenuInfo> bhMenuInfoList = bhFoodmenu.getBhMenuInfoList();
        Long id = bhFoodmenu.getId();
        if (StringUtils.isNotNull(bhMenuInfoList))
        {
            List<BhMenuInfo> list = new ArrayList<BhMenuInfo>();
            for (BhMenuInfo bhMenuInfo : bhMenuInfoList)
            {
                bhMenuInfo.setMenuId(id);
                list.add(bhMenuInfo);
            }
            if (list.size() > 0)
            {
                bhFoodmenuMapper.batchBhMenuInfo(list);
            }
        }
    }
}
