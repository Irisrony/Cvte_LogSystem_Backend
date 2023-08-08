package com.cvte.logsystem.controller;

import com.cvte.logsystem.aop.annotation.VerifyToken;
import com.cvte.logsystem.domain.AppInfo;
import com.cvte.logsystem.domain.UploadEntity;
import com.cvte.logsystem.service.AppInfoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Classname AppController
 * @Date 2023/7/28 11:28 AM
 * @Created by liushenghao
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class AppInfoController {
    @Autowired
    private AppInfoService appInfoService;

    /**
     * 下发appid
     * @param appInfo
     * @return
     */
    @PostMapping("/getAppid")
    @VerifyToken
    public Map<String,String> addAppInfo(@Valid @RequestBody AppInfo appInfo){
        String appid = appInfoService.addAppInfo(appInfo.getAppName());
        Map<String,String> res = new HashMap<>();
        res.put("appid",appid);
        return res;
    }

    /**
     * 添加待上传日志用户id与对应应用id
     * @param uploadEntity
     * @return
     */
    @PostMapping("/report")
    @VerifyToken
    public Map<String,Boolean> addUploadData(@Valid @RequestBody UploadEntity uploadEntity){
        Boolean flag = appInfoService.addUploadData(uploadEntity);
        Map<String,Boolean> res = new HashMap<>();
        res.put("status",flag);
        return res;
    }

    /**
     * 返回所有的appid以及对应的userid集合和总日志数
     * @return
     */
    @GetMapping("/idSet")
    @VerifyToken
    public Map<String, Object> getIdSet(){
        List<AppInfo> list = appInfoService.getIdSet();
        Map<String, Object> map = new HashMap<>();
        map.put("idArray",list);
        return map;
    }
}
