package me.zrxjava.system.controller;


import com.diboot.core.binding.Binder;
import com.diboot.core.binding.QueryBuilder;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.system.criteria.UserCriteria;
import me.zrxjava.system.entity.User;
import me.zrxjava.system.service.IUserService;
import me.zrxjava.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/list")
    public ResponseResult<List<UserVo>> list(@RequestBody UserCriteria criteria){
        List<User> users =  userService.list(QueryBuilder.toDynamicJoinQueryWrapper(criteria));
        List<UserVo> userVos = Binder.convertAndBindRelations(users, UserVo.class);
        return ResponseResult.success(userVos);
    }

}

