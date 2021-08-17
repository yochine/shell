package me.zrxjava.generator.controller;


import io.swagger.annotations.ApiOperation;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.generator.service.IFormConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 表单配置 前端控制器
 * </p>
 *
 * @author void
 * @since 2021-02-05
 */
@RestController
@RequestMapping("/dev/form")
public class FormConfController {

    @Autowired
    private IFormConfService formConfService;

    @GetMapping("/conf")
    @ApiOperation("数据库表详情")
    @PreAuthorize("@ps.check('tool:gen:detail')")
    public ResponseResult<String> detail(@NotNull(message = "表名不能为空") @RequestParam(name = "tableId") Long tableId,
                                           @RequestParam(name = "dsName",required = false) String dsName){
        return ResponseResult.success(formConfService.detail(tableId,dsName));
    }
}

