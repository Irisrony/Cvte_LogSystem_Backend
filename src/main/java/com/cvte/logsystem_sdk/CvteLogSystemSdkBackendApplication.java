package com.cvte.logsystem_sdk;

import com.cvte.logsystem_sdk.db_mongo.repositoryImpl.MongoRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class CvteLogSystemSdkBackendApplication {
    @Autowired
    private MongoRepositoryImpl mongoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CvteLogSystemSdkBackendApplication.class, args);
    }

    //@Scheduled(cron = "0 * 21-23,0-5 * * ?")
    @Scheduled(cron = "*/5 * * * * *")
    public void cron1(){
        mongoRepository.clean();
    }

}
