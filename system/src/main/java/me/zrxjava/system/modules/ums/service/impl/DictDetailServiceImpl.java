package me.zrxjava.system.modules.ums.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.utils.QueryHelper;
import me.zrxjava.system.modules.ums.criteria.DictDetailCriteria;
import me.zrxjava.system.modules.ums.dto.DictDetailDto;
import me.zrxjava.system.modules.ums.entity.DictDetail;
import me.zrxjava.system.modules.ums.mapper.DictDetailMapper;
import me.zrxjava.system.modules.ums.service.IDictDetailService;
import me.zrxjava.system.modules.ums.transfer.DictDetailTransfer;
import me.zrxjava.system.modules.ums.vo.DictDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
/**
 * 数据字典详情Service业务层处理
 *
 * @author zrxjava
 * @date 2021-04-05
 */
@Service
@RequiredArgsConstructor
public class DictDetailServiceImpl extends ServiceImpl<DictDetailMapper, DictDetail> implements IDictDetailService {
    private final DictDetailTransfer dictDetailTransfer;

    /**
     * 查询数据字典详情
     *
     * @param dictDetailId 数据字典详情ID
     * @return 数据字典详情
     */
    @Override
    public DictDetailVo detail(Long dictDetailId) {
        return dictDetailTransfer.toVo(getById(dictDetailId));
    }

    /**
     * 查询数据字典详情列表
     *
     * @param criteria
     * @return 数据字典详情
     */
    @Override
    public Page<DictDetailVo> selectPage(DictDetailCriteria criteria){
        Page<DictDetail> page = new Page<>(criteria.getCurrent(), criteria.getSize());
        return dictDetailTransfer.toPageVo(this.page(page, QueryHelper.getQueryWrapper(criteria,DictDetail.class)));
    }

    /**
     * 新增数据字典详情
     *
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(DictDetailDto dto) {
        return save(dictDetailTransfer.toEntity(dto));
    }

    /**
     * 修改数据字典详情
     *
     * @param dto
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean edit(DictDetailDto dto) {
        return updateById(dictDetailTransfer.toEntity(dto));
    }

    /**
     * 批量删除数据字典详情
     * 
     * @param ids 需要删除的数据字典详情ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Set<Long> ids) {
        return removeByIds(ids);
    }
}
