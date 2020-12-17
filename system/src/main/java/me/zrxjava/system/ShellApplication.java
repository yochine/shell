package me.zrxjava.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author void
 * @create 2020-09-16
 */
@EnableCaching
@SpringBootApplication(scanBasePackages ={"me.zrxjava"})
public class ShellApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShellApplication.class,args);
    }
}
