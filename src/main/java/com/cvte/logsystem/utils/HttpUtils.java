package com.cvte.logsystem.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description TODO
 * @Classname HttpUtils
 * @Date 2023/8/18 10:27 AM
 * @Created by liushenghao
 */
@Component
public class HttpUtils {
    public static HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        if (attributes != null) {
            return attributes.getRequest();
        }
        return null;
    }
}
