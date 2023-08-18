package com.cvte.logsystem.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.cvte.logsystem.utils.LogUtils.httpLogInfo;

/**
 * @Description TODO
 * @Classname LogRecordAspect
 * @Date 2023/8/18 10:58 AM
 * @Created by liushenghao
 */
@Aspect
@Component
@Order(1)
public class LogRecordAspect {

    @Pointcut("execution(@com.cvte.logsystem.aop.annotation.LogRecord * *(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void logRecord(){
        try{
            httpLogInfo(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
