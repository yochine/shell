package me.zrxjava.generator.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.generator.dto.TableColumnDto;
import me.zrxjava.generator.entity.TableColumn;
import me.zrxjava.generator.vo.TableColumnVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author void
 * @create 2020-12-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TableColumnTransfer extends BaseTransfer<TableColumnDto, TableColumn, TableColumnVo> {
}
