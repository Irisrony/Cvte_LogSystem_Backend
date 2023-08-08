package com.cvte.cvte_logsystem_sdk_backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cvte.cvte_logsystem_sdk_backend.domain.Info;
import com.cvte.cvte_logsystem_sdk_backend.exception.ArgsException;
import com.cvte.cvte_logsystem_sdk_backend.service.LogInfoService;
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

    @PostMapping("/logUpload")
    public Map<String,Boolean> logUpload(@RequestBody JSONObject parseMap){
        if(!parseMap.containsKey("logArray") || !parseMap.containsKey("appid") || !parseMap.containsKey("userid")){
            throw new ArgsException();
        }

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
