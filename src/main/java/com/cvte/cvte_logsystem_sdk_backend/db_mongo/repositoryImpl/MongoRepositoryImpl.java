package com.cvte.cvte_logsystem_sdk_backend.db_mongo.repositoryImpl;

import com.cvte.cvte_logsystem_sdk_backend.db_mongo.repository.BasicMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Classname MongoRepository
 * @Date 2023/8/2 6:36 PM
 * @Created by liushenghao
 */
@Repository
public class MongoRepositoryImpl implements BasicMongoRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    // 添加/更新数据

    @Override
    public void singleUpsert(String key,String value,String updateKey,Object updateValue,Class className,String collectionName){
        Query query = Query.query(Criteria.where(key).is(value));
        Update update = Update.update(updateKey,updateValue);
        mongoTemplate.upsert(query,update,className,collectionName);
    }

    @Override
    public void save(Object obj,String collectionName){
        mongoTemplate.save(obj,collectionName);
    }

    // 条件查询数据

    /**
     * 单个查询
     * @param key
     * @param value
     * @param className
     * @param collectionName
     * @return
     */
    @Override
    public Object queryFindOne(String key,String value,Class className,String collectionName){
        Query query = Query.query(Criteria.where(key).is(value));
        return mongoTemplate.findOne(query,className,collectionName);
    }

    @Override
    public List<Object> queryFind(String key, String value, Class className){
        Query query = Query.query(Criteria.where(key).is(value));
        return mongoTemplate.find(query,className);
    }
}
