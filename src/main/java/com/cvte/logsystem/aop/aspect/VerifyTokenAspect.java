package com.cvte.logsystem.aop.aspect;

import com.cvte.logsystem.exception.AuthException;
import com.cvte.logsystem.redis.repositoryImpl.RedisRepositoryImpl;
import com.cvte.logsystem.utils.CookieUtils;
import com.cvte.logsystem.utils.JwtUtils;
import com.cvte.logsystem.response.ResultCode;
import com.cvte.logsystem.utils.LogUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.cvte.logsystem.utils.HttpUtils.getRequest;

@Aspect
@Component
@Order(2)
public class VerifyTokenAspect {
    @Resource
    private RedisRepositoryImpl redisRepository;

    private final static String TOKEN = "token";
    private final static String TOKEN_LIST = "tokenList";

    @Pointcut("execution(@com.cvte.logsystem.aop.annotation.VerifyToken * *(..))")
    public void methodPointCut() {
    }

    @Before("methodPointCut()")
    public void loginCheck(){
        try {
            HttpServletRequest request = getRequest();
            Cookie cookie = CookieUtils.get(request,TOKEN);
            String token = cookie == null ? null : cookie.getValue();
            if(!redisRepository.setHasKey(TOKEN_LIST,token) || !JwtUtils.verifyToken(token)){
                redisRepository.removeSetValue(TOKEN_LIST,token);
                throw new AuthException(ResultCode.UNAUTHORIZED);
            }
        } catch (Exception e) {
            LogUtils.httpLogError(e.getMessage());
            throw new AuthException(ResultCode.UNAUTHORIZED);
        }
    }
}
