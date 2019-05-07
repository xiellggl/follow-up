package com.dayi.follow.service;


import org.springframework.data.redis.core.RedisTemplate;

public interface FRedisService {
    void set(String key, Object value, RedisTemplate redisTemplate);

    /**
     * 将内容插入到Redis，保存类型为List
     * key不存在的话就创建，存在的话就append（rightPush）
     *
     * @param key   Key
     * @param value 待写入的值
     * @return 成功写入返回true，否则返回false
     */
    boolean setList(String key, Object value, RedisTemplate redisTemplate);

    Object get1(String key, RedisTemplate redisTemplate);

    //使用T调用者不用进行强制类型转换
    <T> T get2(String key, RedisTemplate redisTemplate);

    <T> T getList(String key, long index ,RedisTemplate redisTemplate);
}
