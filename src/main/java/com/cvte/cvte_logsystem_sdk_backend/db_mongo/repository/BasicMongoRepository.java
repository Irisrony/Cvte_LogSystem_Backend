package com.cvte.cvte_logsystem_sdk_backend.db_mongo.repository;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description TODO
 * @Classname BasicMongoRepository
 * @Date 2023/8/2 6:36 PM
 * @Created by liushenghao
 */
public interface BasicMongoRepository {
    // =========== 获取全部集合名称 =============
    default Set<String> getCollectionNames(){
        return null;
    }

    default Boolean hasCollectionName(String appid){return null;};

    // =========== 条件查询 =============

    default List<Object> findAllByField(String key, String value, Class className) {
        return null;
    }

    default List<Object> findAllByField(String key, String value, Class className, String collectionName) {
        return null;
    }

    default Object findOneByField(String key, String value, Class className) {
        return null;
    }

    default Object findOneByField(String key, String value, Class className, String collectionName) {
        return null;
    }

    // =========== 综合查询 =============

    default List<Object> findAllByPage(int pageNum,int pageSize,String key,String value,String regexTag,String content,Class className,String collectionName){
        return null;
    }

    // =========== Sql查询 =============

    default Object findOneByQuery(Query query,Class className,String collectionName){
        return null;
    }

    default List<Object> findAllByQuery(Query query, Class className){
        return null;
    }

    default List<Object> findAllByQuery(Query query,Class className,String collectionName){
        return null;
    }

    // =========== 查询所有数据 =============

    default List<Object> findAll(Class className) {
        return null;
    }

    default List<Object> findAll(Class className, String collectionName) {
        return null;
    }

    // ============ 分页查询 ============

    default List<Object> findByPage(int pageNum, int pageSize, Class className) {
        return null;
    }


    default List<Object> findByPage(int pageNum, int pageSize, Class className, String sortTag) {
        return null;
    }

    default List<Object> findByPage(int pageNum, int pageSize, Class className, String sortTag, String collectionName) {
        return null;
    }

    // =========== 多匹配查询 =============

    default List<Object> multiMatchFind(Map<String, List<Object>> map, Class className) {
        return null;
    }

    // =========== 数据插入 ==============

    default void save(Object obj, String className) {

    }

    default void insert(List<Object> obj, String className) {

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
