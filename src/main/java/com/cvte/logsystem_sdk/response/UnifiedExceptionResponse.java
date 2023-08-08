package com.cvte.logsystem_sdk.response;

import com.cvte.logsystem_sdk.exception.AppException;
import com.cvte.logsystem_sdk.exception.ArgsException;
import com.cvte.logsystem_sdk.exception.UserInfoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @Description TODO
 * @Classname ControllerExceptionAdivce
 * @Date 2023/8/4 2:49 PM
 * @Created by liushenghao
 */
@Slf4j
@RestControllerAdvice
public class UnifiedExceptionResponse extends BasicResponse {

    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, ArgsException.class})
    protected Object methodArgNotValid(Exception e) {
        log.error(e.getMessage());
        return responseFail(ResultCode.VALIDATION_FAILED);
    }

    /**
     * 缺少参数
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected Object missingServletRequestParameter(MissingServletRequestParameterException e) {
        log.error(e.getMessage());
        return responseFail(ResultCode.MISSING_PARAM);
    }

    /**
     * 不支持的请求类型
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected Object httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage());
        return responseFail(ResultCode.UNSUPPORTED_METHOD);
    }

    /**
     * appid不存在
     * @param e
     * @return
     */
    @ExceptionHandler(AppException.class)
    protected Object appException(AppException e){
        log.error(e.toString());
        return responseFail(e.getCode(),e.getMsg());
    }

    /**
     * 用户无需上传日志
     * @param e
     * @return
     */
    @ExceptionHandler(UserInfoException.class)
    protected Object userInfoException(UserInfoException e){
        log.error(e.toString());
        return responseFail(e.getCode(),e.getMsg());
    }

    /**
     * 其他常见错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpClientErrorException.class, IOException.class, Exception.class, SQLException.class})
    protected Object commonException(Exception e) {
        log.error(e.getMessage());
        return responseFail(ResultCode.SYSTEM_ERROR);
    }
}

