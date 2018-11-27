package com.dayi.follow.vo;


public class SerCusStatusVo {
    private Integer followUpNum = 0; // 跟进人数量
    private long waitAssignNum;//待分配用户
    private long followCusNum = 0;  // 跟进客户总数
    private long nameNum = 0; // 已实名认证--用户人数
    private long cardNum = 0;  // 已绑卡--用户人数
    private long agentNum = 0;  // 已代理--用户人数
    private Double totalFund = 0d;  //资产总规模
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

    public Double getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(Double totalFund) {
        this.totalFund = totalFund;
    }

    public String getTotalFundFm() {
        return totalFundFm;
    }

    public void setTotalFundFm(String totalFundFm) {
        this.totalFundFm = totalFundFm;
    }
}