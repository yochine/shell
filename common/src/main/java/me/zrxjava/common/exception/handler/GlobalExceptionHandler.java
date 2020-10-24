
package me.zrxjava.common.exception.handler;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.base.ResponseResult;
import me.zrxjava.common.exception.BadRequestException;
import me.zrxjava.common.exception.BusinessException;
import me.zrxjava.common.exception.EntityExistException;
import me.zrxjava.common.exception.EntityNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


/**
 * 全局异常处理
 * @author void
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseResult handleException(Throwable e){
        // 打印堆栈信息
        log.error("Throwable ->",e);
        return ResponseResult.failed(BAD_REQUEST.value(),e.getMessage());
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult runtimeException(RuntimeException e) {
        // 打印堆栈信息
        log.error("RuntimeException ->",e);
        return ResponseResult.failed(BAD_REQUEST.value(),e.getMessage());
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult businessException(BusinessException e) {
        // 打印堆栈信息
        log.error("BusinessException ->",e);
        return ResponseResult.failed(INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

    /**
     * 处理自定义异常
     */
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseResult badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error("BadRequestException ->",e);
        return ResponseResult.failed(BAD_REQUEST.value(),e.getMessage());
	}

    /**
     * 处理 EntityExist
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseResult entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error("EntityExistException ->",e);
        return ResponseResult.failed(BAD_REQUEST.value(),e.getMessage());
    }

    /**
     * 处理 EntityNotFound
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseResult entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error("EntityNotFoundException ->",e);
        return ResponseResult.failed(BAD_REQUEST.value(),e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error("MethodArgumentNotValidException ->",e);
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            sb.append("[").append(fieldName).append("]").append(error.getDefaultMessage()).append(";  ");
        });
        String str = StrUtil.format("参数未通过校验:{}", sb.toString());
        return ResponseResult.failed(INTERNAL_SERVER_ERROR.value(),str);
    }

}
