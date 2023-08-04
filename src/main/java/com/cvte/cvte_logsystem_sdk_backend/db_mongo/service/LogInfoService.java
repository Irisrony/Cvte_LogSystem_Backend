package com.cvte.cvte_logsystem_sdk_backend.db_mongo.service;

import com.cvte.cvte_logsystem_sdk_backend.db_mongo.domain.Info;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.domain.LogInfo;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.exception.AppException;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.cvte_logsystem_sdk_backend.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;

/**
 * @Description TODO
 * @Classname LogInfoService
 * @Date 2023/8/4 9:54 AM
 * @Created by liushenghao
 */
@Service
@Slf4j
public class LogInfoService {
    @Autowired
    private MongoRepositoryImpl mongoRepository;

    public Boolean saveOrUpsert(String appid, String userid, LinkedList<Info> infos) {
        try {
            if(!mongoRepository.hasCollectionName(appid)){
                throw new AppException(ResultCode.APPID_NOT_EXIST);
            }
            LogInfo logInfo = new LogInfo(new ObjectId(new Date()),appid,userid,infos);
            mongoRepository.save(logInfo,appid);
            return true;
        } catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }
}
