package me.zrxjava.generator.controller;


import cn.hutool.core.convert.Convert;
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
import org.apache.commons.io.IOUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
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
    public ResponseResult<Boolean> importTable(@RequestParam(name = "tableNames") @NotBlank String tableNames,
                                               Authentication authentication){
        return ResponseResult.setBody(tableService.importTable(Convert.toStrArray(tableNames),authentication.getName()));
    }

    @GetMapping("/page")
    @ApiOperation("代码生成列表")
    @PreAuthorize("@ps.check('tool:gen:page')")
    public ResponseResult<Page<TableVo>> page(TableListCriteria criteria){
        return ResponseResult.success(tableService.selectPage(criteria));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("数据库表详情")
    @PreAuthorize("@ps.check('tool:gen:detail')")
    public ResponseResult<TableDetailVo> detail(@NotNull(message = "表id不能为空") @PathVariable(name = "id") Long tableId){
        return ResponseResult.success(tableService.detail(tableId));
    }


    @PutMapping("/update")
    @ApiOperation("数据库表修改")
    @PreAuthorize("@ps.check('tool:gen:update')")
    @Log(title = "系统工具",businessType = BusinessType.UPDATE)
    public ResponseResult<Boolean> update(@Validated @RequestBody UpdateTableDto dto){
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
    public ResponseResult<Boolean> delete(@RequestBody  @NotEmpty(message = "id不能为空") Set<Long> ids){
        return ResponseResult.setBody(tableService.delete(ids));
    }

    @GetMapping("/preview")
    @ApiOperation("代码生成预览")
    @PreAuthorize("@ps.check('tool:gen:preview')")
    @Log(title = "系统工具",businessType = BusinessType.OTHER)
    public ResponseResult<Map<String, String>> preview(@NotNull(message = "缺少参数") Long tableId){
        return ResponseResult.success(tableService.preview(tableId));
    }

    @GetMapping("/generate")
    @ApiOperation("代码生成")
    @PreAuthorize("@ps.check('tool:gen:generate')")
    @Log(title = "系统工具",businessType = BusinessType.GENCODE)
    public ResponseResult<Map<String, String>> generate(@NotEmpty(message = "id不能为空")  Set<Long> ids){
        return ResponseResult.setBody(tableService.generate(ids));
    }

    @GetMapping("/downLoad")
    @ApiOperation("代码生成下载")
    @PreAuthorize("@ps.check('tool:gen:downLoad')")
    @Log(title = "系统工具",businessType = BusinessType.DOWNLOAD)
    public void downLoad(HttpServletResponse response,@NotEmpty(message = "id不能为空")  Set<Long> ids) throws IOException {
        byte[] data = tableService.downLoad(ids);
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"shell.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }


}

