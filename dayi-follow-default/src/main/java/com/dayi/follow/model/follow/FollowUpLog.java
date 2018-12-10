package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;

import java.math.BigDecimal;

/**
 * @author xiell
 * @date 2018/7/20
 */
public class FollowUpLog extends BaseModel {
    private String followId;//跟进人id
    private String deptId;//部门（团队）id
    private Integer openAccountNum;//新开户数
    private BigDecimal inCash;//网银转入总额
    private Integer inCashNum;//入金人数
    private BigDecimal outCash;//转出到卡总额
    private Integer outCashNum;//出金人数
    private BigDecimal manageFund;//管理资产规模
    private BigDecimal manageGrowthFund;//管理资产规模净增

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Integer getOpenAccountNum() {
        return openAccountNum;
    }

    public void setOpenAccountNum(Integer openAccountNum) {
        this.openAccountNum = openAccountNum;
    }

    public BigDecimal getInCash() {
        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public Integer getInCashNum() {
        return inCashNum;
    }

    public void setInCashNum(Integer inCashNum) {
        this.inCashNum = inCashNum;
    }

    public BigDecimal getOutCash() {
        return outCash;
    }

    public void setOutCash(BigDecimal outCash) {
        this.outCash = outCash;
    }

    public Integer getOutCashNum() {
        return outCashNum;
    }

    public void setOutCashNum(Integer outCashNum) {
        this.outCashNum = outCashNum;
    }

    public BigDecimal getManageFund() {
        return manageFund;
    }

    public void setManageFund(BigDecimal manageFund) {
        this.manageFund = manageFund;
    }

    public BigDecimal getManageGrowthFund() {
        return manageGrowthFund;
    }

    public void setManageGrowthFund(BigDecimal manageGrowthFund) {
        this.manageGrowthFund = manageGrowthFund;
    }
}