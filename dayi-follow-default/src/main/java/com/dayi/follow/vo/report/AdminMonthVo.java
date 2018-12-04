package com.dayi.follow.vo.report;


import java.math.BigDecimal;

public class AdminMonthVo {
    private String deptName;//部门名称

    private String name;//姓名

    private String inviteCode;//邀请码

    private Integer orgNum;//新签创客

    private Integer openNum;//开户量

    private BigDecimal inCash;  // 入金

    private BigDecimal manageFund;//管理资产

    private BigDecimal manageFundGrowth;//管理资产

    public BigDecimal getInCash() {
        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(Integer orgNum) {
        this.orgNum = orgNum;
    }

    public Integer getOpenNum() {
        return openNum;
    }

    public void setOpenNum(Integer openNum) {
        this.openNum = openNum;
    }

    public BigDecimal getManageFund() {
        return manageFund;
    }

    public void setManageFund(BigDecimal manageFund) {
        this.manageFund = manageFund;
    }

    public BigDecimal getManageFundGrowth() {
        return manageFundGrowth;
    }

    public void setManageFundGrowth(BigDecimal manageFundGrowth) {
        this.manageFundGrowth = manageFundGrowth;
    }
}