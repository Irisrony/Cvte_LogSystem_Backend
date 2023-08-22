package com.cvte.logsystem.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    // 当前跨域请求最大有效时长。这里默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                //.allowCredentials(true)
                //.allowedOrigins("http://175.178.76.218")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(MAX_AGE);
    }

}

