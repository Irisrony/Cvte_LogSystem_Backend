package com.cvte.logsystem_sdk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cvte.logsystem_sdk.aop.annotation.VerifyArgs;
import com.cvte.logsystem_sdk.domain.Info;
import com.cvte.logsystem_sdk.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Description TODO
 * @Classname LogInfoController
 * @Date 2023/8/4 9:44 AM
 * @Created by liushenghao
 */
@RestController
@RequestMapping("/api")
public class LogInfoController {
    @Autowired
    private LogInfoService logInfoService;

    /**
     * 日志上传
     * @param parseMap
     * @return
     */
    @PostMapping("/logUpload")
    @VerifyArgs
    public Map<String,Boolean> logUpload(@RequestBody JSONObject parseMap){

        List<Object> list = (List<Object>) parseMap.get("logArray");
        String appid = parseMap.get("appid").toString();
        String userid = parseMap.get("userid").toString();

        LinkedList<Info> infos = new LinkedList<>();
        list.forEach(s -> infos.add(JSON.parseObject(JSON.toJSONString(s),Info.class)));

        boolean status = logInfoService.saveOrUpsert(appid,userid,infos);

        Map<String,Boolean> res = new HashMap<>();
        res.put("status",status);
        return res;
    }
}
