package me.zrxjava.system.modules.ums.service;

import me.zrxjava.system.modules.ums.entity.DictDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.system.modules.ums.dto.DictDetailDto;
import me.zrxjava.system.modules.ums.vo.DictDetailVo;
import me.zrxjava.system.modules.ums.criteria.DictDetailCriteria;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Set;

/**
 * 数据字典详情Service接口
 * 
 * @author zrxjava
 * @date 2021-04-05
 */
public interface IDictDetailService extends IService<DictDetail>
{
    /**
     * 查询数据字典详情
     * 
     * @param dictDetailId 数据字典详情ID
     * @return 数据字典详情
     */
    public DictDetailVo detail(Long dictDetailId);


    /**
     * 查询数据字典详情列表
     * 
     * @param  criteria
     * @return 数据字典详情集合
     */
     public Page<DictDetailVo> selectPage(DictDetailCriteria criteria);

    /**
     * 新增数据字典详情
     * 
     * @param dto
     * @return 结果
     */
    public Boolean add(DictDetailDto dto);

    /**
     * 修改数据字典详情
     * 
     * @param dto
     * @return 结果
     */
    public Boolean edit(DictDetailDto dto);

    /**
     * 批量删除数据字典详情
     * 
     * @param ids 需要删除的数据字典详情ID
     * @return 结果
     */
    public Boolean delete(Set<Long> ids);

}
