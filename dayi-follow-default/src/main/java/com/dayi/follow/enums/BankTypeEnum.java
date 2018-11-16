package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 删除状态枚举
 */
public enum DelStatusEnum {
    Delete(-1, "删除"),
    Normal(1, "正常");
    String name;
    int value;

    DelStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public static DelStatusEnum getEnumDelStatus(Integer key) {
        for (DelStatusEnum type : DelStatusEnum.values()) {
            if (key != null && key.equals(type.getValue())) return type;
        }
        return null;
    }
}
