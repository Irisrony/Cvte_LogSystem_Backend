package com.cvte.logsystem.service;

import com.cvte.logsystem.db_mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.logsystem.domain.AppInfo;
import com.cvte.logsystem.domain.LogInfo;
import com.cvte.logsystem.domain.UploadEntity;
import com.cvte.logsystem.exception.AppInfoException;
import com.cvte.logsystem.db_mysql.mapper.AppInfoMapper;
import com.cvte.logsystem.response.ResultCode;
import com.cvte.logsystem.utils.AppUtils;
import com.cvte.logsystem.db_redis.repositoryImpl.RedisRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private RedisRepositoryImpl redisRepository;

    @Autowired
    private MongoRepositoryImpl mongoRepository;

    /**
     * 生成新应用id并更新到数据库
     * @param appName 应用名称
     * @return  应用唯一appid
     */
    public String addAppInfo(String appName){
        String appid = AppUtils.getAppid();
        if(Strings.isBlank(appid) || appid.length() != 8){
            log.error("Create appid for " + appName + "failed!");
            throw new AppInfoException(ResultCode.APPID_CREATE_FAILED);
        }else if (appInfoMapper.addAppInfo(new AppInfo(appid,appName)) != 1){
            log.error("Add { " + appid + " : " + appName + " } to database failed!");
        }
        // 更新redis
        redisRepository.setSetValue("appid",appid);
        log.info("Add { " + appid + " : " + appName + " } to database succeed!");
        return appid;
    }

    /**
     * 添加待上传用户及对应appid
     * @param uploadEntity  待上传用户实体
     * @return  上传状态
     */
    public Boolean addUploadData(UploadEntity uploadEntity){
        if (appInfoMapper.checkExist(uploadEntity.getAppid()) != 1){
            log.warn("Appid : " + uploadEntity.getAppid() + " 不存在！");
            throw new AppInfoException(ResultCode.APPID_NOT_EXIST);
        }else if (appInfoMapper.addUploadData(uploadEntity) != 1){
            log.error("Add " + uploadEntity + " to database failed!");
        }
        return true;
    }

    /**
     * 获取所有appid信息
     * @return
     */
    public List<AppInfo> getAllAppInfo(){
        return appInfoMapper.getAllAppInfo();
    }

    /**
     * 获取appid与对应的userid列表
     * @return
     */
    public List<AppInfo> getIdSet(){
        List<AppInfo> list = getAllAppInfo();
        for (AppInfo appInfo : list) {
            String appid = appInfo.getAppid();
            List<LogInfo> userList = mongoRepository.findOneField("userid", LogInfo.class,appid);
            appInfo.setUserid(userList.stream().map(LogInfo::getUserid).collect(Collectors.toSet()));
        }
        return list;
    }

}
