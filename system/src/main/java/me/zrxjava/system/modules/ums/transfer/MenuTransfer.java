package me.zrxjava.system.modules.ums.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.system.modules.ums.dto.MenuDto;
import me.zrxjava.system.modules.ums.entity.Menu;
import me.zrxjava.system.modules.ums.vo.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author void
 * @create 2020-12-04
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuTransfer extends BaseTransfer<MenuDto, Menu, MenuVo> {
}
