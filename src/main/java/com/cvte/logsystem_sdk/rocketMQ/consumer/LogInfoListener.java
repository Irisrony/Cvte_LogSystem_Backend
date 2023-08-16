package com.cvte.logsystem_sdk.rocketMQ.consumer;

import com.alibaba.fastjson.JSON;
import com.cvte.logsystem_sdk.domain.LogInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Classname RocketMQListener
 * @Date 2023/8/16 11:39 AM
 * @Created by liushenghao
 */
@Service
@Slf4j
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic}", consumerGroup = "${rocketmq.consumer.group}")
public class LogInfoListener implements RocketMQListener<List<Object>> {
    @Override
    public void onMessage(List<Object> list) {
        try{
            log.info("开始消费：");
            List<LogInfo> infos = list.stream().map(s->JSON.parseObject(JSON.toJSONString(s),LogInfo.class)).toList();
            infos.forEach(s -> log.info(s.toString()));
        }catch (Exception e){
            log.info("消费失败");
            log.error(e.getMessage());
        }
    }
}
