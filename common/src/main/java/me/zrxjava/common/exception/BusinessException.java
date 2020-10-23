package me.zrxjava.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 系统业务异常
 * @author void
 * @create 2020-10-23
 */
@Getter
public class BusinessException extends RuntimeException {

    private Integer status = INTERNAL_SERVER_ERROR.value();

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(HttpStatus status,String msg){
        super(msg);
        this.status = status.value();
    }
}
