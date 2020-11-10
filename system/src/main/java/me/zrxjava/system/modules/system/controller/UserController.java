package me.zrxjava.system.modules.system.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zrxjava.common.annotation.ResponseExcel;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.validated.group.Insert;
import me.zrxjava.system.modules.system.criteria.UserCriteria;
import me.zrxjava.system.modules.system.dto.UserDto;
import me.zrxjava.system.modules.system.entity.User;
import me.zrxjava.system.modules.system.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author void
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理",tags = "用户服务")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/list")
    @ApiOperation("用户列表")
    public ResponseResult<List<User>> list(UserCriteria criteria){
        List<User> users =  userService.list();
        return ResponseResult.success(users);
    }

    @GetMapping("/export")
    @ApiOperation("导出用户列表")
    @ResponseExcel(name = "user",password = "123")
    public List<User> export(UserCriteria criteria){
        List<User> users =  userService.list();
        return users;
    }

    @PostMapping("/add")
    @ApiOperation("新增用户")
    @ApiOperationSupport(ignoreParameters = {"dto.id"})
    public ResponseResult<Boolean> add(@Validated(Insert.class) @RequestBody UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return ResponseResult.setBody(userService.save(user));
    }

}

