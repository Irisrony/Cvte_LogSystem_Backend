package com.cvte.cvte_logsystem_sdk_backend;

import com.cvte.cvte_logsystem_sdk_backend.db_mongo.domain.Info;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.domain.LogInfo;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.cvte_logsystem_sdk_backend.db_redis.repositoryImpl.RedisRepositoryImpl;
import com.mysql.cj.log.Log;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String userid = "12345";
        String appid = "12345678";
        ObjectId objectId = new ObjectId(new Date());
        System.out.println(objectId);
        LinkedList<Info> list = new LinkedList<>();
        list.add(new Info(0,0,"111"));
        list.add(new Info(0,1,"111"));
        list.add(new Info(0,2,"111"));
        LogInfo logInfo = new LogInfo(objectId,appid,userid,list);
        try{
            mongoRepository.save(logInfo,appid);
        }catch (Exception e){
            e.printStackTrace();
            mongoRepository.singleUpsert("userid",userid,"infos",logInfo.getInfos(), LogInfo.class,appid);
        }
    }

    @Test
    void mongoInitTest2(){
        String userid = "125";
        String appid = "12345678";
        ObjectId objectId = new ObjectId(new Date());
        LinkedList<Info> list = new LinkedList<>();
        list.add(new Info(0,0,"111"));
        list.add(new Info(0,1,"111"));
        list.add(new Info(0,2,"111"));
        LogInfo logInfo = new LogInfo(objectId,appid,userid,list);
        try{
            mongoRepository.save(logInfo,appid);
        }catch (Exception e){
            e.printStackTrace();
            mongoRepository.singleUpsert("userid",userid,"infos",logInfo.getInfos(), LogInfo.class,appid);
        }
    }

    @Test
    void mongoFindTest(){
        //LogInfo logInfo = (LogInfo) mongoRepository.findOneByField("userid","123", LogInfo.class,"12345678");
        LogInfo logInfo = (LogInfo) mongoRepository.findOneByField("userid","94", LogInfo.class,"48");
        List<Info> list = logInfo.getInfos();
        for (Info info : list) {
            System.out.println(info);
        }
        System.out.println(logInfo);
    }

    @Test
    void mongoFindOneTest(){
        Object obj = mongoRepository.findOneByField("userid","134",LogInfo.class,"12345678");
        System.out.println((LogInfo)obj);
    }

    @Test
    void mongoFindAllTest1(){
        List<Object> objs = mongoRepository.findAllByField("userid","134",LogInfo.class);
        List<LogInfo> list =  objs.stream().map(s -> (LogInfo) s).toList();
        list.forEach(s -> s.getInfos().forEach(System.out::println));
    }

    @Test
    void mongoFindAllTest2(){
        List<LogInfo> list = mongoRepository.findAll(LogInfo.class).stream().map(s -> (LogInfo)s).toList();
        list.forEach(s -> s.getInfos().forEach(System.out::println));
    }

    @Test
    void mongoPageFind(){
        List<LogInfo> list = mongoRepository.findByPage(1,2, LogInfo.class,"12345678").stream().map(s -> (LogInfo)s).toList();
        list.forEach(s -> s.getInfos().forEach(System.out::println));
    }

    @Test
    void mongoMultiParamFind(){
        List<LogInfo> list = mongoRepository.findAllByPage(2,3,null,null,null,null, LogInfo.class,"12345678").stream().map(s -> (LogInfo)s).toList();
        list.forEach(System.out::println);
    }

    @Test
    void simpleTest(){
        LinkedList<Info> list = new LinkedList<>();
        list.add(new Info(1,0,"111"));
        list.add(new Info(1,1,"111"));
        list.add(new Info(1,2,"111"));
        LogInfo logInfo = new LogInfo(new ObjectId(new Date()),"123456","1234",list);
        logInfo.getInfos().addAll(list);
        System.out.println(logInfo.getInfos());
    }

}
