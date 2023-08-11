package com.cvte.logsystem.response;

import com.cvte.logsystem.exception.AppInfoException;
import com.cvte.logsystem.exception.AuthException;
import com.cvte.logsystem.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.security.SignatureException;
import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice extends BasicResponse {

    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
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
     * 签名验证失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SignatureException.class)
    protected Object signatureException(SignatureException e) {
        log.error(e.getMessage());
        return responseFail(ResultCode.UNAUTHORIZED);
    }

    /**
     * 权限验证失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthException.class)
    protected Object authException(AuthException e) {
        log.error(e.toString());
        return responseFail(ResultCode.UNAUTHORIZED);
    }

    /**
     * appid生成失败
     * @param e
     * @return
     */
    @ExceptionHandler(AppInfoException.class)
    protected Object appInfoException(AppInfoException e){
        log.error(e.toString());
        return responseFail(e.getCode(),e.getMsg());
    }

    /**
     * 登陆失败
     * @param e
     * @return
     */
    @ExceptionHandler(LoginException.class)
    protected Object loginException(LoginException e){
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
