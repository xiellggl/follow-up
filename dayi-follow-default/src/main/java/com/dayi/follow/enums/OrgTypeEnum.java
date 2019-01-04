package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 机构会员分类枚举
 */
public enum OrgTypeEnum {

    Manager(1, "经纪会员"),
    Colligate(2, "综合会员"),
    Maker(4, "创客会员"),
    CityServer(5, "城市服务商");
    String name;
    int value;

    OrgTypeEnum(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static OrgTypeEnum getEnumOrgType(Integer key) {
        for (OrgTypeEnum type : OrgTypeEnum.values()) {
            if (key != null && key.equals(type.getValue())) return type;
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for (OrgTypeEnum item : OrgTypeEnum.values()) {
            if (item.value == value) {
                return item.getName();
            }
        }
        return "";
    }
}
