package me.zrxjava.system.support.aspect;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import me.zrxjava.common.annotation.Log;
import me.zrxjava.common.enums.BusinessStatus;
import me.zrxjava.common.enums.BusinessType;
import me.zrxjava.common.utils.ServletUtils;
import me.zrxjava.common.utils.SpringContextHolder;
import me.zrxjava.system.modules.entity.SysLog;
import me.zrxjava.system.modules.event.SysLogEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 日志切面
 * @author void
 * @create 2020-11-06
 */
@Aspect
@Component
public class LogAspect {


    @Pointcut("@annotation(me.zrxjava.common.annotation.Log) || execution(* me.zrxjava..*.controller..*(..)) ")
    public void aspect() {
    }

    @Around(value = "aspect()")
    @SneakyThrows
    public Object execute(ProceedingJoinPoint pjp) {
        Long startTime = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        Method method = methodSignature.getMethod();
        HttpServletRequest request = ServletUtils.getRequest();
        // 设置操作用户相关信息
        SysLog sysLog = setUserInfo(pjp,request);
        Log log = method.getAnnotation(Log.class);
        if (null != log){
            sysLog.title(log.title()).businessType(log.businessType().ordinal());
        }else {
            if (null != method.getAnnotation(ApiOperation.class)){
                sysLog.title(method.getAnnotation(ApiOperation.class).value()).businessType(BusinessType.OTHER.ordinal());
            }
        }
        Object obj;
        try {
            obj = pjp.proceed();
            sysLog.jsonResult(JSON.toJSONString(obj));
        }
        catch (Exception e) {
            sysLog.status(BusinessStatus.FAIL.ordinal());
            sysLog.errorMsg(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
            throw e;
        }
        finally {
            Long endTime = System.currentTimeMillis();
            sysLog.costTime(endTime - startTime);
            // 发送异步日志事件
            SpringContextHolder.publishEvent(new SysLogEvent(sysLog));
        }
        return obj;
    }

    /**
     * 设置操作用户相关信息
     * @param pjp
     * @param request
     * @return
     */
    public SysLog setUserInfo(JoinPoint pjp,HttpServletRequest request){
        return new SysLog()
                .ip(ServletUtil.getClientIP(request))
                .requestUri(URLUtil.getPath(request.getRequestURI()))
                .userAgent(UserAgentUtil.parse(request.getHeader("user-agent")).getBrowser().getName())
                .method(request.getMethod())
                .params(getRequestParam(pjp,request))
                .status(BusinessStatus.SUCCESS.ordinal());
    }

    /**
     * 异常堆栈信息转为字符串
     * @param exceptionName
     * @param exceptionMessage
     * @param elements
     * @return
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuilder errMsg = new StringBuilder();
        for (StackTraceElement stet : elements) {
            errMsg.append(stet).append("\n");
        }
        return exceptionName + ":" + exceptionMessage + "\n\t" + errMsg.toString();
    }


    /**
     * 获取请求的参数，放到log中
     *
     * @param request
     */
    public String getRequestParam(JoinPoint joinPoint,HttpServletRequest request) {
        String requestMethod = request.getMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))
        {
            Object[] paramsArray = joinPoint.getArgs();
            StringBuilder params = new StringBuilder();
            if (paramsArray != null && paramsArray.length > 0)
            {
                for (Object o : paramsArray) {
                    if (!isFilterObject(o)) {
                        Object jsonObj = JSON.toJSON(o);
                        params.append(jsonObj.toString()).append(" ");
                    }
                }
            }
            return params.toString().trim();
        }
        return  HttpUtil.toParams(request.getParameterMap());
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    public boolean isFilterObject(final Object o)
    {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }



}
