package me.zrxjava.system.support.aspect;

import lombok.Data;

/**
 * @author void
 * @create 2020-10-19
 */
@Data
public class RequestInfo {

    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private Object result;
    private Long timeCost;
}
