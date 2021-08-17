package me.zrxjava.system.modules.service;

import me.zrxjava.system.modules.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.system.modules.dto.DictDto;
import me.zrxjava.system.modules.vo.DictVo;
import me.zrxjava.system.modules.criteria.DictCriteria;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Set;

/**
 * 数据字典Service接口
 * 
 * @author zrxjava
 * @date 2021-04-05
 */
public interface IDictService extends IService<Dict>
{
    /**
     * 查询数据字典
     * 
     * @param dictId 数据字典ID
     * @return 数据字典
     */
    public DictVo detail(Long dictId);


    /**
     * 查询数据字典列表
     * 
     * @param  criteria
     * @return 数据字典集合
     */
     public Page<DictVo> selectPage(DictCriteria criteria);

    /**
     * 新增数据字典
     * 
     * @param dto
     * @return 结果
     */
    public Boolean add(DictDto dto);

    /**
     * 修改数据字典
     * 
     * @param dto
     * @return 结果
     */
    public Boolean edit(DictDto dto);

    /**
     * 批量删除数据字典
     * 
     * @param ids 需要删除的数据字典ID
     * @return 结果
     */
    public Boolean delete(Set<Long> ids);

}
