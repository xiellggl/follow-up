package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 联系方式
 */
public enum ContactTypeEnum {

    /**
     * 电话联系
     */
    PHONE("电话联系", 1),
    /**
     * 微信联系
     */
    WECHAT("微信联系", 2),
    /**
     * QQ联系
     */
    QQ("QQ联系", 3),
    /**
     * 邮件联系
     */
    EMAIL("邮件联系", 4),
    /**
     * 短信联系
     */
    MESSAGE("短信联系", 5);
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    ContactTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
