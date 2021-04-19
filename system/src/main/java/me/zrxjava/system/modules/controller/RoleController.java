package me.zrxjava.system.modules.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.annotation.Log;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.enums.BusinessType;
import me.zrxjava.common.validated.group.Insert;
import me.zrxjava.common.validated.group.Update;
import me.zrxjava.system.modules.criteria.RoleCriteria;
import me.zrxjava.system.modules.dto.RoleDto;
import me.zrxjava.system.modules.service.IRoleService;
import me.zrxjava.system.modules.vo.RoleVo;
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
import java.util.Set;


/**
 * 角色控制器
 * 
 * @author zrxjava
 * @date 2021-04-12
 */
@Api("角色")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/role")
public class RoleController {

    private final IRoleService roleService;

    /**
     * 查询角色列表
     */
    @PreAuthorize("@ps.check('system:role:pageList')")
    @GetMapping("/list")
    @ApiOperation("角色列表")
    public ResponseResult<Page<RoleVo>> pageList(RoleCriteria criteria){
        return ResponseResult.success(roleService.selectPage(criteria));
    }

    /**
     * 获取角色详细信息
     */
    @ApiOperation("角色详情")
    @PreAuthorize("@ps.check('system:role:detail')")
    @GetMapping(value = "/{roleId}")
    public ResponseResult<RoleVo> detail(@PathVariable("roleId") @NotNull Long roleId){
        return ResponseResult.success(roleService.detail(roleId));
    }

    /**
     * 新增角色
     */
    @ApiOperation("角色新增")
    @PreAuthorize("@ps.check('system:role:add')")
    @Log(title = "角色", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Validated(Insert.class) RoleDto dto){
        return ResponseResult.setBody(roleService.add(dto));
    }

    /**
     * 修改角色
     */
    @ApiOperation("角色修改")
    @PreAuthorize("@ps.check('system:role:edit')")
    @Log(title = "角色", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseResult<Boolean> edit(@RequestBody @Validated(Update.class) RoleDto dto){
        return ResponseResult.setBody(roleService.edit(dto));
    }

    /**
     * 删除角色
     */
    @ApiOperation("角色删除")
    @PreAuthorize("@ps.check('system:role:delete')")
    @Log(title = "角色", businessType = BusinessType.DELETE)
	@DeleteMapping
    public ResponseResult<Boolean> delete(@RequestBody @NotEmpty(message = "缺少参数")  Set<Long> ids){
        return ResponseResult.setBody(roleService.delete(ids));
    }
}
