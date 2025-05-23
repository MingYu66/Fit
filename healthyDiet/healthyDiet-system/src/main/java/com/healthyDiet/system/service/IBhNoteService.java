package com.healthyDiet.system.service;

import java.util.List;
import com.healthyDiet.system.domain.BhNote;

/**
 * 便签Service接口
 * 
 * @author ruoyi
 * @date 2025-03-26
 */
public interface IBhNoteService 
{
    /**
     * 查询便签
     * 
     * @param id 便签主键
     * @return 便签
     */
    public BhNote selectBhNoteById(Long id);

    /**
     * 查询便签列表
     * 
     * @param bhNote 便签
     * @return 便签集合
     */
    public List<BhNote> selectBhNoteList(BhNote bhNote);

    /**
     * 新增便签
     * 
     * @param bhNote 便签
     * @return 结果
     */
    public int insertBhNote(BhNote bhNote);

    /**
     * 修改便签
     * 
     * @param bhNote 便签
     * @return 结果
     */
    public int updateBhNote(BhNote bhNote);

    /**
     * 批量删除便签
     * 
     * @param ids 需要删除的便签主键集合
     * @return 结果
     */
    public int deleteBhNoteByIds(Long[] ids);

    /**
     * 删除便签信息
     * 
     * @param id 便签主键
     * @return 结果
     */
    public int deleteBhNoteById(Long id);
}
