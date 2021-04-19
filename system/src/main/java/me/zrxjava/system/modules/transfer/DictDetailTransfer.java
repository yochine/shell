package me.zrxjava.system.modules.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.system.modules.dto.DictDetailDto;
import me.zrxjava.system.modules.vo.DictDetailVo;
import me.zrxjava.system.modules.entity.DictDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 数据字典详情转换器
 *
 * @author zrxjava
 * @date 2021-04-05
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailTransfer extends BaseTransfer<DictDetailDto, DictDetail, DictDetailVo> {
}
