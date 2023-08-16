package com.cvte.logsystem_sdk.service;

import com.cvte.logsystem_sdk.redis.repositoryImpl.RedisRepositoryImpl;
import com.cvte.logsystem_sdk.domain.Info;
import com.cvte.logsystem_sdk.domain.LogInfo;
import com.cvte.logsystem_sdk.mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.logsystem_sdk.exception.AppException;
import com.cvte.logsystem_sdk.response.ResultCode;
import com.cvte.logsystem_sdk.rocketMQ.producer.repositoryImpl.ProducerRepositoryImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description TODO
 * @Classname LogInfoService
 * @Date 2023/8/4 9:54 AM
 * @Created by liushenghao
 */
@Service
@Slf4j
@EnableScheduling
public class LogInfoService {
    @Autowired
    private MongoRepositoryImpl mongoRepository;

    @Autowired
    private RedisRepositoryImpl redisRepository;

    @Autowired
    private ProducerRepositoryImpl producerRepository;

    private ConcurrentHashMap<String, uploadingEntity> map;

    private final static Long EXPIRE_TIME = (long) 6e4;
    private final static String UPLOAD_ZSET = "uploading";
    private final static String ALL_LOG = "allLogs";

    /**
     * 待上传日志实体类
     */
    @Data
    static class uploadingEntity{
        private Long timestamp;
        private CopyOnWriteArrayList<LogInfo> infoList;

        public uploadingEntity(Long timestamp){
            this.timestamp = timestamp;
            this.infoList = new CopyOnWriteArrayList<>();
        }

        public boolean update(Long timestamp,List<LogInfo> infos){
            try{
                this.timestamp = timestamp;
                this.infoList.addAll(infos);
                return true;
            }catch (Exception e){
                log.error(e.getMessage());
                return false;
            }
        }
    }

    public LogInfoService(){
        map = new ConcurrentHashMap<>();
    }

    /**
     * 分片上传
     * @param appid
     * @param userid
     * @param infos
     * @return
     */
    public Boolean singleSave(String appid,String userid,LinkedList<Info> infos){
        // 记录当前时间
        Date now = new Date();
        // key
        String key = appid + "_" + userid;
        // 检查该上传用户是否在待上传列表中
        // 删除过期信息
        redisRepository.removeZSetValueByScore(UPLOAD_ZSET,Long.MIN_VALUE,now.getTime() - 24 * 60 * 60 * 1000 * 3);
        if(!redisRepository.zsetHasKey("uploading", key)){
            throw new AppException(ResultCode.UNAUTHORIZED);
        }
        // 删除缓存，避免持续轮询
        redisRepository.removeZSetValue(appid,userid);
        List<LogInfo> list = new ArrayList<>();
        infos.forEach(info -> list.add(new LogInfo(appid, userid, info)));

        return producerRepository.sendSingleMessage(list);
    }
    //public Boolean singleSave(String appid,String userid,LinkedList<Info> infos){
    //    // 记录当前时间
    //    Date now = new Date();
    //    // key
    //    String key = appid + "_" + userid;
    //    // 检查该上传用户是否在待上传列表中
    //    // 删除过期信息
    //    redisRepository.removeZSetValueByScore(UPLOAD_ZSET,Long.MIN_VALUE,now.getTime() - 24 * 60 * 60 * 1000 * 3);
    //    if(!redisRepository.zsetHasKey("uploading", key)){
    //        throw new AppException(ResultCode.UNAUTHORIZED);
    //    }
    //    // 删除缓存，避免持续轮询
    //    redisRepository.removeZSetValue(appid,userid);
    //    List<LogInfo> list = new ArrayList<>();
    //    infos.forEach(info -> list.add(new LogInfo(appid, userid, info)));
    //
    //    // 新建
    //    if(!map.containsKey(key)){
    //        map.put(key, new uploadingEntity(now.getTime()));
    //    }
    //    // 保存
    //    return map.get(key).update(now.getTime(),list);
    //}

    /**
     * 上传日志
     * @return
     */
    public void saveOrUpsert() {

        final Enumeration<String> keys = map.keys();

        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            List<LogInfo> list = map.get(key).getInfoList();
            int n = list.size();
            if(n != 0) {
                try {
                    List<LogInfo> sublist = list.subList(0, n);
                    // 保存日志
                    mongoRepository.insert(sublist, key.split("_")[0]);
                    mongoRepository.insert(sublist, ALL_LOG);
                    // 清空当前数据
                    sublist.clear();
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }else {
                Long timestamp = map.get(key).getTimestamp();
                // 超过时间未更新，清除key
                if (isUpload(new Date().getTime(),timestamp)){
                    map.remove(key);
                    redisRepository.removeZSetValue(UPLOAD_ZSET, key);
                }
            }
        }
    }

    /**
     * 判断是否已长时间未更新信息
     * @param curTime   当前时间
     * @param preTime   上一次更新时间
     * @return  比较结果
     */
    private boolean isUpload(Long curTime,Long preTime){
        return curTime - preTime > EXPIRE_TIME;
    }

    /**
     * 定期上传
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void cron(){
        // 定期上传
        saveOrUpsert();
    }
}
