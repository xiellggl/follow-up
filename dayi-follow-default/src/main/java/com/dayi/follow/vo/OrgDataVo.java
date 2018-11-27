package com.dayi.follow.vo;


import java.math.BigDecimal;

public class OrgDataVo {

    private Integer kaOrgNum;//KA创客人数
    private Integer kaOrgValidAgentNum;//KA创客有效代理商总数
    private BigDecimal kaOrgManageFund;//KA创客管理资金规模

    public Integer getKaOrgNum() {
        return kaOrgNum;
    }

    public void setKaOrgNum(Integer kaOrgNum) {
        this.kaOrgNum = kaOrgNum;
    }

    public Integer getKaOrgValidAgentNum() {
        return kaOrgValidAgentNum;
    }

    public void setKaOrgValidAgentNum(Integer kaOrgValidAgentNum) {
        this.kaOrgValidAgentNum = kaOrgValidAgentNum;
    }

    public BigDecimal getKaOrgManageFund() {
        return kaOrgManageFund;
    }

    public void setKaOrgManageFund(BigDecimal kaOrgManageFund) {
        this.kaOrgManageFund = kaOrgManageFund;
    }
}