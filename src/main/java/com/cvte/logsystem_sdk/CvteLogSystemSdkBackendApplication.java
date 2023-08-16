package com.cvte.logsystem_sdk;

import com.cvte.logsystem_sdk.domain.Info;
import com.cvte.logsystem_sdk.domain.LogInfo;
import com.cvte.logsystem_sdk.mongo.repositoryImpl.MongoRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class CvteLogSystemSdkBackendApplication {
    @Autowired
    private MongoRepositoryImpl mongoRepository;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) throws MQClientException {

        SpringApplication.run(CvteLogSystemSdkBackendApplication.class, args);

    }

    //@Scheduled(cron = "0 * 21-23,0-5 * * ?")
    @Scheduled(cron = "*/5 * * * * *")
    public void cron1(){
        mongoRepository.clean();
    }

}
