package me.zrxjava.system.modules.ums.service.impl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.utils.QueryHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zrxjava.system.modules.ums.transfer.DictDetailTransfer;
import me.zrxjava.system.modules.ums.dto.DictDetailDto;
import me.zrxjava.system.modules.ums.vo.DictDetailVo;
import me.zrxjava.system.modules.ums.criteria.DictDetailCriteria;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import me.zrxjava.system.modules.ums.mapper.DictDetailMapper;
import me.zrxjava.system.modules.ums.entity.DictDetail;
import org.springframework.stereotype.Service;
import me.zrxjava.system.modules.ums.service.IDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 数据字典详情Service业务层处理
 * 
 * @author zrxjava
 * @date 2021-04-05
 */
@Service
@RequiredArgsConstructor
public class DictDetailServiceImpl extends ServiceImpl<DictDetailMapper, DictDetail> implements IDictDetailService {
    private final DictDetailTransfer dictDetailtransfer;

    /**
     * 查询数据字典详情
     * 
     * @param dictDetailId 数据字典详情ID
     * @return 数据字典详情
     */
    @Override
    public DictDetailVo detail(Long dictDetailId) {
        return dictDetailtransfer.toVo(getById(dictDetailId));
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
        return dictDetailtransfer.toPageVo(this.page(page, QueryHelper.getQueryWrapper(criteria,DictDetail.class)));
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
        return save(dictDetailtransfer.toEntity(dto));
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
        return updateById(dictDetailtransfer.toEntity(dto));
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
