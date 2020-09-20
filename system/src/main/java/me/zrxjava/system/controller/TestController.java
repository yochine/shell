package me.zrxjava.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author void
 * @create 2020-09-18
 */
@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/test/1")
    public void test(String name){
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
