package com.cvte.logsystem.controller;

import com.cvte.logsystem.aop.annotation.LogRecord;
import com.cvte.logsystem.service.UserService;
import com.cvte.logsystem.domain.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 管理员登陆
     * @param user  用户登陆实体
     * @param response  响应
     * @return  返回处理结果
     */
    @PostMapping("/login")
    @LogRecord
    public Map<String,Boolean> login(@Valid @RequestBody User user, HttpServletResponse response) {

        Boolean status = userService.login(user,response);

        Map<String,Boolean> res = new HashMap<>();
        res.put("status",status);

        return res;
    }

    /**
     * 管理员登出
     * @param response  响应
     * @return  返回处理结果
     */
    @PostMapping("/logout")
    @LogRecord
    public Map<String,Boolean> logout(HttpServletResponse response) {

        Boolean status = userService.logout(response);

        Map<String,Boolean> res = new HashMap<>();
        res.put("status",status);

        return res;
    }

}
