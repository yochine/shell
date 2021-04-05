package me.zrxjava.system.modules.ums.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.system.modules.ums.dto.DictDto;
import me.zrxjava.system.modules.ums.vo.DictVo;
import me.zrxjava.system.modules.ums.entity.Dict;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 数据字典转换器
 *
 * @author zrxjava
 * @date 2021-04-05
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictTransfer extends BaseTransfer<DictDto, Dict, DictVo> {
}
