package me.zrxjava.sercurity.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.sercurity.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token 认证器 登录后才会执行
 * @author void
 * @create 2020-09-17
 */
@Slf4j
public class JwtTokenFilter extends BasicAuthenticationFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public JwtTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            if (StrUtil.isEmpty(jwtTokenUtil.refreshHeadToken(authHeader))) {
                throw new BadCredentialsException("非法请求");
            }
            Authentication authentication = jwtTokenUtil.getAuthentication(authHeader);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("authentication->" + authentication);

        }
        chain.doFilter(request, response);
    }

}