package com.cvte.logsystem.redis.repository;

import java.util.*;

/**
 * @Description TODO
 * @Classname repository
 * @Date 2023/8/2 9:30 AM
 * @Created by liushenghao
 */
public interface BasicRedisRepository{
    // =================  Key-Value  ==================

    /**
     * 设置过期时间
     * @param key   键
     * @param time  过期时间
     * @return  设置结果
     */
    boolean setKeyExpire(String key,long time);

    /**
     * 获取过期时间
     * @param key   键
     * @return  过期时间
     */
    long getKeyExpireTime(String key);

    /**
     * 判断Key是否存在
     * @param key   键
     * @return  判断结果
     */
    boolean hasKey(String key);

    /**
     * 删除key
     * @param keys  键
     */
    void deleteKey(String... keys);

    // =================  Key-HashMap  ==================

    /**
     * 获取键值表中某个键对应的值
     *
     * @param key   redis的键
     * @param item  hash的键
     * @return  对应的值
     */
    default Object getHashMapValue(String key, String item) {
        return null;
    }

    /**
     * 向哈希表中添加数据
     *
     * @param key
     * @param item
     * @param value
     * @return
     */
    default boolean setHashMapValue(String key, String item, Object value) {
        return false;
    }

    /**
     * 获取某个键对应的哈希表
     *
     * @param key
     * @return
     */
    default Map<String, Object> getHashMap(String key) {
        return null;
    }

    /**
     * 设置 K-HashMap
     *
     * @param key
     * @param map
     * @return
     */
    default boolean setHashMap(String key, Map<String, Object> map) {
        return false;
    }

    /**
     * 设置 K - HashMap 并 设置过期时间
     *
     * @param key
     * @param map
     * @param time
     * @return
     */
    default boolean setHashMap(String key, Map<String, Object> map, long time) {
        return false;
    }

    /**
     * 向哈希表中插入数据并设置过期时间
     *
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
    default boolean setHashMapValue(String key, String item, Object value, long time) {
        return false;
    }

    /**
     * 删除哈希表中的值
     *
     * @param key
     * @param items
     */
    default void deleteHashMapValue(String key, String... items) {

    }

    /**
     * 检查哈希表中是否存在item项
     *
     * @param key
     * @param item
     * @return
     */
    default boolean hashMapHasKey(String key, String item) {
        return false;
    }

    // =================  Key-Set  ==================

    /**
     * 获取Set集合中的所有值
     *
     * @param key
     * @return
     */
    default Set<Object> getSet(String key) {
        return null;
    }

    /**
     * 检查Set中是否存在该值
     *
     * @param key
     * @param value
     * @return
     */
    default boolean setHasKey(String key, Object value) {
        return false;
    }

    /**
     * 将数据放入Set中
     *
     * @param key
     * @param values
     * @return 成功个数
     */
    default long setSetValue(String key, Object... values) {
        return 0;
    }

    /**
     * 添加数据到Set中并设置过期时间
     *
     * @param key
     * @param time
     * @param values
     * @return
     */
    default long setSetValue(String key, long time, Object... values) {
        return 0;
    }

    /**
     * 获取Set大小
     *
     * @param key
     * @return
     */
    default long getSetSize(String key) {
        return 0;
    }

    /**
     * 从Set中移除values
     *
     * @param key
     * @param values
     * @return
     */
    default long removeSetValue(String key, Object... values) {
        return 0;
    }

    // =================  Key-List  ==================

    /**
     * 获取list中[start,end]的内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    default List<Object> getList(String key, long start, long end) {
        return null;
    }

    /**
     * 获取list的长度
     *
     * @param key
     * @return
     */
    default long getListSize(String key) {
        return 0;
    }

    /**
     * 获取list对应索引下的值
     *
     * @param key
     * @param index
     * @return
     */
    default Object getListValueByIndex(String key, long index) {
        return null;
    }

    /**
     * 将值放入list
     *
     * @param key
     * @param value
     * @return
     */
    default boolean setListValue(String key, Object value) {
        return false;
    }

    /**
     * 将值放入list中并设置过期时间
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    default boolean setListValue(String key, Object value, long time) {
        return false;
    }

    /**
     * 将集合list放入list中
     *
     * @param key
     * @param list
     * @return
     */
    default boolean setListValue(String key, Object... list) {
        return false;
    }

    /**
     * 将集合list放入list中,并设置过期时间
     *
     * @param key
     * @param list
     * @param time
     * @return
     */
    default boolean setListValue(String key, long time, Object... list) {
        return false;
    }

    /**
     * 根据索引修改值
     *
     * @param key
     * @param index
     * @param value
     * @return
     */
    default boolean updateListValueByIndex(String key, long index, Object value) {
        return false;
    }

    /**
     * 删除N个value值
     *
     * @param key
     * @param cnt
     * @param value
     * @return
     */
    default long removeListValue(String key, long cnt, Object value) {
        return 0;
    }

    // =================  Key-ZSet  ==================

    /**
     * 获取ZSet集合中[start,end]值  [0,-1]表示全部区间
     *
     * @param key
     * @return
     */
    default Set<Object> getZSet(String key, long start, long end) {
        return null;
    }

    /**
     * 检查ZSet中是否存在该值
     *
     * @param key
     * @param value
     * @return
     */
    default boolean zsetHasKey(String key, Object value) {
        return false;
    }

    /**
     * 将数据放入ZSet中
     *
     * @param key
     * @param value
     * @return
     */
    default boolean setZSetValue(String key, Object value, long score) {
        return false;
    }

    /**
     * 添加数据到ZSet中并设置过期时间
     *
     * @param key
     * @param time
     * @param value
     * @return
     */
    default boolean setZSetValue(String key, Object value, long time, long score) {
        return false;
    }

    /**
     * 获取ZSet大小
     *
     * @param key
     * @return
     */
    default long getZSetSize(String key) {
        return 0;
    }

    /**
     * 从ZSet中移除values
     *
     * @param key
     * @param values
     * @return
     */
    default long removeZSetValue(String key, Object... values) {
        return 0;
    }

    /**
     * 根据score区间删除数据
     *
     * @param key
     * @param minExpireTime
     * @param maxExpireTime
     * @return
     */
    default long removeZSetValueByScore(String key, long minExpireTime, long maxExpireTime) {
        return 0;
    }
}
