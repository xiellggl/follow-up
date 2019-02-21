package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 会员状态枚举
 */
public enum MemberStatusEnum {

    LOCK("锁定",-1),
    DISABLE("禁用", 0),
    ENABLE("启用", 1);

    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    MemberStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public MemberStatusEnum returnEnum(Integer persistedValue) {
        if (persistedValue == null) return MemberStatusEnum.ENABLE;
        for (MemberStatusEnum status : values()) {
            if (status.getValue().intValue() == persistedValue.intValue()) return status;
        }
        return MemberStatusEnum.ENABLE;
    }

    public static String getNameByValue(Integer value) {
        for (MemberStatusEnum item : MemberStatusEnum.values()) {
            if (item.value == value) {
                return item.getName();
            }
        }
        return "";
    }
}

