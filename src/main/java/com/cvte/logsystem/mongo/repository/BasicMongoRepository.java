package com.cvte.logsystem.mongo.repository;

import com.cvte.logsystem.domain.LogInfo;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BasicMongoRepository<T> {
    // =========== 获取集合信息 =============
    default Set<String> getCollectionNames(){
        return null;
    }

    default Boolean hasCollectionName(String appid){return null;}

    // =========== 条件查询 =============

    default List<T> findAllByField(String key, String value, Class className) {
        return null;
    }

    default List<T> findAllByField(String key, String value, Class className, String collectionName) {
        return null;
    }

    default T findOneByField(String key, String value, Class className) {
        return null;
    }

    default T findOneByField(String key, String value, Class className, String collectionName) {
        return null;
    }

    // =========== 综合查询 =============

    default List<T> findAllByPage(int pageNum,int pageSize,String key,String value,String regexTag,String content,Class className,String collectionName){
        return null;
    }

    // =========== Sql查询 =============

    LogInfo findById(Object id, Class className, String collectionName);

    default T findOneByQuery(Query query, Class className, String collectionName){
        return null;
    }

    default List<T> findAllByQuery(Query query, Class className){
        return null;
    }

    default List<T> findAllByQuery(Query query,Class className,String collectionName){
        return null;
    }

    // =========== 查询所有数据 =============

    default List<T> findAll(Class className) {
        return null;
    }

    default List<T> findAll(Class className, String collectionName) {
        return null;
    }

    // ============ 分页查询 ============

    default List<T> findByPage(int pageNum, int pageSize, Class className) {
        return null;
    }


    List<LogInfo> findOneField(String field, Class className, String collectionName);

    List<LogInfo> findOneField(String field, Class className);

    default List<T> findByPage(int pageNum, int pageSize, Class className, String sortTag) {
        return null;
    }

    default List<T> findByPage(int pageNum, int pageSize, Class className, String sortTag, String collectionName) {
        return null;
    }

    // =========== 多匹配查询 =============

    default List<T> multiMatchFind(Map<String, List<Object>> map, Class className) {
        return null;
    }

    // =========== 数据插入 ==============

    default void save(T obj, String collectionName) {

    }

    default void insert(List<T> list, String collectionName) {

    }

    // =========== 数据更新 ==============

    default void singleUpdate(String key, String value, String updateKey, Object updateValue, Class className) {

    }

    default void singleUpsert(String key, String value, String updateKey, Object updateValue, Class className, String collectionName) {

    }

    // =========== 数据删除 ==============

    default void singleRemove(String key, String value, Class className) {

    }

    default void singleRemove(String key, String value, Class className, String collectionName) {

    }
}

