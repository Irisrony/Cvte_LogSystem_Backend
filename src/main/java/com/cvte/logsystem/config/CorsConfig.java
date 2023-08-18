package com.cvte.logsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    // 当前跨域请求最大有效时长。这里默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://175.178.76.218")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(MAX_AGE);
    }

    //@Bean
    //public CorsFilter corsFilter() {
    //    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //    CorsConfiguration corsConfiguration = new CorsConfiguration();
    //    corsConfiguration.addAllowedOrigin("http://175.178.76.218"); // 1 设置访问源地址
    //    corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
    //    corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
    //    corsConfiguration.setMaxAge(MAX_AGE);
    //    source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
    //    return new CorsFilter(source);
    //}
}

