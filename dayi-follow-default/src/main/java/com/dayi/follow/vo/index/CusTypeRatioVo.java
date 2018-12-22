package com.dayi.follow.vo.index;


import com.dayi.follow.enums.AgentCusTypeEnum;

public class CusTypeRatioVo {
    private Integer num;
    private Integer cusType;
    private String cusTypeStr;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCusType() {
        return cusType;
    }

    public void setCusType(Integer cusType) {
        this.cusType = cusType;
    }

    public String getCusTypeStr() {
        return AgentCusTypeEnum.getNameByValue(cusType);
    }

    public void setCusTypeStr(String cusTypeStr) {
        this.cusTypeStr = cusTypeStr;
    }
}