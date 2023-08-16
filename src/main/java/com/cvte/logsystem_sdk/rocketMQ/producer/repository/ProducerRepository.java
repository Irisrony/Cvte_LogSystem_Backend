package com.cvte.logsystem_sdk.rocketMQ.producer.repository;

/**
 * @Description TODO
 * @Classname repository
 * @Date 2023/8/16 6:48 PM
 * @Created by liushenghao
 */
public interface ProducerRepository {
    // 单次上传
    boolean sendSingleMessage(Object payload);
    default boolean sendSingleMessage(String destination,Object payload){
        return false;
    }
}
