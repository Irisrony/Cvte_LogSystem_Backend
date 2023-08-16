package com.cvte.logsystem_sdk.rocketMQ.producer.repositoryImpl;

import com.cvte.logsystem_sdk.rocketMQ.producer.repository.ProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Classname ProducerRepositoryImpl
 * @Date 2023/8/16 6:49 PM
 * @Created by liushenghao
 */
@Repository
@Slf4j
public class ProducerRepositoryImpl implements ProducerRepository {
    @Value("${rocketmq.producer.group}")
    private String destination;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public boolean sendSingleMessage(Object payload) {
        try{
            log.info("开始发送消息：");
            rocketMQTemplate.convertAndSend(destination,payload);
            return true;
        }catch (Exception e){
            log.info("消息发送失败：" + payload.toString());
            log.error(e.getMessage());
            return false;
        }
    }
}
