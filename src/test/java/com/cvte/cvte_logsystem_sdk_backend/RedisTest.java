package com.cvte.cvte_logsystem_sdk_backend;

import com.cvte.cvte_logsystem_sdk_backend.db_mongo.domain.Info;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.domain.LogInfo;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.cvte_logsystem_sdk_backend.db_redis.repositoryImpl.RedisRepositoryImpl;
import com.mysql.cj.log.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class RedisTest {
    @Autowired
    private RedisRepositoryImpl redisRepository;
    @Autowired
    private MongoRepositoryImpl mongoRepository;

    @Test
    void zsetTest() {
        redisRepository.setZSetValue("fPhlM37R","user1",new Date().getTime());
        redisRepository.setZSetValue("fPhlM37R","user2",new Date().getTime());
        redisRepository.setZSetValue("fPhlM37R","user3",new Date().getTime());
        redisRepository.setZSetValue("fPhlM37R","user4",new Date().getTime());
        System.out.println(redisRepository.getZSet("fPhlM37R", 0, -1));
    }

    @Test
    void mongoInitTest1(){
        String userid = "1234";
        String appid = "12345678";
        List<Info> list = new ArrayList<>();
        list.add(new Info(0,0,"111"));
        list.add(new Info(0,1,"111"));
        list.add(new Info(0,2,"111"));
        LogInfo logInfo = new LogInfo(userid,list);
        try{
            mongoRepository.save(logInfo,appid);
        }catch (Exception e){
            e.printStackTrace();
            mongoRepository.singleUpsert("userid",userid,"infos",logInfo.getInfos(), LogInfo.class,appid);
        }
    }

    @Test
    void mongoInitTest2(){
        String userid = "1234";
        String appid = "12345679";
        List<Info> list = new ArrayList<>();
        list.add(new Info(1,0,"111"));
        list.add(new Info(1,1,"111"));
        list.add(new Info(1,2,"111"));
        LogInfo logInfo = new LogInfo(userid,list);
        try{
            mongoRepository.save(logInfo,appid);
        }catch (Exception e){
            e.printStackTrace();
            mongoRepository.singleUpsert("userid",userid,"infos",logInfo.getInfos(), LogInfo.class,appid);
        }
    }

    @Test
    void mongoFindTest(){
        LogInfo logInfo = (LogInfo) mongoRepository.queryFindOne("userid","123", LogInfo.class,"12345678");
        List<Info> list = logInfo.getInfos();
        for (Info info : list) {
            System.out.println(info);
        }
        System.out.println(logInfo);
    }

    @Test
    void mongoFindAllTest(){
        List<Object> objs = mongoRepository.queryFind("userid","123",LogInfo.class);
        System.out.println(objs.size());
        List<LogInfo> list =  objs.stream().map(s -> (LogInfo) s).toList();
        list.forEach(s -> s.getInfos().forEach(System.out::println));
    }

}
