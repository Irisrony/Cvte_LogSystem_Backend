package com.cvte.cvte_logsystem_sdk_backend.db_redis.service;

import com.cvte.cvte_logsystem_sdk_backend.db_redis.domain.UserInfo;
import com.cvte.cvte_logsystem_sdk_backend.db_redis.repositoryImpl.RedisRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description TODO
 * @Classname LogInfoService
 * @Date 2023/8/2 4:08 PM
 * @Created by liushenghao
 */
@Service
public class UserInfoService {
    @Autowired
    private RedisRepositoryImpl redisRepository;

    public boolean isExist(UserInfo userInfo){
        redisRepository.removeZSetValueByScore(userInfo.getAppid(),Long.MIN_VALUE,new Date().getTime() - 24*60*60*3);
        return redisRepository.zsetHasKey(userInfo.getAppid(), userInfo.getUserid());
    }
}
