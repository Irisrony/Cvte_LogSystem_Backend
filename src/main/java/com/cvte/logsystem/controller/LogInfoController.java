package com.cvte.logsystem.controller;

import com.cvte.logsystem.aop.annotation.LogRecord;
import com.cvte.logsystem.aop.annotation.VerifyToken;
import com.cvte.logsystem.domain.LogInfo;
import com.cvte.logsystem.service.LogInfoService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class LogInfoController {
    @Resource
    private LogInfoService logInfoService;

    /**
     * 日志查询
     * @param pageNum   页数
     * @param pageSize  每页大小
     * @param appid 应用id
     * @param userid    用户id
     * @param content   模糊查询关键词
     * @return  返回日志集合
     */
    @GetMapping("/sendLog")
    @VerifyToken
    @LogRecord
    public Map<String, Object> sendLog(
            @Min(1) @RequestParam Integer pageNum, @Min(1) @RequestParam Integer pageSize, @NotBlank @RequestParam String appid,
            @NotBlank @RequestParam String userid, @RequestParam(required = false) String content){

        List<LogInfo> logs = logInfoService.sendLog(pageNum,pageSize,appid,userid,content);
        Long total = logInfoService.getTotal(userid,content,appid);

        Map<String,Object> res = new HashMap<>();
        res.put("logs",logs);
        res.put("total",total);
        return res;
    }
}
