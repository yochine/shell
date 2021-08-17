package me.zrxjava.system.config;

import me.zrxjava.sercurity.config.SecurityConfig;
import me.zrxjava.system.support.handler.LogoutSuccessHandlerImpl;
import me.zrxjava.system.support.filter.JwtTokenFilter;
import me.zrxjava.system.support.filter.MyAuthenticationFilter;
import me.zrxjava.system.support.service.UserDetailsServiceImpl;
import me.zrxjava.system.support.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author void
 * @create 2020-09-19
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 退出处理类
     */
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Bean
    public MyAuthenticationFilter myAuthenticationFilter() throws Exception {
        return new MyAuthenticationFilter(authenticationManagerBean());
    }

    @Bean
    public JwtTokenFilter jwtFilter() throws Exception {
        return new JwtTokenFilter(authenticationManagerBean());
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // jwt过滤器
                .addFilter(jwtFilter())
                .logout().logoutUrl("/auth/token/logout").logoutSuccessHandler(logoutSuccessHandler);
        super.configure(http);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/**/*.html","/**/*.png","/**/*.js","/v2/api-docs",
                "/img/**", "/fonts/**","/webjars/**", "/favicon.ico", "/code","/swagger-resources/**");
    }

}
