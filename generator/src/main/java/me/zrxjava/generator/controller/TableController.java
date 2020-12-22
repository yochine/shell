package me.zrxjava.generator.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.annotation.Log;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.enums.BusinessType;
import me.zrxjava.common.utils.QueryHelper;
import me.zrxjava.generator.entity.Table;
import me.zrxjava.generator.service.ITableService;
import me.zrxjava.generator.vo.TableDetailVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 代码生成业务表 前端控制器
 * </p>
 *
 * @author void
 * @since 2020-12-21
 */
@Api("代码生成")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tool/gen")
public class TableController {


    private final ITableService tableService;

    @GetMapping("/dbList")
    @ApiOperation("数据库列表")
    @PreAuthorize("@ps.check('tool:gen:dbList')")
    public ResponseResult<Page<Table>> dbList(String tableName){
        return ResponseResult.success(tableService.selectDbList(tableName));
    }


    @PostMapping("/import")
    @ApiOperation("导入表结构")
    @Log(title = "系统工具",businessType = BusinessType.IMPORT)
    @PreAuthorize("@ps.check('tool:gen:import')")
    public ResponseResult<Boolean> importTable(@NotBlank String[] tableNames, Authentication authentication){
        return ResponseResult.setBody(tableService.importTable(tableNames,authentication.getName()));
    }

    @GetMapping("/list")
    @ApiOperation("代码生成列表")
    @PreAuthorize("@ps.check('tool:gen:list')")
    public ResponseResult<Page<Table>> list(Table table){
        Page<Table> page = new Page<>(1, 20);
        return ResponseResult.success(tableService.page(page,QueryHelper.getQueryWrapper(table,Table.class)));
    }

    @GetMapping("/detail")
    @ApiOperation("数据库表详情")
    @PreAuthorize("@ps.check('tool:gen:detail')")
    public ResponseResult<TableDetailVo> detail(@NotNull(message = "缺少参数")  Long tableId){
        return ResponseResult.success(tableService.detail(tableId));
    }


    @PutMapping("/update")
    @ApiOperation("数据库表修改")
    @PreAuthorize("@ps.check('tool:gen:update')")
    @Log(title = "系统工具",businessType = BusinessType.UPDATE)
    public ResponseResult<TableDetailVo> update(@NotNull(message = "缺少参数")  Long tableId){
        return ResponseResult.success(tableService.detail(tableId));
    }
}

