package me.zrxjava.system.modules.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.system.modules.dto.RoleDto;
import me.zrxjava.system.modules.vo.RoleVo;
import me.zrxjava.system.modules.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 角色转换器
 *
 * @author zrxjava
 * @date 2021-04-12
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleTransfer extends BaseTransfer<RoleDto, Role, RoleVo> {
}
