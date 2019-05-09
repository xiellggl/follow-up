package com.dayi.follow.service.impl;


import com.dayi.common.util.Misc;
import com.dayi.follow.model.follow.RedisTest;
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
    public RedisTest get1(String key, RedisTemplate redisTemplate) {
        //有问题不能这样转
        return (RedisTest) redisTemplate.opsForValue().get(key);
    }

    //这样子不用显示强转，不过如果有错误还是会在运行时报不能cast异常
    @Override
    public <T> T get2(String key, RedisTemplate redisTemplate) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T getList(String key, long index, RedisTemplate redisTemplate) {
        return (T) redisTemplate.opsForList().index(key, index);
    }
}

