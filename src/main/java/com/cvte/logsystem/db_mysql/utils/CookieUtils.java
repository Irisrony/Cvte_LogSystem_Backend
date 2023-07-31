package com.cvte.logsystem.db_mysql.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Classname CookieUtils
 * @Date 2023/7/28 10:37 AM
 * @Created by liushenghao
 */
@Component
public class CookieUtils {
    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     * @param isOnly
     */
    public static void set(HttpServletResponse response,String name,String value,int maxAge,boolean isOnly){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(isOnly);
        response.addCookie(cookie);
    }

    /**
     * 获取Cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }
        return null;
    }

    /**
     * 将Cookie封装成Map
     * @param request
     * @return
     */
    public static Map<String, Cookie> readCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        Arrays.stream(cookies).forEach(cookie -> cookieMap.put(cookie.getName(),cookie));
        return cookieMap;
    }
}
