package me.zrxjava.system.modules.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zrxjava.common.base.ResponseResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 * @author void
 * @create 2020-11-28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {


    @DeleteMapping("/token/logout")
    @ApiOperation("退出登录")
    public ResponseResult<Boolean> logout(){

        return ResponseResult.setBody(true);
    }

}
