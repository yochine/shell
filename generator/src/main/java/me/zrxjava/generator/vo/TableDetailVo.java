package me.zrxjava.generator.vo;

import lombok.Builder;
import lombok.Data;
import me.zrxjava.generator.entity.Table;
import me.zrxjava.generator.entity.TableColumn;

import java.util.List;

/**
 * @author void
 * @create 2020-12-22
 */
@Data
@Builder
public class TableDetailVo {

    private Table table;

    private List<TableColumn> tableColumns;
}
