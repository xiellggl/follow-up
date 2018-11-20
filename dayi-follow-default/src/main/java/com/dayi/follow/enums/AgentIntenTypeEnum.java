package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 代理商客户意向度
 */
public enum AgentIntenTypeEnum {
    /**
     * 强
     */
    STRONG("强", 1),
    /**
     * 中
     */
    MIDDLE("中", 2),
    /**
     * 弱
     */
    WEAK("弱", 3),
    /**
     * 无
     */
    NO("无", 4);
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    AgentIntenTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
