package me.zrxjava.generator.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zrxjava.generator.criteria.TableListCriteria;
import me.zrxjava.generator.dto.UpdateTableDto;
import me.zrxjava.generator.entity.Table;
import me.zrxjava.generator.vo.TableDetailVo;
import me.zrxjava.generator.vo.TableVo;

import java.util.Map;
import java.util.Set;

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
    Page<TableVo> selectDbList(String tableName);

    /**
     * 导入表结构
     * @param tableNames
     * @param name
     * @return
     */
    Boolean importTable(String[] tableNames, String name);

    /**
     * 分页查询代码生成列表
     * @param criteria
     * @return
     */
    Page<TableVo> selectPage(TableListCriteria criteria);

    /**
     * 查询代码生成表详情
     * @param tableId
     * @return
     */
    TableDetailVo detail(Long tableId);

    /**
     * 编辑代码生成配置
     * @param dto
     * @return
     */
    Boolean updateTableAndColumn(UpdateTableDto dto);

    /**
     * 同步代码生成表
     * @param tableName
     * @param tableId
     * @param username
     * @return
     */
    Boolean syncTable(String tableName, Long tableId, String username);

    /**
     * 删除代码生成表数据
     * @param ids
     * @return
     */
    Boolean delete(Set<Long> ids);

    /**
     * 代码生成预览
     * @param tableId
     * @return
     */
    Map<String, String> preview(Long tableId);


}
