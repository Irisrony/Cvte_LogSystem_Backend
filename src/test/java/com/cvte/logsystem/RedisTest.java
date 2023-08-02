package com.cvte.logsystem;

import com.cvte.logsystem.db_redis.repositoryImpl.RedisRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class RedisTest {
    @Autowired
    private RedisRepositoryImpl redisRepositoryImpl;
    //@Test
    //void saveValueTest() {
    //    System.out.println(redisRepositoryImpl.setValue("123", "1234"));
    //    System.out.println(redisRepositoryImpl.getValue("123"));
    //    redisRepositoryImpl.deleteKey("123");
    //}

    //@Test
    //void saveSetTest1(){
    //    //System.out.println(redisUtils.setSetValue("mySet", "user1", "user2", "user4"));
    //    //System.out.println(redisUtils.getSet("mySet"));
    //    //System.out.println(redisUtils.setKeyExpire("mySet", 5 * 60));
    //    redisRepositoryImpl.deleteKey("mySet");
    //}

    //@Test
    //void opsSetTest1(){
    //    System.out.println(redisRepositoryImpl.setHasKey("mySet", "user3"));
    //    System.out.println(redisRepositoryImpl.setHasKey("mySet", "user2"));
    //}

    @Test
    void saveZSetTest1(){
        System.out.println(redisRepositoryImpl.setZSetValue("mySet", "user1",10));
        System.out.println(redisRepositoryImpl.setZSetValue("mySet", "user4",20));
        System.out.println(redisRepositoryImpl.setZSetValue("mySet", "user2",30));
        System.out.println(redisRepositoryImpl.getZSet("mySet",0,-1));
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

    //@Test
    //void opsHashMapTest1(){
    //    Map<String, Object> map = new HashMap<>();
    //    Set<String> set = new HashSet<>();
    //    set.add("002841");
    //    set.add("test2");
    //    map.put("14DNJwRY",set);
    //    System.out.println(redisRepositoryImpl.setHashMap("uploaded", map));
    //    System.out.println(redisRepositoryImpl.getHashMapValue("uploaded", "14DNJwRY"));
    //}

    //@Test
    //void opsHashMapTest2(){
    //    Object obj = redisRepositoryImpl.getHashMapValue("uploaded", "14DNJwRY");
    //    Set<Object> set = new HashSet<>();
    //    if (obj instanceof Set<?>){
    //        set = (Set<Object>) obj;
    //    }
    //    set.add("new Test");
    //    redisRepositoryImpl.setHashMapValue("uploaded","14DNJwRY",set);
    //    System.out.println(redisRepositoryImpl.getHashMapValue("uploaded", "14DNJwRY"));
    //}
}
