package me.zrxjava.generator.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.generator.entity.Table;
import me.zrxjava.generator.vo.TableDetailVo;

/**
 * <p>
 * 代码生成业务表 服务类
 * </p>
 *
 * @author void
 * @since 2020-12-21
 */
public interface ITableService extends IService<Table> {

    /**
     * 查询数据库列表
     * @param tableName
     * @return
     */
    Page<Table> selectDbList(String tableName);

    /**
     * 导入表结构
     * @param tableNames
     * @param name
     * @return
     */
    Boolean importTable(String[] tableNames, String name);

    /**
     * 查询代码生成表详情
     * @param tableId
     * @return
     */
    TableDetailVo detail(Long tableId);

}
