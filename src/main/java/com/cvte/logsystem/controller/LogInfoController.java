package com.cvte.logsystem.controller;

import com.cvte.logsystem.aop.annotation.VerifyToken;
import com.cvte.logsystem.domain.LogInfo;
import com.cvte.logsystem.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Classname LogInfoController
 * @Date 2023/8/4 3:40 PM
 * @Created by liushenghao
 */
@RestController
@RequestMapping("/user")
public class LogInfoController {
    @Autowired
    private LogInfoService logInfoService;

    @GetMapping("/sendLog")
    @VerifyToken
    public Map<String, List<LogInfo>> sendLog(
            @RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(required = false) String appid,
            @RequestParam(required = false) String userid,@RequestParam(required = false) String content){
        List<LogInfo> logs = logInfoService.sendLog(pageNum,pageSize,appid,userid,content);
        Map<String,List<LogInfo>> res = new HashMap<>();
        res.put("logs",logs);
        return res;
    }
}
