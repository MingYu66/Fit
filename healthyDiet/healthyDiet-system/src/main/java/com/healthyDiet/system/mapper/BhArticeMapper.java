package com.healthyDiet.system.mapper;

import java.util.List;
import com.healthyDiet.system.domain.BhArtice;

/**
 * 社区Mapper接口
 * 
 * @author Mr Yuan
 * @date 2023-05-31
 */
public interface BhArticeMapper 
{
    /**
     * 查询社区
     * 
     * @param id 社区主键
     * @return 社区
     */
    public BhArtice selectBhArticeById(Long id);

    /**
     * 查询社区列表
     * 
     * @param bhArtice 社区
     * @return 社区集合
     */
    public List<BhArtice> selectBhArticeList(BhArtice bhArtice);

    /**
     * 新增社区
     * 
     * @param bhArtice 社区
     * @return 结果
     */
    public int insertBhArtice(BhArtice bhArtice);

    /**
     * 修改社区
     * 
     * @param bhArtice 社区
     * @return 结果
     */
    public int updateBhArtice(BhArtice bhArtice);

    /**
     * 删除社区
     * 
     * @param id 社区主键
     * @return 结果
     */
    public int deleteBhArticeById(Long id);

    /**
     * 批量删除社区
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBhArticeByIds(Long[] ids);
}
