package com.cvte.logsystem.db_mysql.controller;

import com.cvte.logsystem.db_mysql.aop.annotation.VerifyToken;
import com.cvte.logsystem.db_mysql.domain.AppInfo;
import com.cvte.logsystem.db_mysql.domain.UploadEntity;
import com.cvte.logsystem.db_mysql.service.AppInfoService;
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
    public Map<String,Boolean> addUploadData(@Valid @RequestBody UploadEntity uploadEntity){
        Boolean flag = appInfoService.addUploadData(uploadEntity);
        Map<String,Boolean> res = new HashMap<>();
        res.put("status",flag);
        return res;
    }

    @GetMapping("/idSet")
    @VerifyToken
    public Map<String, List<AppInfo>> getIdSet(){
        List<AppInfo> list = appInfoService.getIdSet();
        Map<String, List<AppInfo>> map = new HashMap<>();
        map.put("idArray",list);
        return map;
    }
}
