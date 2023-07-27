package com.cvte.logsystem.db_mysql.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 管理员操作时检查token是否合法
 * @Classname CheckLogin
 * @Date 2023/7/21 10:25 AM
 * @Created by liushenghao
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLogin {
}
