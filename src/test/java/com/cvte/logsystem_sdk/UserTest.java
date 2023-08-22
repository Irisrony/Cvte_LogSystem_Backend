package com.cvte.logsystem_sdk;

import com.cvte.logsystem_sdk.redis.repositoryImpl.RedisRepositoryImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class UserTest {
    @Autowired
    private RedisRepositoryImpl redisRepository;

    private final static int EXPIRE_TIME = 24*60*60*1000*3;

    private final static String UPLOADING = "uploading";

    @Test
    void waitForUpload(){
        redisRepository.setZSetValue("b7eyu9T4","123",new Date().getTime()-24*60*60*1000*4);
    }

    @Test
    void onUploading(){
        redisRepository.setZSetValue(UPLOADING,"b7eyu9T4_123",new Date().getTime()-24*60*60*1000*4);
    }

    @Test
    void checkUserExist(){
        // 添加用户以便测试 正确appid : "b7eyu9T4" 正确userid : "123"
        waitForUpload();

        // 1. 错误测试
        // 1.1 appid 错误 userid 正确
        redisRepository.zsetHasKey("asjdfklsajkdlf","123");
        // 1.2 appid 正确 userid 错误
        redisRepository.zsetHasKey("b7eyu9T4","123123123");

        // 2. 正确测试
        redisRepository.zsetHasKey("b7eyu9T4","123");

    }

    @Test
    void removeUser(){
        // 添加用户以便测试 正确appid : "b7eyu9T4" 正确userid : "123"
        waitForUpload();

        // 1. 错误测试
        // 1.1 appid 错误
        redisRepository.removeZSetValueByScore("asjdfklsajkdlf",Long.MIN_VALUE,new Date().getTime() - EXPIRE_TIME);

        // 2. 正确测试
        redisRepository.removeZSetValueByScore("b7eyu9T4",Long.MIN_VALUE,new Date().getTime() - EXPIRE_TIME);
    }

    @Test
    void checkUploadExist(){
        // 添加用户以便测试 正确appid : "b7eyu9T4_123"
        onUploading();

        // 1. 错误测试
        // 1.1 key正确 value错误
        redisRepository.zsetHasKey(UPLOADING,"skdfjlks_dfj");
        // 1.2 key错误 value正确
        redisRepository.zsetHasKey("123123123","b7eyu9T4_123");

        // 2. 正确测试
        redisRepository.zsetHasKey(UPLOADING,"b7eyu9T4_123");
    }

    @Test
    void removeUpload(){
        // 添加用户以便测试 正确appid : "b7eyu9T4_123"
        onUploading();

        // 1. 错误测试
        // 1.1 key错误
        redisRepository.removeZSetValueByScore("123123123",Long.MIN_VALUE,new Date().getTime() - EXPIRE_TIME);

        // 2. 正确测试
        redisRepository.removeZSetValueByScore(UPLOADING,Long.MIN_VALUE,new Date().getTime() - EXPIRE_TIME);
    }

}
