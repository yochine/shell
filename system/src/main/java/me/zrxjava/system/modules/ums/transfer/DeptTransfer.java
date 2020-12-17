package me.zrxjava.system.modules.ums.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.system.modules.ums.dto.DeptDto;
import me.zrxjava.system.modules.ums.entity.Dept;
import me.zrxjava.system.modules.ums.vo.DeptVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author void
 * @create 2020-12-14
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptTransfer extends BaseTransfer<DeptDto, Dept, DeptVo> {
}
