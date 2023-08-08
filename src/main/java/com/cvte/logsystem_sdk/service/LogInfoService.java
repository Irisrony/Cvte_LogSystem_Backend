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
    private MongoRepositoryImpl<LogInfo> mongoRepository;

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
        if(!redisRepository.setHasKey("appid",appid)){
            throw new AppException(ResultCode.APPID_NOT_EXIST);
        }
        Date now = new Date();
        List<LogInfo> list = new ArrayList<>();
        Set<DefaultTypedTuple<String>> set = new HashSet<>();
        infos.forEach(info -> {
            ObjectId objectId = new ObjectId(now);
            list.add(new LogInfo(objectId, appid, userid, info));
            set.add(new DefaultTypedTuple<>(appid+"_"+objectId, (double) now.getTime()));
        });
        mongoRepository.insert(list,appid);
        redisRepository.setZSetValues("uploaded",set);
        return true;
    }
}
