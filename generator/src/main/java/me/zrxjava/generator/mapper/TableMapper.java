package me.zrxjava.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.zrxjava.generator.entity.Table;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 Mapper 接口
 * </p>
 *
 * @author void
 * @since 2020-12-21
 */
public interface TableMapper extends BaseMapper<Table> {

    /**
     * 查询数据库列表
     *
     * @param page
     * @param tableName
     * @return
     */
    Page<Table> selectDbList(Page<Table> page, String tableName);

    /**
     * 根据表名查询数据库表
     * @param tableNames
     * @return
     */
    List<Table> selectDbListByTableNames(String[] tableNames);
}
