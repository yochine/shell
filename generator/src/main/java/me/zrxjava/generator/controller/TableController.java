package me.zrxjava.generator.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.zrxjava.generator.service.ITableService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/table")
public class TableController {


    private final ITableService tableService;



}

