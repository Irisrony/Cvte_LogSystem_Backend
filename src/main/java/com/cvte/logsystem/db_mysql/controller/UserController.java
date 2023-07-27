package com.cvte.logsystem.db_mysql.controller;

import com.cvte.logsystem.db_mysql.aop.annotation.CheckLogin;
import com.cvte.logsystem.db_mysql.exception.LoginException;
import com.cvte.logsystem.db_mysql.service.UserService;
import com.cvte.logsystem.db_mysql.domain.User;
import com.cvte.logsystem.db_mysql.response.ResultCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String,String> login(@Valid @RequestBody User user) {
        Map<String, String> res = userService.login(user);
        if (!res.containsKey("token")) {
            throw new LoginException(ResultCode.USER_LOGIN_ERROR);
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes)(RequestContextHolder.getRequestAttributes());
        HttpServletResponse response = attributes.getResponse();
        response.addCookie(new Cookie("token",res.get("token")));
        return new HashMap<>();
    }

    @GetMapping("/test")
    @CheckLogin
    public String test(@RequestParam(required = true) String param) {
        return param;
    }

    //注册
    @PostMapping("/registry")
    public String register(@Valid @RequestBody User user) {
        return userService.register(user);
    }

}
