package com.cvte.cvte_logsystem_sdk_backend.db_mongo.repository;

import javax.print.attribute.standard.JobKOctets;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Classname BasicMongoRepository
 * @Date 2023/8/2 6:36 PM
 * @Created by liushenghao
 */
public interface BasicMongoRepository {
    // =========== 条件查询 =============

    default List<Object> queryFind(String key, String value, Class className) {
        return null;
    }

    default List<Object> queryFind(String key, String value, Class className, String collectionName) {
        return null;
    }

    default Object queryFindOne(String key, String value, Class className) {
        return null;
    }

    default Object queryFindOne(String key, String value, Class className, String collectionName) {
        return null;
    }

    // =========== 查询所有 =============

    default List<Object> findAll(Class className) {
        return null;
    }

    default List<Object> findAll(Class className, String collectionName) {
        return null;
    }

    // ============ 分页查询 ============

    default List<Object> findByPage(int pageNum, int pageSize, Class className, String sortTag) {
        return null;
    }

    default List<Object> findByPage(int pageNum, int pageSize, Class className, String collectionName, String sortTag) {
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

    default void singleUpsert(String key, String value, String updateKey, Object updateValue, Class className,String collectionName) {

    }

    // =========== 数据删除 ==============

    default void singleRemove(String key, String value, Class className) {

    }
}
