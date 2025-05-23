package com.healthyDiet.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhLoveFoodMapper;
import com.healthyDiet.system.domain.BhLoveFood;
import com.healthyDiet.system.service.IBhLoveFoodService;

/**
 * 收藏食物Service业务层处理
 * 
 * @author MrYuan
 * @date 2023-05-18
 */
@Service
public class BhLoveFoodServiceImpl implements IBhLoveFoodService 
{
    @Autowired
    private BhLoveFoodMapper bhLoveFoodMapper;

    /**
     * 查询收藏食物
     * 
     * @param uid 收藏食物主键
     * @return 收藏食物
     */
    @Override
    public BhLoveFood selectBhLoveFoodByUid(Long uid)
    {
        return bhLoveFoodMapper.selectBhLoveFoodByUid(uid);
    }

    @Override
    public List<BhLoveFood>  selectBhLoveList(Long uid) {
        return bhLoveFoodMapper.selectBhLoveList(uid);
    }

    @Override
    public int deleteBhLove(BhLoveFood loveFood) {
        return bhLoveFoodMapper.deleteBhLove(loveFood);
    }

    /**
     * 查询收藏食物列表
     * 
     * @param bhLoveFood 收藏食物
     * @return 收藏食物
     */
    @Override
    public List<BhLoveFood> selectBhLoveFoodList(BhLoveFood bhLoveFood)
    {
        return bhLoveFoodMapper.selectBhLoveFoodList(bhLoveFood);
    }

    /**
     * 新增收藏食物
     * 
     * @param bhLoveFood 收藏食物
     * @return 结果
     */
    @Override
    public int insertBhLoveFood(BhLoveFood bhLoveFood)
    {
        return bhLoveFoodMapper.insertBhLoveFood(bhLoveFood);
    }

    /**
     * 修改收藏食物
     * 
     * @param bhLoveFood 收藏食物
     * @return 结果
     */
    @Override
    public int updateBhLoveFood(BhLoveFood bhLoveFood)
    {
        return bhLoveFoodMapper.updateBhLoveFood(bhLoveFood);
    }

    /**
     * 批量删除收藏食物
     * 
     * @param uids 需要删除的收藏食物主键
     * @return 结果
     */
    @Override
    public int deleteBhLoveFoodByUids(Long[] uids)
    {
        return bhLoveFoodMapper.deleteBhLoveFoodByUids(uids);
    }

    /**
     * 删除收藏食物信息
     * 
     * @param uid 收藏食物主键
     * @return 结果
     */
    @Override
    public int deleteBhLoveFoodByUid(Long uid)
    {
        return bhLoveFoodMapper.deleteBhLoveFoodByUid(uid);
    }
}
