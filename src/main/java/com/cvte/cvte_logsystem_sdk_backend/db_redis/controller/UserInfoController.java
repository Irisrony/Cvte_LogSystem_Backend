package com.cvte.cvte_logsystem_sdk_backend.db_redis.controller;

import com.cvte.cvte_logsystem_sdk_backend.db_redis.domain.UserInfo;
import com.cvte.cvte_logsystem_sdk_backend.db_redis.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Classname LogInfoController
 * @Date 2023/8/2 4:07 PM
 * @Created by liushenghao
 */
@RestController
@RequestMapping("/api")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/isExist")
    public Map<String,Boolean> isExist(@Valid @RequestBody UserInfo userInfo){
        Map<String,Boolean> res = new HashMap<>();
        Boolean status = userInfoService.isExist(userInfo);
        res.put("status",status);
        return res;
    }
}
