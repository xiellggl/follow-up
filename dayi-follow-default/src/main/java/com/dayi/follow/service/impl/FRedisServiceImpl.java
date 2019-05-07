package com.dayi.follow.service.impl;


import com.dayi.common.util.Misc;
import com.dayi.follow.service.FRedisService;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 跟进人 业务实现类
 */
@Service
public class FRedisServiceImpl implements FRedisService {

    @Override
    public void set(String key, Object value, RedisTemplate redisTemplate) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public boolean setList(String key, Object value, RedisTemplate redisTemplate) {
        if (!Misc.isEmpty(key)) {
            return -1 < redisTemplate.opsForList().rightPush(key, value);
        }
        return false;
    }

    @Override
    public Object get1(String key, RedisTemplate redisTemplate) {
        redisTemplate.opsForValue().get(key);
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get2(String key, RedisTemplate redisTemplate) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T getList(String key, long index, RedisTemplate redisTemplate) {
        return (T) redisTemplate.opsForList().index(key, index);
    }
}

