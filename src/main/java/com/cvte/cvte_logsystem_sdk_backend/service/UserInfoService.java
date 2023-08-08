package com.cvte.cvte_logsystem_sdk_backend.service;

import com.cvte.cvte_logsystem_sdk_backend.domain.UserInfo;
import com.cvte.cvte_logsystem_sdk_backend.exception.UserInfoException;
import com.cvte.cvte_logsystem_sdk_backend.db_redis.repositoryImpl.RedisRepositoryImpl;
import com.cvte.cvte_logsystem_sdk_backend.response.ResultCode;
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

    /**
     * 检查用户是否需要上传日志
     * @param userInfo
     * @return
     */
    public Boolean isExist(UserInfo userInfo){
        // 先清除过期的KV
        redisRepository.removeZSetValueByScore(userInfo.getAppid(),Long.MIN_VALUE,new Date().getTime() - 24*60*60*3);
        if (!redisRepository.zsetHasKey(userInfo.getAppid(), userInfo.getUserid())){
            throw new UserInfoException(ResultCode.UNAUTHORIZED);
        }
        return true;
    }
}
