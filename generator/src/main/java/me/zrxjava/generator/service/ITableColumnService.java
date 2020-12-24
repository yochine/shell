package me.zrxjava.generator.service;

import me.zrxjava.generator.entity.TableColumn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务类
 * </p>
 *
 * @author void
 * @since 2020-12-21
 */
public interface ITableColumnService extends IService<TableColumn> {

    /**
     * 查询db表字段等信息
     * @param tableName
     * @return
     */
    List<TableColumn> selectDbTableColumnsByName(String tableName);
}
