package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

public enum LoginTypeEnum {
    PC(1, "PC端"),
    H5(2, "h5"),
    IOS(3, "ios端"),//APP端
    FRIEND(4, "合作伙伴"),
    ANDROID(5, "android端");//APP端

    LoginTypeEnum(Integer key, String cname) {
        this.key = key;
        this.cname = cname;
    }

    private Integer key;
    private String cname;

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
        for (LoginTypeEnum type : LoginTypeEnum.values()) {
            if (type.getKey() == key) {
                return type.getCname();
            }
        }
        return "";
    }

    public static String getStringByKey(int key) {
        for (LoginTypeEnum type : LoginTypeEnum.values()) {
            if (type.getKey() == key) {
                return type.getCname();
            }
        }
        return "";
    }

    static public LoginTypeEnum getLoginType(int key) {
        for (LoginTypeEnum loginType : LoginTypeEnum.values()) {
            if (loginType.getKey() == key) {
                return loginType;
            }
        }
        return PC;
    }

}
