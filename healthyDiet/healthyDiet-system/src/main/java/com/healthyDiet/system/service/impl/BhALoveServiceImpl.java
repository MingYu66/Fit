package com.healthyDiet.system.service.impl;

import java.util.List;

import com.healthyDiet.system.domain.BhArtice;
import com.healthyDiet.system.mapper.BhArticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhALoveMapper;
import com.healthyDiet.system.domain.BhALove;
import com.healthyDiet.system.service.IBhALoveService;

/**
 * 点赞记录Service业务层处理
 * 
 * @author MrYuan
 * @date 2023-05-31
 */
@Service
public class BhALoveServiceImpl implements IBhALoveService 
{
    @Autowired
    private BhALoveMapper bhALoveMapper;
    @Autowired
    private BhArticeMapper articeService;
    /**
     * 查询点赞记录
     * 
     * @param uid 点赞记录主键
     * @return 点赞记录
     */
    @Override
    public BhALove selectBhALoveByUid(Long uid)
    {
        return bhALoveMapper.selectBhALoveByUid(uid);
    }

    /**
     * 查询点赞记录列表
     * 
     * @param bhALove 点赞记录
     * @return 点赞记录
     */
    @Override
    public List<BhALove> selectBhALoveList(BhALove bhALove)
    {
        return bhALoveMapper.selectBhALoveList(bhALove);
    }

    /**
     * 新增点赞记录
     * 
     * @param bhALove 点赞记录
     * @return 结果
     */
    @Override
    public int insertBhALove(BhALove bhALove)
    {

        int i = bhALoveMapper.insertBhALove(bhALove);
        if(i == 1){
            System.out.println(bhALove.getAid());
            BhArtice bhArtice= articeService.selectBhArticeById(Long.parseLong(bhALove.getAid().toString()));
            bhArtice.setCount(bhArtice.getCount() + 1);
            i = articeService.updateBhArtice(bhArtice);
        }

        return i;
    }

    /**
     * 修改点赞记录
     * 
     * @param bhALove 点赞记录
     * @return 结果
     */
    @Override
    public int updateBhALove(BhALove bhALove)
    {
        return bhALoveMapper.updateBhALove(bhALove);
    }

    /**
     * 批量删除点赞记录
     * 
     * @param uids 需要删除的点赞记录主键
     * @return 结果
     */
    @Override
    public int deleteBhALoveByUids(Long[] uids)
    {
        return bhALoveMapper.deleteBhALoveByUids(uids);
    }

    /**
     * 删除点赞记录信息
     * 
     * @param uid 点赞记录主键
     * @return 结果
     */
    @Override
    public int deleteBhALoveByUid(BhALove bhALove)
    {
        if(bhALoveMapper.deleteBhALoveByUid(bhALove) == 1){
            BhArtice artice = articeService.selectBhArticeById(bhALove.getAid());
            artice.setCount(artice.getCount() - 1L);
            articeService.updateBhArtice(artice);
            return 1;
        }

        return 0;
    }
}
