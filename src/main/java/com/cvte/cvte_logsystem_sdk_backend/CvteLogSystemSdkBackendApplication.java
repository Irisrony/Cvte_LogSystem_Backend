package com.cvte.cvte_logsystem_sdk_backend;

import com.cvte.cvte_logsystem_sdk_backend.db_mongo.domain.LogInfo;
import com.cvte.cvte_logsystem_sdk_backend.db_mongo.repositoryImpl.MongoRepositoryImpl;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

@SpringBootApplication
@Slf4j
public class CvteLogSystemSdkBackendApplication {
    @Autowired
    private MongoRepositoryImpl mongoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CvteLogSystemSdkBackendApplication.class, args);
    }

    @Scheduled(cron = "0 * 21-23,0-5 * * ?")
    public void cron(){
        mongoRepository.clean();
    }

}
