package me.zrxjava.generator.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.annotation.Log;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.enums.BusinessType;
import me.zrxjava.generator.criteria.TableListCriteria;
import me.zrxjava.generator.dto.UpdateTableDto;
import me.zrxjava.generator.service.ITableService;
import me.zrxjava.generator.vo.TableDetailVo;
import me.zrxjava.generator.vo.TableVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

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
    public ResponseResult<Page<TableVo>> dbList(String tableName){
        return ResponseResult.success(tableService.selectDbList(tableName));
    }


    @PostMapping("/import")
    @ApiOperation("导入表结构")
    @Log(title = "系统工具",businessType = BusinessType.IMPORT)
    @PreAuthorize("@ps.check('tool:gen:import')")
    public ResponseResult<Boolean> importTable(@NotBlank String[] tableNames,
                                               Authentication authentication){
        return ResponseResult.setBody(tableService.importTable(tableNames,authentication.getName()));
    }

    @GetMapping("/list")
    @ApiOperation("代码生成列表")
    @PreAuthorize("@ps.check('tool:gen:list')")
    public ResponseResult<Page<TableVo>> list(TableListCriteria criteria){
        return ResponseResult.success(tableService.selectPage(criteria));
    }

    @GetMapping("/detail")
    @ApiOperation("数据库表详情")
    @PreAuthorize("@ps.check('tool:gen:detail')")
    public ResponseResult<TableDetailVo> detail(@NotNull(message = "表id不能为空")  Long tableId){
        return ResponseResult.success(tableService.detail(tableId));
    }


    @PutMapping("/update")
    @ApiOperation("数据库表修改")
    @PreAuthorize("@ps.check('tool:gen:update')")
    @Log(title = "系统工具",businessType = BusinessType.UPDATE)
    public ResponseResult<Boolean> update(@Validated UpdateTableDto dto){
        //todo 参数增加valid校验
        return ResponseResult.setBody(tableService.updateTableAndColumn(dto));
    }


    @PostMapping("/sync")
    @ApiOperation("数据库表同步")
    @PreAuthorize("@ps.check('tool:gen:sync')")
    @Log(title = "系统工具",businessType = BusinessType.SYNC)
    public ResponseResult<Boolean> syncTable(@NotBlank(message = "表名不能为空")  String tableName,
                                             @NotNull(message = "表id不能为空") Long tableId,
                                             Authentication authentication){
        return ResponseResult.setBody(tableService.syncTable(tableName,tableId,authentication.getName()));
    }

    @DeleteMapping
    @ApiOperation("数据库表删除")
    @PreAuthorize("@ps.check('tool:gen:delete')")
    @Log(title = "系统工具",businessType = BusinessType.DELETE)
    public ResponseResult<Boolean> delete(@NotEmpty(message = "缺少参数")  Set<Long> ids){
        return ResponseResult.setBody(tableService.delete(ids));
    }

    @GetMapping("/preview")
    @ApiOperation("代码生成预览")
    @PreAuthorize("@ps.check('tool:gen:preview')")
    @Log(title = "系统工具",businessType = BusinessType.OTHER)
    public ResponseResult<Map<String, String>> preview(@NotEmpty(message = "缺少参数") Long tableId){
        return ResponseResult.success(tableService.preview(tableId));
    }



}

