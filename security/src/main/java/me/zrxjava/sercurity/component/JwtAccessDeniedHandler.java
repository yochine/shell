package me.zrxjava.sercurity.component;

import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.utils.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author void
 * @create 2020-09-17
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //当用户在没有授权的情况下访问受保护的REST资源时，将调用此方法发送403 Forbidden响应
        ServletUtils.renderString(response,ResponseResult.failed(HttpServletResponse.SC_FORBIDDEN,accessDeniedException.getMessage()));
    }
}
