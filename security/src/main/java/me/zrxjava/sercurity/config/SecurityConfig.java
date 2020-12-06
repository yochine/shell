package me.zrxjava.sercurity.config;

import me.zrxjava.sercurity.component.JwtAccessDeniedHandler;
import me.zrxjava.sercurity.component.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author void
 * @create 2020-09-16
 */

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.ignoreUrl}")
    private  String[] permitPaths;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //哪些资源允许放行
        http
                // 禁用 CSRF
                .csrf().disable()
                //支持跨域
                .cors()
                .and()
                //不创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(permitPaths)
                .permitAll()
                // 放行OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .and()
                // 其他请求都需要登录后才能访问
                .authorizeRequests().anyRequest().authenticated()
                .and()
                // 授权异常
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint())
                .accessDeniedHandler(jwtAccessDeniedHandler());

    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    @Bean
    public JwtAccessDeniedHandler jwtAccessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }


}
