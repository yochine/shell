package me.zrxjava.system.modules.ums.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.utils.QueryHelper;
import me.zrxjava.system.modules.ums.criteria.DictCriteria;
import me.zrxjava.system.modules.ums.dto.DictDto;
import me.zrxjava.system.modules.ums.entity.Dict;
import me.zrxjava.system.modules.ums.mapper.DictMapper;
import me.zrxjava.system.modules.ums.service.IDictService;
import me.zrxjava.system.modules.ums.transfer.DictTransfer;
import me.zrxjava.system.modules.ums.vo.DictVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
/**
 * 数据字典Service业务层处理
 * 
 * @author zrxjava
 * @date 2021-04-05
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {
    private final DictTransfer dictTransfer;

    /**
     * 查询数据字典
     * 
     * @param dictId 数据字典ID
     * @return 数据字典
     */
    @Override
    public DictVo detail(Long dictId) {
        return dictTransfer.toVo(getById(dictId));
    }

    /**
     * 查询数据字典列表
     * 
     * @param criteria
     * @return 数据字典
     */
    @Override
    public Page<DictVo> selectPage(DictCriteria criteria){
        Page<Dict> page = new Page<>(criteria.getCurrent(), criteria.getSize());
        return dictTransfer.toPageVo(this.page(page, QueryHelper.getQueryWrapper(criteria,Dict.class)));
    }

    /**
     * 新增数据字典
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(DictDto dto) {
        return save(dictTransfer.toEntity(dto));
    }

    /**
     * 修改数据字典
     * 
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(DictDto dto) {
        return updateById(dictTransfer.toEntity(dto));
    }

    /**
     * 批量删除数据字典
     * 
     * @param ids 需要删除的数据字典ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Set<Long> ids) {
        return removeByIds(ids);
    }
}
