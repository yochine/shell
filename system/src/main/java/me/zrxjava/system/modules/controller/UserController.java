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
import me.zrxjava.system.modules.criteria.UserCriteria;
import me.zrxjava.system.modules.dto.UserDto;
import me.zrxjava.system.modules.service.IUserService;
import me.zrxjava.system.modules.vo.UserVo;
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
 * 系统用户控制器
 * 
 * @author zrxjava
 * @date 2021-04-17
 */
@Api("系统用户")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/user")
public class UserController {

    private final IUserService userService;

    /**
     * 查询系统用户列表
     */
    @PreAuthorize("@ps.check('system:user:pageList')")
    @GetMapping("/list")
    @ApiOperation("系统用户列表")
    public ResponseResult<Page<UserVo>> pageList(UserCriteria criteria){
        return ResponseResult.success(userService.selectPage(criteria));
    }

    /**
     * 获取系统用户详细信息
     */
    @ApiOperation("系统用户详情")
    @PreAuthorize("@ps.check('system:user:detail')")
    @GetMapping(value = "/{userId}")
    public ResponseResult<UserVo> detail(@PathVariable("userId") @NotNull Long userId){
        return ResponseResult.success(userService.detail(userId));
    }

    /**
     * 新增系统用户
     */
    @ApiOperation("系统用户新增")
    @PreAuthorize("@ps.check('system:user:add')")
    @Log(title = "系统用户", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody @Validated(Insert.class) UserDto dto){
        return ResponseResult.setBody(userService.add(dto));
    }

    /**
     * 修改系统用户
     */
    @ApiOperation("系统用户修改")
    @PreAuthorize("@ps.check('system:user:edit')")
    @Log(title = "系统用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseResult<Boolean> edit(@RequestBody @Validated(Update.class) UserDto dto){
        return ResponseResult.setBody(userService.edit(dto));
    }

    /**
     * 删除系统用户
     */
    @ApiOperation("系统用户删除")
    @PreAuthorize("@ps.check('system:user:delete')")
    @Log(title = "系统用户", businessType = BusinessType.DELETE)
	@DeleteMapping
    public ResponseResult<Boolean> delete(@RequestBody @NotEmpty(message = "缺少参数")  Set<Long> ids){
        return ResponseResult.setBody(userService.delete(ids));
    }
}
