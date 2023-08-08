package com.cvte.cvte_logsystem_sdk_backend.service;

import com.cvte.cvte_logsystem_sdk_backend.domain.Info;
import com.cvte.cvte_logsystem_sdk_backend.domain.LogInfo;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.repositoryImpl.MongoRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    /**
     * 保存日志信息
     * @param appid
     * @param userid
     * @param infos
     * @return
     */
    public Boolean saveOrUpsert(String appid, String userid, LinkedList<Info> infos) {
        try {
            //if(!mongoRepository.hasCollectionName(appid)){
            //    throw new AppException(ResultCode.APPID_NOT_EXIST);
            //}
            Date now = new Date();
            List<LogInfo> list = new ArrayList<>();
            infos.forEach(info -> {
                list.add(new LogInfo(new ObjectId(now),appid,userid,info));
            });
            //LogInfo logInfo = new LogInfo(new ObjectId(new Date()),appid,userid,infos);

            return true;
        } catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }
}
