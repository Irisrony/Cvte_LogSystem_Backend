package com.cvte.logsystem_sdk;

import com.alibaba.fastjson.JSON;
import com.cvte.logsystem_sdk.domain.Info;
import com.cvte.logsystem_sdk.domain.LogInfo;
import com.cvte.logsystem_sdk.mongo.repositoryImpl.MongoRepositoryImpl;
import com.cvte.logsystem_sdk.redis.repositoryImpl.RedisRepositoryImpl;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;


@SpringBootTest
class RedisTest {
    @Autowired
    private RedisRepositoryImpl redisRepository;
    @Autowired
    private MongoRepositoryImpl mongoRepository;
    //@Autowired
    //private RocketMQTemplate rocketMQTemplate;
    //@Test
    //void zsetTest() {
    //    redisRepository.setZSetValue("fPhlM37R","user1",new Date().getTime());
    //    redisRepository.setZSetValue("fPhlM37R","user2",new Date().getTime());
    //    redisRepository.setZSetValue("fPhlM37R","user3",new Date().getTime());
    //    redisRepository.setZSetValue("fPhlM37R","user4",new Date().getTime());
    //    System.out.println(redisRepository.getZSet("fPhlM37R", 0, -1));
    //}
    //
    //@Test
    //void zsetTest2() {
    //    Set<DefaultTypedTuple<String>> set = new HashSet<>();
    //    for (int i = 0; i < 4; i++) {
    //        set.add(new DefaultTypedTuple<>("user"+i, (double) new Date().getTime()));
    //    }
    //    redisRepository.setZSetValues("uploaded",set);
    //    System.out.println(redisRepository.getZSet("uploaded", 0, -1));
    //}
    //
    //@Test
    //void redisExpireTest(){
    //    System.out.println(redisRepository.removeZSetValueByScore("uploaded", Long.MIN_VALUE,
    //            new Date().getTime() - 24 * 60 * 60 * 3));
    //}
    //
    //// ===========================
    //
    //@Test
    //void mongoInitTest1(){
    //    String userid = "12345";
    //    String appid = "12345679";
    //    ObjectId objectId = new ObjectId(new Date());
    //    Info info = new Info(0,0,"222");
    //    LogInfo logInfo = new LogInfo(objectId,appid,userid,info);
    //    try{
    //        mongoRepository.save(logInfo,appid);
    //    }catch (Exception e){
    //        e.printStackTrace();
    //        //mongoRepository.singleUpsert("userid",userid,"info",logInfo.getInfo(), LogInfo.class,appid);
    //    }
    //}
    //
    //@Test
    //void mongoInitTest2(){
    //    String userid = "125";
    //    String appid = "12345678";
    //    ObjectId objectId = new ObjectId(new Date());
    //    Info info = new Info(0,0,"111");
    //    LogInfo logInfo = new LogInfo(objectId,appid,userid,info);
    //    try{
    //        mongoRepository.save(logInfo,appid);
    //    }catch (Exception e){
    //        e.printStackTrace();
    //        //mongoRepository.singleUpsert("userid",userid,"info",logInfo.getInfo(), LogInfo.class,appid);
    //    }
    //}
    //
    //@Test
    //void mongoFindOneTest1(){
    //    //LogInfo logInfo = (LogInfo) mongoRepository.findOneByField("userid","123", LogInfo.class,"12345678");
    //    LogInfo logInfo = mongoRepository.findOneByField("userid","94", LogInfo.class,"48");
    //    Info info = logInfo.getInfo();
    //    System.out.println(info);
    //    System.out.println(logInfo);
    //}
    //
    //@Test
    //void mongoFindOneTest2(){
    //    Object obj = mongoRepository.findOneByField("userid","134",LogInfo.class,"12345678");
    //    System.out.println((LogInfo)obj);
    //}
    //
    //@Test
    //void mongoFindAllTest1(){
    //    List<LogInfo> list = mongoRepository.findAllByField("userid","12345",LogInfo.class);
    //    list.forEach(s -> System.out.println(s.getInfo()));
    //}
    //
    //@Test
    //void mongoFindAllTest2(){
    //    List<LogInfo> list = mongoRepository.findAll(LogInfo.class).stream().map(s -> (LogInfo)s).toList();
    //    list.forEach(s -> System.out.println(s.getInfo()));
    //}
    //
    //@Test
    //void mongoPageFind(){
    //    List<LogInfo> list = mongoRepository.findByPage(1,2, LogInfo.class,"12345678").stream().map(s -> (LogInfo)s).toList();
    //    list.forEach(s -> System.out.println(s.getInfo()));
    //}
    //
    //@Test
    //void mongoMultiParamFind(){
    //    List<LogInfo> list = mongoRepository.findAllByPage(1,3,"userid","12345",null,null, LogInfo.class,null).stream().map(s -> (LogInfo)s).toList();
    //    list.forEach(System.out::println);
    //}
    //
    //@Test
    //void simpleTest(){
    //
    //}
    //
    //@Test
    //void mongoFindById(){
    //    String id = "64d1dfad6f24c3784009fcf7";
    //    String appid = "Vzx5kDOT";
    //    LogInfo logInfo = mongoRepository.findById(id, LogInfo.class,appid);
    //    System.out.println(logInfo.toString());
    //}

    @Test
    void simpleProducerTest() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        LogInfo logInfo = new LogInfo("123","abc",new Info());
        DefaultMQProducer producer = new DefaultMQProducer("test-producer-group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        Message message = new Message("test-producer-group","123".getBytes());
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult.getSendStatus());
        producer.shutdown();
    }

    @Test
    void simpleConsumerTest() throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer-group");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("test-producer-group","*");
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            System.out.println("开始消费");
            System.out.println("消息内容：" + new String(list.get(0).getBody()));
            System.out.println("消费上下文：" + consumeConcurrentlyContext);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.in.read();
    }

    //@Test
    //void simpleTest(){
    //    LogInfo logInfo = new LogInfo("123","abc",new Info());
    //    rocketMQTemplate.convertAndSend("test-producer-group",logInfo);
    //}
    //
    //@Test
    //void simpleListener(){
    //    List<String> list = rocketMQTemplate.receive(String.class);
    //    list.forEach(System.out::println);
    //}

    @Test
    void inputTest(){
        long _x = 2,n = 57690709,res = 1;
        final int MOD = (int)1e9+7;
        while(n > 0){
            System.out.println(_x);
            if((n & 1) == 1){
                res = (res * _x) % MOD;
            }
            _x = (_x * _x) % MOD;
            n >>= 1;
        }
        System.out.println(res);
    }

}
