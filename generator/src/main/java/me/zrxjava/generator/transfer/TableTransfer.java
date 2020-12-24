package me.zrxjava.generator.transfer;

import me.zrxjava.common.base.BaseTransfer;
import me.zrxjava.generator.dto.TableDto;
import me.zrxjava.generator.entity.Table;
import me.zrxjava.generator.vo.TableVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author void
 * @create 2020-12-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TableTransfer extends BaseTransfer<TableDto, Table, TableVo> {
}
