package me.zrxjava.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.zrxjava.common.enums.ResultCode;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * @author zhongrongxin
 */
@Data
@ApiModel("返回结果")
public class ResponseResult<T> implements Serializable {
    @ApiModelProperty("状态码")
    private int code;
    @ApiModelProperty("信息")
    private String message;
    @ApiModelProperty("返回对象")
    private T data;

    private String traceId;

    /**
     * 日志跟踪标识
     */
    private static final String TRACE_ID = "traceId";

    public ResponseResult(int code, String message, T data) {
        this.traceId = MDC.get(TRACE_ID);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> ResponseResult<T> success(T data, String message) {
        return new ResponseResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param resultCode 错误码
     */
    public static <T> ResponseResult<T> failed(ResultCode resultCode) {
        return new ResponseResult<T>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param resultCode 错误码
     * @param message 错误信息
     */
    public static <T> ResponseResult<T> failed(ResultCode resultCode, String message) {
        return new ResponseResult<T>(resultCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> ResponseResult<T> failed(String message) {
        return new ResponseResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResponseResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResponseResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> ResponseResult<T> validateFailed(String message) {
        return new ResponseResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 根据返回boolean判断结果
     * @param flag
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> setBody(Boolean flag) {
        return flag ? success(null) :failed();
    }


    /**
     * 未登录返回结果
     */
    public static <T> ResponseResult<T> unauthorized(T data) {
        return new ResponseResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResponseResult<T> forbidden(T data) {
        return new ResponseResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

}
