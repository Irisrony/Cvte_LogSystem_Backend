package com.cvte.logsystem.redis.repository;

import java.util.*;

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
     * @param key   redis键
     * @param item  hash键
     * @param value 值
     * @return  是否添加成功
     */
    default boolean setHashMapValue(String key, String item, Object value) {
        return false;
    }

    /**
     * 获取某个键对应的哈希表
     *
     * @param key   键
     * @return  hashmap
     */
    default Map<String, Object> getHashMap(String key) {
        return null;
    }

    /**
     * 设置 K-HashMap
     *
     * @param key   键
     * @param map   hashmap
     * @return  是否设置成功
     */
    default boolean setHashMap(String key, Map<String, Object> map) {
        return false;
    }

    /**
     * 设置 K - HashMap 并 设置过期时间
     *
     * @param key   键
     * @param map   hashmap
     * @param time  过期时间
     * @return  是否设置成功
     */
    default boolean setHashMap(String key, Map<String, Object> map, long time) {
        return false;
    }

    /**
     * 向哈希表中插入数据并设置过期时间
     *
     * @param key   redis键
     * @param item  hash键
     * @param value 值
     * @param time  过期时间
     * @return  是否设置成功
     */
    default boolean setHashMapValue(String key, String item, Object value, long time) {
        return false;
    }

    /**
     * 删除哈希表中的值
     *
     * @param key   redis键
     * @param items hash键
     */
    default void deleteHashMapValue(String key, String... items) {

    }

    /**
     * 检查哈希表中是否存在item项
     *
     * @param key   redis键
     * @param item  hash键
     * @return  是否存在
     */
    default boolean hashMapHasKey(String key, String item) {
        return false;
    }

    // =================  Key-Set  ==================

    /**
     * 获取Set集合中的所有值
     *
     * @param key   键
     * @return  set
     */
    default Set<Object> getSet(String key) {
        return null;
    }

    /**
     * 检查Set中是否存在该值
     *
     * @param key   键
     * @param value 值
     * @return  是否存在
     */
    default boolean setHasKey(String key, Object value) {
        return false;
    }

    /**
     * 将数据放入Set中
     *
     * @param key   键
     * @param values    值
     * @return 成功个数
     */
    default long setSetValue(String key, Object... values) {
        return 0;
    }

    /**
     * 添加数据到Set中并设置过期时间
     *
     * @param key   键
     * @param time  过期时间
     * @param values    值
     * @return  成功添加的数量
     */
    default long setSetValue(String key, long time, Object... values) {
        return 0;
    }

    /**
     * 获取Set大小
     *
     * @param key   键
     * @return  set大小
     */
    default long getSetSize(String key) {
        return 0;
    }

    /**
     * 从Set中移除values
     *
     * @param key   键
     * @param values    值
     * @return  成功删除个数
     */
    default long removeSetValue(String key, Object... values) {
        return 0;
    }

    // =================  Key-List  ==================

    /**
     * 获取list中[start,end]的内容
     *
     * @param key   键
     * @param start 起始位置
     * @param end   结束位置
     * @return  [start,end]的list
     */
    default List<Object> getList(String key, long start, long end) {
        return null;
    }

    /**
     * 获取list的长度
     *
     * @param key   键
     * @return  list长度
     */
    default long getListSize(String key) {
        return 0;
    }

    /**
     * 获取list对应索引下的值
     *
     * @param key   键
     * @param index 索引
     * @return  对应的值
     */
    default Object getListValueByIndex(String key, long index) {
        return null;
    }

    /**
     * 将值放入list
     *
     * @param key   键
     * @param value 值
     * @return  是否添加成功
     */
    default boolean setListValue(String key, Object value) {
        return false;
    }

    /**
     * 将值放入list中并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间
     * @return  是否设置成功
     */
    default boolean setListValue(String key, Object value, long time) {
        return false;
    }

    /**
     * 将集合list放入list中
     *
     * @param key   键
     * @param list  list
     * @return  是否添加成功
     */
    default boolean setListValue(String key, Object... list) {
        return false;
    }

    /**
     * 将集合list放入list中,并设置过期时间
     *
     * @param key   键
     * @param list  值
     * @param time  过期时间
     * @return  是否添加成功
     */
    default boolean setListValue(String key, long time, Object... list) {
        return false;
    }

    /**
     * 根据索引修改值
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return  是否修改成功
     */
    default boolean updateListValueByIndex(String key, long index, Object value) {
        return false;
    }

    /**
     * 删除N个value值
     *
     * @param key   键
     * @param cnt   数量
     * @param value 值
     * @return  返回成功删除的数量
     */
    default long removeListValue(String key, long cnt, Object value) {
        return 0;
    }

    // =================  Key-ZSet  ==================

    /**
     * 获取ZSet集合中[start,end]值  [0,-1]表示全部区间
     *
     * @param key   键
     * @param start 起始位置
     * @param end 结束位置
     * @return  [start,end]区间的set
     */
    default Set<Object> getZSet(String key, long start, long end) {
        return null;
    }

    /**
     * 检查ZSet中是否存在该值
     *
     * @param key   键
     * @param value 值
     * @return  是否存在
     */
    default boolean zsetHasKey(String key, Object value) {
        return false;
    }

    /**
     * 将数据放入ZSet中
     *
     * @param key   键
     * @param value 值
     * @return  是否添加成功
     */
    default boolean setZSetValue(String key, Object value, long score) {
        return false;
    }

    /**
     * 添加数据到ZSet中并设置过期时间
     *
     * @param key   键
     * @param time  过期时间
     * @param value 值
     * @return  是否添加成功
     */
    default boolean setZSetValue(String key, Object value, long time, long score) {
        return false;
    }

    /**
     * 获取ZSet大小
     *
     * @param key   键
     * @return  set大小
     */
    default long getZSetSize(String key) {
        return 0;
    }

    /**
     * 从ZSet中移除values
     *
     * @param key   键
     * @param values    值
     * @return  成功移除数量
     */
    default long removeZSetValue(String key, Object... values) {
        return 0;
    }

    /**
     * 根据score区间删除数据
     *
     * @param key   键
     * @param minExpireTime 最小过期时间
     * @param maxExpireTime 最大过期时间
     * @return  成功删除个数
     */
    default long removeZSetValueByScore(String key, long minExpireTime, long maxExpireTime) {
        return 0;
    }
}
