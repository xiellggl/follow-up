package com.dayi.follow.model.follow;

import java.io.Serializable;

/**
 * @author xiell
 * @date 2019/5/7
 */
public class RedisTest implements Serializable {
    private String name;

    public RedisTest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return "default";
    }


}