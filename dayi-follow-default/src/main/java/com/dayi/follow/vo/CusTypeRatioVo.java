package com.dayi.follow.vo;


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
        if (cusType != null) {
            switch (cusType) {
                case (1):
                    cusTypeStr = AgentCusTypeEnum.NOT_LINK.getName();
                    break;
                case (2):
                    cusTypeStr = AgentCusTypeEnum.OPEN_ACCOUNT.getName();
                    break;
                case (3):
                    cusTypeStr = AgentCusTypeEnum.CAN_FOLLOWUP.getName();
                    break;
                case (4):
                    cusTypeStr = AgentCusTypeEnum.NO_INTENTION.getName();
                    break;
                case (5):
                    cusTypeStr = AgentCusTypeEnum.LOST.getName();
                    break;
                case (6):
                    cusTypeStr = AgentCusTypeEnum.INVALID.getName();
                    break;
            }
        }
        return cusTypeStr;
    }

    public void setCusTypeStr(String cusTypeStr) {
        this.cusTypeStr = cusTypeStr;
    }
}