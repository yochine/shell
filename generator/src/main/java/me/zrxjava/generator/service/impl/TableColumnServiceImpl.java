package me.zrxjava.generator.service.impl;

import lombok.RequiredArgsConstructor;
import me.zrxjava.generator.entity.TableColumn;
import me.zrxjava.generator.mapper.TableColumnMapper;
import me.zrxjava.generator.service.ITableColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务实现类
 * </p>
 *
 * @author void
 * @since 2020-12-21
 */
@Service
@RequiredArgsConstructor
public class TableColumnServiceImpl extends ServiceImpl<TableColumnMapper, TableColumn> implements ITableColumnService {



    private final TableColumnMapper tableColumnMapper;

    @Override
    public List<TableColumn> selectDbTableColumnsByName(String tableName) {
        return tableColumnMapper.selectDbTableColumnsByName(tableName);
    }
}
