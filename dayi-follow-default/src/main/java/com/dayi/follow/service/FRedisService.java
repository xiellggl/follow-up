package com.dayi.follow.service;


public interface FRedisService {
    void set(String key, Object value);

    Object get1(String key);

    //使用T调用者不用进行强制类型转换
    <T> T get2(String key);
}
