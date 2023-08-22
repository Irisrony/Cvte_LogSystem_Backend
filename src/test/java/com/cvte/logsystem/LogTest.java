package com.cvte.logsystem;

import com.cvte.logsystem.mongo.repositoryImpl.MongoRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogTest {
    @Autowired
    private MongoRepositoryImpl mongoRepository;

    @Test
    void collectionSizeTest(){
        // 1. 测试
        // 1.1 userid不存在
        Long size;
        if ((size = mongoRepository.getCollectionSize("notExist",null,"allLogs")).equals(0L)){
            System.out.println("Test 1.1 passed!");
        }else{
            System.out.println("Test 1.1 failed! Error data - size : " + size);
        }

        // 1.2 collection不存在
        if ((size = mongoRepository.getCollectionSize("123",null,"b7eyu9T5")).equals(0L)){
            System.out.println("Test 1.2 passed!");
        }else{
            System.out.println("Test 1.2 failed! Error data - size : " + size);
        }

        // 1.3 userid与collection存在
        if ((size = mongoRepository.getCollectionSize("123",null,"b7eyu9T4")).equals(46L)){
            System.out.println("Test 1.3 passed!");
        }else{
            System.out.println("Test 1.3 failed! Error data - size : " + size);
        }

    }

    @Test
    void getLogInfoTest(){
        
    }
}
