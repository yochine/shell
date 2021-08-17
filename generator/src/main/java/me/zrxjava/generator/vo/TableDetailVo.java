package me.zrxjava.generator.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author void
 * @create 2020-12-22
 */
@Data
@Builder
public class TableDetailVo implements Serializable {

    private TableVo table;

    private List<TableColumnVo> tableColumns;
}
