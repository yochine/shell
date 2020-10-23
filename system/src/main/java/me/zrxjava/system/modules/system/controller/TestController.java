package me.zrxjava.system.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.annotation.AccessLimit;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.exception.BusinessException;
import me.zrxjava.system.modules.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author void
 * @create 2020-09-18
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IUserService userService;

    @GetMapping("/1")
    @AccessLimit(perSecond = 0.1,timeOut = 3000)
    public ResponseResult test(String name){
        log.info("进入test/1方法，name：{}",name);
        userService.test(name);
//        throw  new BusinessException("这是一个错误示例");
        return ResponseResult.success(name);
    }

    @GetMapping("/2")
    public ResponseResult test1(String name){
        return ResponseResult.success(name);
    }

    @PostMapping("/3")
    public void test1(String username,String password){
        System.out.println("hello world" + username);
    }
}
