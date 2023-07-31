package com.cvte.logsystem.db_mysql.aop.aspect;

import com.cvte.logsystem.db_mysql.exception.AuthException;
import com.cvte.logsystem.db_mysql.utils.CookieUtils;
import com.cvte.logsystem.db_mysql.utils.JwtUtils;
import com.cvte.logsystem.db_mysql.response.ResultCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description TODO
 * @Classname CheckLoginAspect
 * @Date 2023/7/21 10:28 AM
 * @Created by liushenghao
 */

@Aspect
@Component
@Slf4j
public class VerifyTokenAspect {
    @Pointcut("execution(@com.cvte.logsystem.db_mysql.aop.annotation.VerifyToken * *(..))")
    public void methodPointCut() {
    }

    @Before("methodPointCut()")
    public void loginCheck() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attributes.getRequest();
            Cookie cookie = CookieUtils.get(request,"token");
            String token = cookie == null ? null : cookie.getValue();
            if(token == null || !JwtUtils.verifyToken(token)){
                throw new AuthException(ResultCode.UNAUTHORIZED);
            }
        } catch (Exception e) {
            throw new AuthException(ResultCode.UNAUTHORIZED);
        }
    }
}
