package com.cvte.logsystem.service;

import com.cvte.logsystem.domain.LogInfo;
import com.cvte.logsystem.mongo.repositoryImpl.MongoRepositoryImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInfoService {
    @Resource
    private MongoRepositoryImpl mongoRepository;

    private final static String KEY = "userid";

    private final static String REGEX_TAG = "msg";

    /**
     * 获取当前日志总数
     * @param collectionName    列名
     * @return  日志总数
     */
    public Long getTotal(String userid,String regex,String collectionName){
        return mongoRepository.getCollectionSize(userid,regex,collectionName);
    }

    /**
     * 获取日志信息
     * @param pageNum   页数
     * @param pageSize  每页大小
     * @param appid 应用id
     * @param userid    用户id
     * @param content   模糊查询内容
     * @return  日志集合
     */
    public List<LogInfo> sendLog(Integer pageNum,Integer pageSize,String appid,String userid,String content){
        return mongoRepository.findAllByPage(pageNum,pageSize,KEY,userid,REGEX_TAG,content,LogInfo.class,appid);
    }
}
