package com.cvte.logsystem_sdk;

import com.cvte.logsystem_sdk.domain.Info;
import com.cvte.logsystem_sdk.domain.LogInfo;
import com.cvte.logsystem_sdk.db_mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.logsystem_sdk.db_redis.repositoryImpl.RedisRepositoryImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class RedisTest {
    @Autowired
    private RedisRepositoryImpl redisRepository;
    @Autowired
    private MongoRepositoryImpl<LogInfo> mongoRepository;

    @Test
    void zsetTest() {
        redisRepository.setZSetValue("fPhlM37R","user1",new Date().getTime());
        redisRepository.setZSetValue("fPhlM37R","user2",new Date().getTime());
        redisRepository.setZSetValue("fPhlM37R","user3",new Date().getTime());
        redisRepository.setZSetValue("fPhlM37R","user4",new Date().getTime());
        System.out.println(redisRepository.getZSet("fPhlM37R", 0, -1));
    }

    @Test
    void zsetTest2() {
        Set<DefaultTypedTuple<String>> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            set.add(new DefaultTypedTuple<>("user"+i, (double) new Date().getTime()));
        }
        redisRepository.setZSetValues("uploaded",set);
        System.out.println(redisRepository.getZSet("uploaded", 0, -1));
    }

    @Test
    void redisExpireTest(){
        System.out.println(redisRepository.removeZSetValueByScore("uploaded", Long.MIN_VALUE,
                new Date().getTime() - 24 * 60 * 60 * 3));
    }

    // ===========================

    @Test
    void mongoInitTest1(){
        String userid = "12345";
        String appid = "12345679";
        ObjectId objectId = new ObjectId(new Date());
        Info info = new Info(0,0,"222");
        LogInfo logInfo = new LogInfo(objectId,appid,userid,info);
        try{
            mongoRepository.save(logInfo,appid);
        }catch (Exception e){
            e.printStackTrace();
            //mongoRepository.singleUpsert("userid",userid,"info",logInfo.getInfo(), LogInfo.class,appid);
        }
    }

    @Test
    void mongoInitTest2(){
        String userid = "125";
        String appid = "12345678";
        ObjectId objectId = new ObjectId(new Date());
        Info info = new Info(0,0,"111");
        LogInfo logInfo = new LogInfo(objectId,appid,userid,info);
        try{
            mongoRepository.save(logInfo,appid);
        }catch (Exception e){
            e.printStackTrace();
            //mongoRepository.singleUpsert("userid",userid,"info",logInfo.getInfo(), LogInfo.class,appid);
        }
    }

    @Test
    void mongoFindOneTest1(){
        //LogInfo logInfo = (LogInfo) mongoRepository.findOneByField("userid","123", LogInfo.class,"12345678");
        LogInfo logInfo = (LogInfo) mongoRepository.findOneByField("userid","94", LogInfo.class,"48");
        Info info = logInfo.getInfo();
        System.out.println(info);
        System.out.println(logInfo);
    }

    @Test
    void mongoFindOneTest2(){
        Object obj = mongoRepository.findOneByField("userid","134",LogInfo.class,"12345678");
        System.out.println((LogInfo)obj);
    }

    @Test
    void mongoFindAllTest1(){
        List<LogInfo> list = mongoRepository.findAllByField("userid","12345",LogInfo.class);
        list.forEach(s -> System.out.println(s.getInfo()));
    }

    @Test
    void mongoFindAllTest2(){
        List<LogInfo> list = mongoRepository.findAll(LogInfo.class).stream().map(s -> (LogInfo)s).toList();
        list.forEach(s -> System.out.println(s.getInfo()));
    }

    @Test
    void mongoPageFind(){
        List<LogInfo> list = mongoRepository.findByPage(1,2, LogInfo.class,"12345678").stream().map(s -> (LogInfo)s).toList();
        list.forEach(s -> System.out.println(s.getInfo()));
    }

    @Test
    void mongoMultiParamFind(){
        List<LogInfo> list = mongoRepository.findAllByPage(1,3,"userid","12345",null,null, LogInfo.class,null).stream().map(s -> (LogInfo)s).toList();
        list.forEach(System.out::println);
    }

    @Test
    void simpleTest(){
        Set<String> st = mongoRepository.getCollectionNames();
        Map<String,Set<String>> map = new HashMap<>();
        for (String s : st) {
            List<LogInfo> list = mongoRepository.findOneField("userid", LogInfo.class,s);
            map.put(s,list.stream().map(LogInfo::getUserid).collect(Collectors.toSet()));
        }
        System.out.println(map);
    }

    @Test
    void mongoFindById(){
        String id = "64d1dfad6f24c3784009fcf7";
        String appid = "Vzx5kDOT";
        LogInfo logInfo = mongoRepository.findById(id, LogInfo.class,appid);
        System.out.println(logInfo.toString());
    }

}
