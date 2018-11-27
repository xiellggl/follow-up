package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 *
 */
public enum SwitchStatusEnum {
    OPEN(1, "开启"),
    CLOSE(-1, "关闭");
    Integer key;
    String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    SwitchStatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

}
