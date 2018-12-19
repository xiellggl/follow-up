package com.dayi.follow.vo.org;


import com.dayi.follow.util.Misc;

import java.math.BigDecimal;

//创客数据
public class OrgDataVo {

    private Integer orgNum;//KA创客人数
    private Integer agentNum;//KA创客有效代理商总数
    private BigDecimal manageFund;//KA创客管理资金规模
    private String manageFundFm;//KA创客管理资金规模

    public String getManageFundFm() {
        if (!this.manageFund.equals(BigDecimal.ZERO)) {
            manageFundFm = Misc.parseMoney(manageFund.doubleValue(), 2);
        } else {
            manageFundFm = "0.00 元";
        }
        return manageFundFm;
    }

    public void setManageFundFm(String manageFundFm) {
        this.manageFundFm = manageFundFm;
    }

    public Integer getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(Integer orgNum) {
        this.orgNum = orgNum;
    }

    public Integer getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(Integer agentNum) {
        this.agentNum = agentNum;
    }

    public BigDecimal getManageFund() {
        return manageFund;
    }

    public void setManageFund(BigDecimal manageFund) {
        this.manageFund = manageFund;
    }
}