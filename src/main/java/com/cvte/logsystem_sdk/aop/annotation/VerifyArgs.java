package com.cvte.logsystem_sdk.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description TODO
 * @Classname VerifyArgs
 * @Date 2023/8/8 10:47 AM
 * @Created by liushenghao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyArgs {
}
