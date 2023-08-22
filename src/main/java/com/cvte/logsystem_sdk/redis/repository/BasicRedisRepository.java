package com.cvte.logsystem_sdk.redis.repository;

import java.util.*;

public interface BasicRedisRepository {
    // =================  Key-Value  ==================

    /**
     * 设置过期时间
     * @param key 键
     * @param time  过期时间
     * @return  是否成功设置过期时间
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
     * @return  是否存在
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
     * @return  hash的键对应的值
     */
    default Object getHashMapValue(String key, String item) {
        return null;
    }

    /**
     * 向哈希表中添加数据
     *
     * @param key   redis的键
     * @param item  hash的键
     * @param value 数据
     * @return  是否添加成功
     */
    default boolean setHashMapValue(String key, String item, Object value) {
        return false;
    }

    /**
     * 获取某个键对应的哈希表
     *
     * @param key   键
     * @return  对应的hashmap
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
     * @param key   键
     * @param item  hash表的键
     * @param value 待插入数据
     * @param time  过期时间
     * @return  是否设置成功
     */
    default boolean setHashMapValue(String key, String item, Object value, long time) {
        return false;
    }

    /**
     * 删除哈希表中的值
     *
     * @param key   键
     * @param items hash的键
     */
    default void deleteHashMapValue(String key, String... items) {

    }

    /**
     * 检查哈希表中是否存在item项
     *
     * @param key   键
     * @param item  hash的键
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
     * @return  对应的set
     */
    default Set<Object> getSet(String key) {
        return null;
    }

    /**
     * 检查Set中是否存在该值
     *
     * @param key 键
     * @param value 待查询的值
     * @return  查询结果
     */
    default boolean setHasKey(String key, Object value) {
        return false;
    }

    /**
     * 添加数据到Set中并设置过期时间
     *
     * @param key   键
     * @param time 过期时间
     * @param values    值
     * @return  成功添加的条数
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
     * @param values    待移除的数据
     * @return  成功移除的条数
     */
    default long removeSetValue(String key, Object... values) {
        return 0;
    }

    // =================  Key-List  ==================

    /**
     * 获取list中[start,end]的内容
     *
     * @param key   键
     * @param start 起始
     * @param end   结尾
     * @return  [start,end]的list
     */
    default List<Object> getList(String key, long start, long end) {
        return null;
    }

    /**
     * 获取list的长度
     *
     * @param key   键
     * @return  长度
     */
    default long getListSize(String key) {
        return 0;
    }

    /**
     * 获取list对应索引下的值
     *
     * @param key   键
     * @param index 索引
     * @return  值
     */
    default Object getListValueByIndex(String key, long index) {
        return null;
    }

    /**
     * 将值放入list
     *
     * @param key   键
     * @param value 值
     * @return  添加结果
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
     * @return  添加结果
     */
    default boolean setListValue(String key, Object value, long time) {
        return false;
    }

    /**
     * 将集合list放入list中
     *
     * @param key   键
     * @param list  list集合
     * @return  添加结果
     */
    default boolean setListValue(String key, Object... list) {
        return false;
    }

    /**
     * 将集合list放入list中,并设置过期时间
     *
     * @param key   键
     * @param list  集合
     * @param time  过期时间
     * @return  是否成功
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
     * @return  是否成功
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
     * @return  成功删除的数量
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
     * @return  [start,end]set集合
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
    boolean zsetHasKey(String key, Object value);

    /**
     * 将数据放入ZSet中
     *
     * @param key   键
     * @param value 值
     * @return  是否成功
     */
    boolean setZSetValue(String key, Object value, long score);

    /**
     * 添加数据到ZSet中并设置过期时间
     *
     * @param key   键
     * @param time  过期时间
     * @param value 值
     * @return  是否成功
     */
    boolean setZSetValue(String key, Object value, long time, long score);

    /**
     * 获取ZSet大小
     *
     * @param key   键
     * @return  set大小
     */
    long getZSetSize(String key);

    /**
     * 从ZSet中移除values
     *
     * @param key   键
     * @param values    值
     * @return  成功删除的个数
     */
    long removeZSetValue(String key, Object... values);

    /**
     * 根据score区间删除数据
     *
     * @param key   键
     * @param minExpireTime 最小过期时间
     * @param maxExpireTime 最大过期时间
     * @return 返回成功删除的数量
     */
    default long removeZSetValueByScore(String key, long minExpireTime, long maxExpireTime) {
        return 0;
    }
}
