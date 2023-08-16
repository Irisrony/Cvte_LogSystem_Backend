package com.cvte.logsystem_sdk.service;

import com.cvte.logsystem_sdk.domain.UserInfo;
import com.cvte.logsystem_sdk.exception.UserInfoException;
import com.cvte.logsystem_sdk.redis.repositoryImpl.RedisRepositoryImpl;
import com.cvte.logsystem_sdk.response.ResultCode;
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
        final String appid = userInfo.getAppid(),userid = userInfo.getUserid();
        // 先清除过期的KV
        redisRepository.removeZSetValueByScore(appid,Long.MIN_VALUE,new Date().getTime() - 24*60*60*1000*3);
        if (!redisRepository.zsetHasKey(appid, userid)){
            throw new UserInfoException(ResultCode.UNAUTHORIZED);
        }
        redisRepository.setZSetValue("uploading",appid+"_"+userid,new Date().getTime());
        return true;
    }
}
