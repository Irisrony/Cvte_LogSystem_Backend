package com.cvte.logsystem;

import com.cvte.logsystem.db_mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.logsystem.db_redis.repositoryImpl.RedisRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
class RedisTest {
    @Autowired
    private RedisRepositoryImpl redisRepositoryImpl;

    @Autowired
    private MongoRepositoryImpl mongoRepository;

    @Test
    void saveZSetTest1(){
        //System.out.println(mongoRepository.getCollectionSize("allLogs"));
        //System.out.println(redisRepositoryImpl.getZSetSize("aabb"));
    }

    @Test
    void opsZSetTest1(){
        System.out.println(redisRepositoryImpl.zsetHasKey("mySet", "user3"));
        System.out.println(redisRepositoryImpl.zsetHasKey("mySet", "user2"));
        //  删除过时数据
        System.out.println(redisRepositoryImpl.removeZSetValueByScore("mySet",Long.MIN_VALUE,20));
    }

    @Test
    void opsZSetTest2(){
        System.out.println(redisRepositoryImpl.setZSetValue("fPhlM37R", "user1",10));
        System.out.println(redisRepositoryImpl.setZSetValue("fPhlM37R", "user4",20));
        System.out.println(redisRepositoryImpl.setZSetValue("fPhlM37R", "user2",30));
        System.out.println(redisRepositoryImpl.getZSet("fPhlM37R",0,-1));
    }

    @Test
    void simpleTest(){
    }

}
