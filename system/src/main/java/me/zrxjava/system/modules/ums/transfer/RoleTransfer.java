package me.zrxjava.system.modules.ums.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.system.modules.ums.dto.RoleDto;
import me.zrxjava.system.modules.ums.vo.RoleVo;
import me.zrxjava.system.modules.ums.entity.Role;
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
