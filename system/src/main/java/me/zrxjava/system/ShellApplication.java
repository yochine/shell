package me.zrxjava.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author void
 * @create 2020-09-16
 */
@SpringBootApplication
public class ShellApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShellApplication.class,args);
    }
}
