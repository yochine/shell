package me.zrxjava.system.modules.ums.service.impl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.utils.QueryHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zrxjava.system.modules.ums.transfer.DictTransfer;
import me.zrxjava.system.modules.ums.dto.DictDto;
import me.zrxjava.system.modules.ums.vo.DictVo;
import me.zrxjava.system.modules.ums.criteria.DictCriteria;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import me.zrxjava.system.modules.ums.mapper.DictMapper;
import me.zrxjava.system.modules.ums.entity.Dict;
import org.springframework.stereotype.Service;
import me.zrxjava.system.modules.ums.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 数据字典Service业务层处理
 * 
 * @author zrxjava
 * @date 2021-04-05
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {
    private final DictTransfer dicttransfer;

    /**
     * 查询数据字典
     * 
     * @param dictId 数据字典ID
     * @return 数据字典
     */
    @Override
    public DictVo detail(Long dictId) {
        return dicttransfer.toVo(getById(dictId));
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
        return dicttransfer.toPageVo(this.page(page, QueryHelper.getQueryWrapper(criteria,Dict.class)));
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
        return save(dicttransfer.toEntity(dto));
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
        return updateById(dicttransfer.toEntity(dto));
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
