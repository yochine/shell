package me.zrxjava.system.modules.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.system.modules.dto.MenuDto;
import me.zrxjava.system.modules.entity.Menu;
import me.zrxjava.system.modules.vo.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author void
 * @create 2020-12-04
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuTransfer extends BaseTransfer<MenuDto, Menu, MenuVo> {
}
