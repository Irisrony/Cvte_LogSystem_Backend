package com.cvte.logsystem_sdk.aop.aspect;

import com.alibaba.fastjson.JSONObject;
import com.cvte.logsystem_sdk.exception.ArgsException;
import com.cvte.logsystem_sdk.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description TODO
 * @Classname VerifyArgsAspect
 * @Date 2023/8/8 10:48 AM
 * @Created by liushenghao
 */
@Aspect
@Component
@Slf4j
public class VerifyArgsAspect {
    @Pointcut("execution(@com.cvte.logsystem_sdk.aop.annotation.VerifyArgs * *(..))")
    public void methodPointCut() {
    }

    @Before("methodPointCut()")
    public void argsCheck(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        if (objects == null){
            throw new ArgsException(ResultCode.VALIDATION_FAILED);
        }
        Arrays.stream(objects).forEach(o -> {
            JSONObject parseMap = (JSONObject) o;
            if (!parseMap.containsKey("logArray") || !parseMap.containsKey("appid") || parseMap.get("appid").toString().length() != 8 || !parseMap.containsKey("userid")){
                throw new ArgsException(ResultCode.VALIDATION_FAILED);
            }
        });
    }
}
