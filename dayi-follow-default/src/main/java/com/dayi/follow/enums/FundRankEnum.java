package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 资产阶级枚举
 */
public enum FundRankEnum {
    /**
     * 资产为零
     */
    ZERO("资产为零", 1),
    /**
     * 2W以下
     */
    DOWN_2W("2W以下", 2),
    /**
     * 2W-10W
     */
    BET_2WTO10W("2W-10W", 3),
    /**
     * 10W-50W
     */
    BET_10WTO50W("10W-50W", 4),
    /**
     * 50W-100W
     */
    BET_50WTO100W("50W-100W", 5),
    /**
     * 100W-300W
     */
    BET_100WTO300W("100W-300W", 6),
    /**
     * 300W以上
     */
    UP_300W("300W以上", 7);
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    FundRankEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (FundRankEnum item : FundRankEnum.values()) {
            if (item.value == value) {
                return item.getName();
            }
        }
        return "";
    }
}
