package com.healthyDiet.system.service.impl;

import java.util.Date;
import java.util.List;
import com.healthyDiet.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthyDiet.system.mapper.BhNoteMapper;
import com.healthyDiet.system.domain.BhNote;
import com.healthyDiet.system.service.IBhNoteService;

/**
 * 便签Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-26
 */
@Service
public class BhNoteServiceImpl implements IBhNoteService 
{
    @Autowired
    private BhNoteMapper bhNoteMapper;

    /**
     * 查询便签
     * 
     * @param id 便签主键
     * @return 便签
     */
    @Override
    public BhNote selectBhNoteById(Long id)
    {
        return bhNoteMapper.selectBhNoteById(id);
    }

    /**
     * 查询便签列表
     * 
     * @param bhNote 便签
     * @return 便签
     */
    @Override
    public List<BhNote> selectBhNoteList(BhNote bhNote)
    {
        return bhNoteMapper.selectBhNoteList(bhNote);
    }

    /**
     * 新增便签
     * 
     * @param bhNote 便签
     * @return 结果
     */
    @Override
    public int insertBhNote(BhNote bhNote)
    {

        bhNote.setCreateTime(DateUtils.getNowDate());
        return bhNoteMapper.insertBhNote(bhNote);
    }

    /**
     * 修改便签
     * 
     * @param bhNote 便签
     * @return 结果
     */
    @Override
    public int updateBhNote(BhNote bhNote)
    {
        bhNote.setCreateTime(new Date());
        return bhNoteMapper.updateBhNote(bhNote);
    }

    /**
     * 批量删除便签
     * 
     * @param ids 需要删除的便签主键
     * @return 结果
     */
    @Override
    public int deleteBhNoteByIds(Long[] ids)
    {
        return bhNoteMapper.deleteBhNoteByIds(ids);
    }

    /**
     * 删除便签信息
     * 
     * @param id 便签主键
     * @return 结果
     */
    @Override
    public int deleteBhNoteById(Long id)
    {
        return bhNoteMapper.deleteBhNoteById(id);
    }
}
