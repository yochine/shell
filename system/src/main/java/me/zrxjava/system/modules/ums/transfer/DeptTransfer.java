package me.zrxjava.system.modules.ums.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.system.modules.ums.dto.DeptDto;
import me.zrxjava.system.modules.ums.vo.DeptVo;
import me.zrxjava.system.modules.ums.entity.Dept;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 部门转换器
 *
 * @author zrxjava
 * @date 2021-04-11
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptTransfer extends BaseTransfer<DeptDto, Dept, DeptVo> {
}
