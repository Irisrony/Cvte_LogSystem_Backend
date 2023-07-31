package com.cvte.logsystem;

import com.cvte.logsystem.db_mysql.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisTest {
    @Autowired
    private RedisUtils redisUtils;
    @Test
    void saveValueTest() {
        System.out.println(redisUtils.setValue("123", "1234"));
        System.out.println(redisUtils.getValue("123"));
        redisUtils.deleteKey("123");
    }

    @Test
    void saveSetTest1(){
        System.out.println(redisUtils.setSetValue("mySet", "user1", "user2", "user4"));
        System.out.println(redisUtils.getSet("mySet"));
        System.out.println(redisUtils.setKeyExpire("mySet", 5 * 60));
    }

    @Test
    void opsSetTest1(){
        System.out.println(redisUtils.setHasKey("mySet", "user3"));
        System.out.println(redisUtils.setHasKey("mySet", "user2"));
    }

    @Test
    void saveZSetTest1(){
        System.out.println(redisUtils.setZSetValue("mySet", "user1",10));
        System.out.println(redisUtils.setZSetValue("mySet", "user4",20));
        System.out.println(redisUtils.setZSetValue("mySet", "user2",30));
        System.out.println(redisUtils.getZSet("mySet",0,-1));
    }

    @Test
    void opsZSetTest1(){
        System.out.println(redisUtils.zsetHasKey("mySet", "user3"));
        System.out.println(redisUtils.zsetHasKey("mySet", "user2"));
        //  删除过时数据
        //System.out.println(redisUtils.removeZSetValueByScore("mySet",Long.MIN_VALUE,20));
    }

    @Test
    void opsZSetTest2(){
        System.out.println(redisUtils.setZSetValue("fPhlM37R", "user1",10));
        System.out.println(redisUtils.setZSetValue("fPhlM37R", "user4",20));
        System.out.println(redisUtils.setZSetValue("fPhlM37R", "user2",30));
        System.out.println(redisUtils.getZSet("fPhlM37R",0,-1));
    }
}
