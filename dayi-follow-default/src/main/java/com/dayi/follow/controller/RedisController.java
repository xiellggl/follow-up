package com.dayi.follow.controller;

import com.dayi.follow.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Controller
@RequestMapping("/redis")
public class RedisController {
    @Resource
    FRedisService FRedisService;

    @RequestMapping("/set")
    public void set(String name, String value) {
        FRedisService.set(name, value);
    }

    @RequestMapping("/get")
    public void get(String name) {
        String s1 = (String) FRedisService.get1(name);
        System.out.println(s1);
        String s2 = FRedisService.get2(name);
        System.out.println(s2);
    }

}
