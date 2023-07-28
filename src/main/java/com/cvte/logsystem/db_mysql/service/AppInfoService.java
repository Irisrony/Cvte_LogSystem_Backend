package com.cvte.logsystem.db_mysql.service;

import com.cvte.logsystem.db_mysql.domain.AppInfo;
import com.cvte.logsystem.db_mysql.domain.UploadEntity;
import com.cvte.logsystem.db_mysql.exception.AppInfoException;
import com.cvte.logsystem.db_mysql.mapper.AppInfoMapper;
import com.cvte.logsystem.db_mysql.response.ResultCode;
import com.cvte.logsystem.db_mysql.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Classname AppInfoService
 * @Date 2023/7/28 11:31 AM
 * @Created by liushenghao
 */
@Service
@Slf4j
public class AppInfoService {
    @Autowired
    private AppInfoMapper appInfoMapper;

    // 生成新应用id并更新到数据库
    public String addAppInfo(String appName){
        String appid = AppUtils.getAppid();
        if(appid == null || appid.length() != 8){
            log.error("Create appid for " + appName + "failed!");
            throw new AppInfoException(ResultCode.APPID_CREATE_FAILED);
        }else if (appInfoMapper.addAppInfo(new AppInfo(appid,appName)) != 1){
            log.error("Add { " + appid + " : " + appName + " } to database failed!");
        }
        log.info("Add { " + appid + " : " + appName + " } to database succeed!");
        return appid;
    }

    // 添加待上传用户及对应应用
    public Boolean addUploadData(UploadEntity uploadEntity){
        if (appInfoMapper.checkExist(uploadEntity.getAppid()) != 1){
            log.warn("Appid : " + uploadEntity.getAppid() + " 不存在！");
            throw new AppInfoException(ResultCode.APPID_NOT_EXIST);
        }else if (appInfoMapper.addUploadData(uploadEntity) != 1){
            log.error("Add " + uploadEntity + " to database failed!");
        }
        return true;
    }
}
