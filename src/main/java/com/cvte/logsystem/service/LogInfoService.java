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

    @Autowired
    private RedisRepositoryImpl redisRepository;

    public List<LogInfo> sendLog(Integer pageNum,Integer pageSize,String appid,String userid,String content){
        if (Strings.isBlank(appid) && Strings.isBlank(userid)){
            redisRepository.removeZSetValueByScore("uploaded", Long.MIN_VALUE,new Date().getTime() - 24*60*60*3);
            Set<String> set = redisRepository.getZSet("uploaded", (long) (pageNum - 1) *pageSize, (long) pageNum *pageSize).stream().map(s -> (String) s).collect(Collectors.toSet());
            List<LogInfo> list = new ArrayList<>();
            for (String s : set) {
                String[] info = s.split("_");
                list.add(mongoRepository.findById(info[1], LogInfo.class,info[0]));
            }
            return list;
        }
        return mongoRepository.findAllByPage(pageNum,pageSize,"userid",userid,null,content,LogInfo.class,appid);
    }
}
