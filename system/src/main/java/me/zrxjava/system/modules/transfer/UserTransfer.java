package me.zrxjava.system.modules.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.system.modules.dto.UserDto;
import me.zrxjava.system.modules.vo.UserVo;
import me.zrxjava.system.modules.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 系统用户转换器
 *
 * @author zrxjava
 * @date 2021-04-17
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserTransfer extends BaseTransfer<UserDto, User, UserVo> {
}
