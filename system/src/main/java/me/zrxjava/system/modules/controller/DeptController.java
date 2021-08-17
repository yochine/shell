package me.zrxjava.system.modules.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.annotation.Log;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.enums.BusinessType;
import me.zrxjava.common.validated.group.Insert;
import me.zrxjava.common.validated.group.Update;
import me.zrxjava.system.modules.dto.DeptDto;
import me.zrxjava.system.modules.service.IDeptService;
import me.zrxjava.system.modules.vo.DeptVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


/**
 * 部门控制器
 * 
 * @author zrxjava
 * @date 2021-04-11
 */
@Api("部门")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dept")
public class DeptController {

    private final IDeptService deptService;

    /**
     * 查询部门列表
     */
    @PreAuthorize("@ps.check('system:dept:list')")
    @GetMapping("/list")
    @ApiOperation("部门列表")
    public ResponseResult<List<DeptVo>> list(String name){
        return ResponseResult.success(deptService.selectList(name));
    }

    /**
     * 获取部门详细信息
     */
    @ApiOperation("部门详情")
    @PreAuthorize("@ps.check('system:dept:detail')")
    @GetMapping(value = "/{deptId}")
    public ResponseResult<DeptVo> detail(@PathVariable("deptId") @NotNull Long deptId){
        return ResponseResult.success(deptService.detail(deptId));
    }

    /**
     * 新增部门
     */
    @ApiOperation("部门新增")
    @PreAuthorize("@ps.check('system:dept:add')")
    @Log(title = "部门", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Validated(Insert.class) DeptDto dto){
        return ResponseResult.setBody(deptService.add(dto));
    }

    /**
     * 修改部门
     */
    @ApiOperation("部门修改")
    @PreAuthorize("@ps.check('system:dept:edit')")
    @Log(title = "部门", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseResult<Boolean> edit(@RequestBody @Validated(Update.class) DeptDto dto){
        return ResponseResult.setBody(deptService.edit(dto));
    }

    /**
     * 删除部门
     */
    @ApiOperation("部门删除")
    @PreAuthorize("@ps.check('system:dept:delete')")
    @Log(title = "部门", businessType = BusinessType.DELETE)
	@DeleteMapping
    public ResponseResult<Boolean> delete(@RequestBody @NotEmpty(message = "缺少参数")  Set<Long> ids){
        return ResponseResult.setBody(deptService.delete(ids));
    }
}
