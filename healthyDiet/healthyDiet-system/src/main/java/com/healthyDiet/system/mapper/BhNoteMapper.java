package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhNote;

/**
 * 便签Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-26
 */
public interface BhNoteMapper 
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
     * 删除便签
     * 
     * @param id 便签主键
     * @return 结果
     */
    public int deleteBhNoteById(Long id);

    /**
     * 批量删除便签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhNoteByIds(Long[] ids);
}
