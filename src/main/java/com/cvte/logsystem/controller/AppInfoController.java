package com.cvte.logsystem.controller;

import com.cvte.logsystem.aop.annotation.LogRecord;
import com.cvte.logsystem.aop.annotation.VerifyToken;
import com.cvte.logsystem.domain.AppInfo;
import com.cvte.logsystem.domain.UploadEntity;
import com.cvte.logsystem.service.AppInfoService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class AppInfoController {
    @Resource
    private AppInfoService appInfoService;

    /**
     * 下发appid
     * @param appInfo   app信息
     * @return  返回appid
     */
    @PostMapping("/getAppid")
    @VerifyToken
    @LogRecord
    public Map<String,String> addAppInfo(@Valid @RequestBody AppInfo appInfo){
        String appid = appInfoService.addAppInfo(appInfo.getAppName());
        Map<String,String> res = new HashMap<>();
        res.put("appid",appid);
        return res;
    }

    /**
     * 添加待上传日志用户id与对应应用id
     * @param uploadEntity  待上传用户实体
     * @return  返回处理结果
     */
    @PostMapping("/report")
    @VerifyToken
    @LogRecord
    public Map<String,Boolean> addUploadData(@Valid @RequestBody UploadEntity uploadEntity){
        Boolean flag = appInfoService.addUploadData(uploadEntity);
        Map<String,Boolean> res = new HashMap<>();
        res.put("status",flag);
        return res;
    }

    /**
     * 返回所有的appid以及对应的userid集合和总日志数
     * @return  返回所有的appid以及对应的userid集合和总日志数
     */
    @GetMapping("/idSet")
    @VerifyToken
    @LogRecord
    public Map<String, Object> getIdSet(){
        List<AppInfo> list = appInfoService.getIdSet();
        Map<String, Object> map = new HashMap<>();
        map.put("idArray",list);
        return map;
    }
}
