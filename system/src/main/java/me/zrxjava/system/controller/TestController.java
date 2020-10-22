package me.zrxjava.system.controller;

import lombok.extern.slf4j.Slf4j;
import me.zrxjava.system.service.IUserService;
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
@RequestMapping("/")
public class TestController {

    @Autowired
    private IUserService userService;

    @GetMapping("/test/1")
    public void test(String name){
        log.info("进入test/1方法，name：{}",name);
        userService.test(name);
        int i = 1/0;
        System.out.println("hello world" + name);
    }

    @GetMapping("/login")
    public void test(String name,String password){
        System.out.println("hello world" + name);
    }

    @PostMapping("/login")
    public void test1(String username,String password){
        System.out.println("hello world" + username);
    }
}
