package com.cvte.logsystem.db_mysql.controller;

import com.cvte.logsystem.db_mysql.exception.LoginException;
import com.cvte.logsystem.db_mysql.service.UserService;
import com.cvte.logsystem.db_mysql.domain.User;
import com.cvte.logsystem.db_mysql.response.ResultCode;
import com.cvte.logsystem.db_mysql.utils.CookieUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 管理员登陆
     * @param user
     * @param response
     * @return
     */
    @PostMapping("/login")
    public Map<String,String> login(@Valid @RequestBody User user, HttpServletResponse response) {
        String token = userService.login(user);
        if (token == null) {
            throw new LoginException(ResultCode.USER_LOGIN_ERROR);
        }
        CookieUtils.set(response,"token",token,24*60*60,true);
        return new HashMap<>();
    }

    /**
     * 管理员登出
     * @param response
     * @return
     */
    @PostMapping("/logout")
    public Map<String,String> logout(HttpServletResponse response) {
        CookieUtils.set(response,"token","",0,true);
        return new HashMap<>();
    }

}
