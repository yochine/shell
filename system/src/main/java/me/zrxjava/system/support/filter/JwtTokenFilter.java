package me.zrxjava.system.support.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.enums.ResultCode;
import me.zrxjava.common.utils.ServletUtils;
import me.zrxjava.system.support.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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

    @Autowired
    private UserDetailsService userDetailsService;

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
        String token = request.getHeader(this.tokenHeader);
        if (token != null && token.startsWith(this.tokenHead)) {
            token = token.substring(tokenHead.length());
            if (StrUtil.isEmpty(jwtTokenUtil.refreshHeadToken(token))) {
                ServletUtils.renderString(response, ResultCode.FAILED.getCode(), JSON.toJSONString(ResponseResult.failed("非法请求")));
                return;
            }
            String username = jwtTokenUtil.getUserNameFromToken(token);
            log.info("authenticated user:{}", username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

}