package me.zrxjava.generator.dto;

import lombok.Data;

import java.util.List;

/**
 * @author void
 * @create 2020-12-23
 */
@Data
public class UpdateTableDto {

    private TableDto table;

    private List<TableColumnDto> tableColumns;
}
