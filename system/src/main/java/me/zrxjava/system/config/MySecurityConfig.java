package me.zrxjava.system.config;

import me.zrxjava.sercurity.config.SecurityConfig;
import me.zrxjava.system.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author void
 * @create 2020-09-19
 */
@EnableWebSecurity
public class MySecurityConfig extends SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

}
