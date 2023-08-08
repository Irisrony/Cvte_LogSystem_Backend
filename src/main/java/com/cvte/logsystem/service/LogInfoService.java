package com.cvte.logsystem.service;

import com.cvte.logsystem.db_redis.repositoryImpl.RedisRepositoryImpl;
import com.cvte.logsystem.domain.LogInfo;
import com.cvte.logsystem.db_mongo.repositoryImpl.MongoRepositoryImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Classname LogInfoService
 * @Date 2023/8/4 3:56 PM
 * @Created by liushenghao
 */
@Service
public class LogInfoService {
    @Autowired
    private MongoRepositoryImpl mongoRepository;

    /**
     * 获取当前日志总数
     * @param collectionName
     * @return
     */
    public Long getTotal(String userid,String regex,String collectionName){
        return mongoRepository.getCollectionSize(userid,regex,collectionName);
    }

    /**
     * 获取日志信息
     * @param pageNum
     * @param pageSize
     * @param appid
     * @param userid
     * @param content
     * @return
     */
    public List<LogInfo> sendLog(Integer pageNum,Integer pageSize,String appid,String userid,String content){
        return mongoRepository.findAllByPage(pageNum,pageSize,"userid",userid,"msg",content,LogInfo.class,appid);
    }
}
