package com.dayi.follow.service.impl;


import com.dayi.follow.service.FRedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 跟进人 业务实现类
 */
@Service
public class FRedisServiceImpl implements FRedisService {
    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object get1(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get2(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }
}

