package com.cvte.logsystem_sdk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cvte.logsystem_sdk.aop.annotation.VerifyArgs;
import com.cvte.logsystem_sdk.domain.Info;
import com.cvte.logsystem_sdk.service.LogInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class LogInfoController {
    @Resource
    private LogInfoService logInfoService;

    /**
     * 日志上传
     * @param parseMap  参数映射表
     * @return  上传状态
     */
    @PostMapping("/logUpload")
    @VerifyArgs
    public Map<String,Boolean> logUpload(@RequestBody JSONObject parseMap){

        List<Object> list = (List<Object>) parseMap.get("logArray");
        String appid = parseMap.get("appid").toString();
        String userid = parseMap.get("userid").toString();

        List<Info> infos = list.stream().map(s -> JSON.parseObject(JSON.toJSONString(s),Info.class)).toList();

        boolean status = logInfoService.singleSave(appid,userid,infos);

        Map<String,Boolean> res = new HashMap<>();
        res.put("status",status);
        return res;
    }
}
