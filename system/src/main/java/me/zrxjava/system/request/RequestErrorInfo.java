package me.zrxjava.system.request;

import lombok.Data;

/**
 * @author void
 * @create 2020-10-19
 */
@Data
public class RequestErrorInfo {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private RuntimeException exception;

}
