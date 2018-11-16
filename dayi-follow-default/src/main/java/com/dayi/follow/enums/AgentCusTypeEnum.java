package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 代理商客户类型
 */
public enum AgentCusTypeEnum {
    NOT_LINK("未联系", 1),
    /**
     * 未联系客户
     */
    OPEN_ACCOUNT("已开户", 2),
    /**
     * 可被跟进客户
     */
    CAN_FOLLOWUP("可被跟进", 3),
    /**
     * 无意向客户
     */
    NO_INTENTION("无意向", 4),
    /**
     * 流失客户
     */
    LOST("流失", 5),
    /**
     * 无效客户
     */
    INVALID("无效", 6);

    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    AgentCusTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
