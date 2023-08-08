package com.cvte.logsystem_sdk.service;

import com.cvte.logsystem_sdk.db_redis.repositoryImpl.RedisRepositoryImpl;
import com.cvte.logsystem_sdk.domain.Info;
import com.cvte.logsystem_sdk.domain.LogInfo;
import com.cvte.logsystem_sdk.db_mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.logsystem_sdk.exception.AppException;
import com.cvte.logsystem_sdk.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
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

    @Autowired
    private RedisRepositoryImpl redisRepository;

    /**
     * 保存日志信息
     * @param appid
     * @param userid
     * @param infos
     * @return
     */
    public Boolean saveOrUpsert(String appid, String userid, LinkedList<Info> infos) {
        // 检查appid是否存在
        if(!redisRepository.setHasKey("appid",appid)){
            throw new AppException(ResultCode.APPID_NOT_EXIST);
        }
        Date now = new Date();
        List<LogInfo> list = new ArrayList<>();
        infos.forEach(info -> list.add(new LogInfo(new ObjectId(now), appid, userid, info)));
        // 保存日志
        mongoRepository.insert(list,appid);
        mongoRepository.insert(list,"allLogs");
        return true;
    }
}
