package me.zrxjava.generator.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.utils.QueryHelper;
import me.zrxjava.generator.config.GenConfig;
import me.zrxjava.generator.criteria.TableListCriteria;
import me.zrxjava.generator.dto.UpdateTableDto;
import me.zrxjava.generator.entity.Table;
import me.zrxjava.generator.entity.TableColumn;
import me.zrxjava.generator.mapper.TableMapper;
import me.zrxjava.generator.service.ITableColumnService;
import me.zrxjava.generator.service.ITableService;
import me.zrxjava.generator.transfer.TableColumnTransfer;
import me.zrxjava.generator.transfer.TableTransfer;
import me.zrxjava.generator.util.GenUtils;
import me.zrxjava.generator.util.VelocityUtils;
import me.zrxjava.generator.vo.TableDetailVo;
import me.zrxjava.generator.vo.TableVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

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
    private final ITableColumnService tableColumnService;
    private final TableTransfer tableTransfer;
    private final TableColumnTransfer tableColumnTransfer;

    private final GenConfig genConfig;


    @Override
    public Page<TableVo> selectDbList(String tableName) {
        Page<Table> page = new Page<>(1, 20);
        return tableTransfer.toPageVo(tableMapper.selectDbList(page,tableName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean importTable(String[] tableNames, String name) {
        List<Table> tableList = tableMapper.selectDbListByTableNames(tableNames);
        if (CollectionUtil.isNotEmpty(tableList)){
            for (Table table : tableList) {
                String tableName = table.getTableName();
                GenUtils.initTable(table, name, genConfig);
                int row = tableMapper.insert(table);
                if (row > 0){
                    // 保存列信息
                    List<TableColumn> genTableColumns = tableColumnService.selectDbTableColumnsByName(tableName);
                    for (TableColumn column : genTableColumns) {
                        GenUtils.initColumnField(column, table.getTableId(),table.getCreateBy());
                        tableColumnService.save(column);
                    }
                }
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Page<TableVo> selectPage(TableListCriteria criteria) {
        Page<Table> page = new Page<>(criteria.getCurrent(), criteria.getSize());
        return tableTransfer.toPageVo(this.page(page, QueryHelper.getQueryWrapper(criteria,Table.class)));
    }

    @Override
    public TableDetailVo detail(Long tableId) {
        Table table = tableMapper.selectById(tableId);
        List<TableColumn> tableColumns = tableColumnService.list(Wrappers.<TableColumn>lambdaQuery().eq(TableColumn::getTableId,tableId));
        return TableDetailVo.builder()
                .table(tableTransfer.toVo(table))
                .tableColumns(tableColumnTransfer.toVos(tableColumns)).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTableAndColumn(UpdateTableDto dto) {
        Table table = tableTransfer.toEntity(dto.getTable());
        List<TableColumn> columns = tableColumnTransfer.toEntities(dto.getTableColumns());
        return updateById(table) && tableColumnService.saveOrUpdateBatch(columns);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean syncTable(String tableName, Long tableId, String username) {
        // 查询db表字段
        List<TableColumn> dbColumns = tableColumnService.selectDbTableColumnsByName(tableName);
        List<TableColumn> genColumns = tableColumnService.list(Wrappers.<TableColumn>lambdaQuery().eq(TableColumn::getTableId,tableId));
        List<String> dbNames = dbColumns.parallelStream().map(TableColumn::getColumnName).collect(Collectors.toList());
        List<String> genNames = genColumns.parallelStream().map(TableColumn::getColumnName).collect(Collectors.toList());
        //新增的字段
        dbColumns.forEach(column -> {
            if (!genNames.contains(column.getColumnName())) {
                GenUtils.initColumnField(column, tableId,username);
                tableColumnService.save(column);
            }
        });
        //删除的字段
        List<TableColumn> delColumns = genColumns.stream().filter(column -> !dbNames.contains(column.getColumnName())).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(delColumns)) {
            tableColumnService.getBaseMapper().deleteBatchIds(delColumns);
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Set<Long> ids) {
        Boolean result1 = tableColumnService.remove(Wrappers.<TableColumn>lambdaUpdate()
                .in(TableColumn::getTableId,ids));
        Boolean result2 = this.removeByIds(ids);
        return result1 && result2;
    }

    @Override
    public Map<String, String> preview(Long tableId) {
        Table table = this.getById(tableId);
        List<TableColumn> tableColumns = tableColumnService.list(Wrappers.<TableColumn>lambdaQuery()
                                        .eq(TableColumn::getTableId,tableId));
        return VelocityUtils.startRendering(table,tableColumns);
    }

    @Override
    public Boolean generate(Set<Long> ids) {
        for(Long id : ids){
            Table table = this.getById(id);
            List<TableColumn> tableColumns = tableColumnService.list(Wrappers.<TableColumn>lambdaQuery()
                    .eq(TableColumn::getTableId,id));
            VelocityUtils.generate(table,tableColumns);
        }
        return Boolean.TRUE;
    }

    @Override
    public byte[] downLoad(Set<Long> ids) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for(Long id : ids){
            Table table = this.getById(id);
            List<TableColumn> tableColumns = tableColumnService.list(Wrappers.<TableColumn>lambdaQuery()
                    .eq(TableColumn::getTableId,id));
            VelocityUtils.download(table,tableColumns,zip);
        }
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

}
