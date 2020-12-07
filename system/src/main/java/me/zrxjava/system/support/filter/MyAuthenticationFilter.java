package me.zrxjava.system.support.filter;

import com.alibaba.fastjson.JSON;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.enums.ResultCode;
import me.zrxjava.common.utils.ServletUtils;
import me.zrxjava.system.modules.login.bo.LoginUser;
import me.zrxjava.system.modules.login.bo.AdminUserDetails;
import me.zrxjava.system.support.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

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

    @Value("${login.privateKey}")
    private String privateKey;

    @Value("${login.publicKey}")
    private String publicKey;

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
            //解密
            return this.getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
        } catch (BadCredentialsException e) {
            log.error("用户或密码错误",e);
            ServletUtils.renderString(response, ResultCode.FAILED.getCode(), JSON.toJSONString(ResponseResult.failed("用户或密码错误")));
        } catch (Exception e){
            log.error("系统内部错误",e);
            ServletUtils.renderString(response, ResultCode.FAILED.getCode(), JSON.toJSONString(ResponseResult.failed("系统内部错误")));
        }
            return null;
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
        HashMap<String,Object> map = Maps.newHashMap();
        AdminUserDetails adminUserDetails = (AdminUserDetails)authResult.getPrincipal();
        String token = jwtTokenUtil.generateToken(adminUserDetails);
        map.put("token",token);
        map.put("username",authResult.getName());
        map.put("user",adminUserDetails.getUser());
        map.put("authorities",adminUserDetails.getGrantedAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(authResult);
        ServletUtils.renderString(response, ResultCode.SUCCESS.getCode(), JSON.toJSONStringWithDateFormat(ResponseResult.success(map),JSON.DEFFAULT_DATE_FORMAT));
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
