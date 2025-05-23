package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhJianyi;

/**
 * 建议反馈Mapper接口
 * 
 * @author bohe
 * @date 2024-03-04
 */
public interface BhJianyiMapper 
{
    /**
     * 查询建议反馈
     * 
     * @param id 建议反馈主键
     * @return 建议反馈
     */
    public BhJianyi selectBhJianyiById(Long id);

    /**
     * 查询建议反馈列表
     * 
     * @param bhJianyi 建议反馈
     * @return 建议反馈集合
     */
    public List<BhJianyi> selectBhJianyiList(BhJianyi bhJianyi);

    /**
     * 新增建议反馈
     * 
     * @param bhJianyi 建议反馈
     * @return 结果
     */
    public int insertBhJianyi(BhJianyi bhJianyi);

    /**
     * 修改建议反馈
     * 
     * @param bhJianyi 建议反馈
     * @return 结果
     */
    public int updateBhJianyi(BhJianyi bhJianyi);

    /**
     * 删除建议反馈
     * 
     * @param id 建议反馈主键
     * @return 结果
     */
    public int deleteBhJianyiById(Long id);

    /**
     * 批量删除建议反馈
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhJianyiByIds(Long[] ids);
}
