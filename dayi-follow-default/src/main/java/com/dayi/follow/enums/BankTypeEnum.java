package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 银行类型枚举
 */
public enum BankTypeEnum {

    Zhong_Xin(1, "中信银行"),
    Ping_An(2, "平安易宝"),
    Ping_An_Card(3, "平安银行"),
    GUANG_FA(4,"广发银行"),
    HUA_XIA(5, "华夏银行");

    private Integer key;
    private String cname;

    BankTypeEnum(Integer key, String cname) {
        this.key = key;
        this.cname = cname;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public static String getCnameByKey(int key) {
        for (BankTypeEnum type : BankTypeEnum.values()) {
            if (type.getKey() == key) return type.getCname();
        }
        return "";
    }
    public static BankTypeEnum getStatusByKey(int key) {
        for (BankTypeEnum type : BankTypeEnum.values()) {
            if (type.getKey() == key) return type;
        }
        return null;
    }

    public static BankTypeEnum getStatusByName(String name) {
        for (BankTypeEnum type : BankTypeEnum.values()) {
            if (type.getCname().equals(name)) return type;
        }
        return null;
    }
}
