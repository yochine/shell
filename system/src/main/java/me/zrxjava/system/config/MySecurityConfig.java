package me.zrxjava.system.config;

import me.zrxjava.sercurity.config.SecurityConfig;
import me.zrxjava.system.filter.MyAuthenticationFilter;
import me.zrxjava.system.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

    @Bean
    public MyAuthenticationFilter myAuthenticationFilter() throws Exception {
        return  new MyAuthenticationFilter(authenticationManagerBean());
    }
}
