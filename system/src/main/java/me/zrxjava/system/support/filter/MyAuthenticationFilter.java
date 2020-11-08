package me.zrxjava.system.support.filter;

import com.alibaba.fastjson.JSON;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.sercurity.bo.LoginUser;
import me.zrxjava.sercurity.utils.JwtTokenUtil;
import me.zrxjava.system.support.bo.AdminUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author void
 * @create 2020-09-19
 */
@Log4j2
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CaptchaService captchaService;

    public MyAuthenticationFilter(AuthenticationManager authenticationManager){
        this.setAuthenticationManager(authenticationManager);
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 从流中获取登录数据
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            CaptchaVO captchaVO = new CaptchaVO();
            captchaVO.setCaptchaVerification(loginUser.getCaptchaVerification());
            ResponseModel responseModel = captchaService.verification(captchaVO);
            if(!responseModel.isSuccess()){
                //验证码校验失败，返回信息告诉前端
                //repCode  0000  无异常，代表成功
                //repCode  9999  服务器内部异常
                //repCode  0011  参数不能为空
                //repCode  6110  验证码已失效，请重新获取
                //repCode  6111  验证失败
                //repCode  6112  获取验证码失败,请联系管理员
            }
            return this.getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            log.error("获取登录信息失败",e);
            throw new AuthenticationServiceException("只接収json格式");
        }

    }

    /**
     * 登录成功后返回token相关信息
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        HashMap<String,Object> map = Maps.newHashMap();
        AdminUserDetails adminUserDetails = (AdminUserDetails)authResult.getPrincipal();
        String token = jwtTokenUtil.generateToken(authResult);
        map.put("token",token);
        map.put("username",authResult.getName());
        map.put("user",adminUserDetails.getUser());
        SecurityContextHolder.getContext().setAuthentication(authResult);
        response.getWriter().write(JSON.toJSONStringWithDateFormat(ResponseResult.success(map),JSON.DEFFAULT_DATE_FORMAT));
    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        log.error("登录失败：{}",failed.getMessage());
        response.getWriter().write(failed.getMessage());

    }
}
