package me.zrxjava.generator.mapper;

import me.zrxjava.generator.entity.TableColumn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 Mapper 接口
 * </p>
 *
 * @author void
 * @since 2020-12-21
 */
public interface TableColumnMapper extends BaseMapper<TableColumn> {

    /**
     * 查询db表字段等信息
     * @param tableName
     * @return
     */
    List<TableColumn> selectDbTableColumnsByName(String tableName);
}
