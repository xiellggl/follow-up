package com.dayi.follow.vo.index;

import com.dayi.follow.util.Misc;

import java.math.BigDecimal;

//管理员客户状态
public class AdminCusStatusVo {
    private Integer followUpNum = 0; // 跟进人数量
    private long waitAssignNum;//待分配用户
    private long followCusNum = 0;  // 跟进客户总数
    private long nameNum = 0; // 已实名认证--用户人数
    private long cardNum = 0;  // 已绑卡--用户人数
    private long agentNum = 0;  // 已代理--用户人数
    private BigDecimal totalFund;  //资产总规模
    private String totalFundFm;  //资产总规模

    public Integer getFollowUpNum() {
        return followUpNum;
    }

    public void setFollowUpNum(Integer followUpNum) {
        this.followUpNum = followUpNum;
    }

    public long getWaitAssignNum() {
        return waitAssignNum;
    }

    public void setWaitAssignNum(long waitAssignNum) {
        this.waitAssignNum = waitAssignNum;
    }

    public long getFollowCusNum() {
        return followCusNum;
    }

    public void setFollowCusNum(long followCusNum) {
        this.followCusNum = followCusNum;
    }

    public long getNameNum() {
        return nameNum;
    }

    public void setNameNum(long nameNum) {
        this.nameNum = nameNum;
    }

    public long getCardNum() {
        return cardNum;
    }

    public void setCardNum(long cardNum) {
        this.cardNum = cardNum;
    }

    public long getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(long agentNum) {
        this.agentNum = agentNum;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public String getTotalFundFm() {
        if (this.totalFund != BigDecimal.ZERO && totalFund != null) {
            totalFundFm = Misc.parseMoney(totalFund.doubleValue(), 2);
        } else {
            totalFundFm = "0.00";
        }
        return totalFundFm;
    }

    public void setTotalFundFm(String totalFundFm) {
        this.totalFundFm = totalFundFm;
    }
}