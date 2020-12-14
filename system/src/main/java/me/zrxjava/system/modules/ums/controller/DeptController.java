package me.zrxjava.system.modules.ums.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.system.modules.ums.service.IDeptService;
import me.zrxjava.system.modules.ums.vo.DeptTree;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author void
 * @since 2020-09-20
 */
@RestController
@RequiredArgsConstructor
@Api(value = "部门管理", tags = "部门服务")
@RequestMapping("/dept")
public class DeptController {


    private final IDeptService deptService;

    @GetMapping("/tree")
    @ApiOperation("查询组织树")
    @PreAuthorize("@ps.check('ums:dept:tree')")
    public ResponseResult<List<DeptTree>> tree(){
        return ResponseResult.success(deptService.buildTree());
    }

}

