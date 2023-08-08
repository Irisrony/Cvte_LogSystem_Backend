package com.cvte.cvte_logsystem_sdk_backend;

import com.cvte.cvte_logsystem_sdk_backend.domain.Info;
import com.cvte.cvte_logsystem_sdk_backend.domain.LogInfo;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.cvte_logsystem_sdk_backend.db_redis.repositoryImpl.RedisRepositoryImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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
            mongoRepository.singleUpsert("userid",userid,"info",logInfo.getInfo(), LogInfo.class,appid);
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
            mongoRepository.singleUpsert("userid",userid,"info",logInfo.getInfo(), LogInfo.class,appid);
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
        List<Object> objs = mongoRepository.findAllByField("userid","12345",LogInfo.class);
        List<LogInfo> list =  objs.stream().map(s -> (LogInfo) s).toList();
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
        Date now = new Date();
        List<LogInfo> list = new ArrayList<>();
        Info info = new Info(0,0,"111");
        LogInfo logInfo1 = new LogInfo(new ObjectId(now),"12345679","125",info);
        LogInfo logInfo2 = new LogInfo(new ObjectId(now),"12345679","126",info);
        LogInfo logInfo3 = new LogInfo(new ObjectId(now),"12345679","127",info);
        list.add(logInfo2);
        list.add(logInfo1);
        list.add(logInfo3);
        mongoRepository.insert(list,"12345678");
    }

}
