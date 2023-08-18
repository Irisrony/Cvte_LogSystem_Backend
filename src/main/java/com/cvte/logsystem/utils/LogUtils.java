package com.cvte.logsystem.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.cvte.logsystem.utils.HttpUtils.getRequest;

/**
 * @Description TODO
 * @Classname LogUtils
 * @Date 2023/8/18 9:46 AM
 * @Created by liushenghao
 */
@Component
@Slf4j
public class LogUtils {

    private static String getRemote(HttpServletRequest request){
        if (request != null){
            return request.getRemoteAddr();
        }
        return null;
    }

    private static String getPath(HttpServletRequest request){
        if (request != null){
            return request.getServletPath();
        }
        return null;
    }

    public static void httpLogInfo(String msg) throws IOException {
        HttpServletRequest request = getRequest();

        String from = getRemote(request);
        String path = getPath(request);

        log.info("User_host : " + from + " - calling method : " + path + " - msg : " + msg);
    }

    public static void httpLogError(String msg) throws IOException {
        HttpServletRequest request = getRequest();

        String from = getRemote(request);
        String path = getPath(request);

        log.error("User_host : " + from + " - calling method : " + path + " - msg : " + msg);
    }
}
