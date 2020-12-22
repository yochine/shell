package me.zrxjava.generator.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.generator.config.GenConfig;
import me.zrxjava.generator.entity.Table;
import me.zrxjava.generator.entity.TableColumn;
import me.zrxjava.generator.mapper.TableColumnMapper;
import me.zrxjava.generator.mapper.TableMapper;
import me.zrxjava.generator.service.ITableService;
import me.zrxjava.generator.util.GenUtils;
import me.zrxjava.generator.vo.TableDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 服务实现类
 * </p>
 *
 * @author void
 * @since 2020-12-21
 */
@Service
@RequiredArgsConstructor
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {


    private final TableMapper tableMapper;

    private final TableColumnMapper tableColumnMapper;

    private final GenConfig genConfig;

    @Override
    public Page<Table> selectDbList(String tableName) {
        Page<Table> page = new Page<>(1, 20);
        return tableMapper.selectDbList(page,tableName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importTable(String[] tableNames, String name) {

        List<Table> tableList = tableMapper.selectDbListByTableNames(tableNames);
        if (CollectionUtil.isNotEmpty(tableList)){
            for (Table table : tableList)
            {
                String tableName = table.getTableName();
                GenUtils.initTable(table, name, genConfig);
                int row = tableMapper.insert(table);
                if (row > 0){
                    // 保存列信息
                    List<TableColumn> genTableColumns = tableColumnMapper.selectDbTableColumnsByName(tableName);
                    for (TableColumn column : genTableColumns)
                    {
                        GenUtils.initColumnField(column, table);
                        tableColumnMapper.insert(column);
                    }
                }
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public TableDetailVo detail(Long tableId) {
        Table table = tableMapper.selectById(tableId);
        List<TableColumn> tableColumns = tableColumnMapper.selectList(Wrappers.<TableColumn>lambdaQuery().eq(TableColumn::getTableId,tableId));
        return TableDetailVo.builder()
                .table(table).
                 tableColumns(tableColumns).build();
    }

}
