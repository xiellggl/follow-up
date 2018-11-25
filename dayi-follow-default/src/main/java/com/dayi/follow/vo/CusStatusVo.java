package com.dayi.follow.vo;


import java.math.BigDecimal;

public class CusStatusVo {
    private Integer cusNum = 0;  // 客户总数
    private Integer hadLinkNum = 0;  // 已联系--用户人数
    private Integer hadInCashNum = 0;  // 已入金--用户人数
    private Integer hadRealNameNum = 0; // 已实名认证--用户人数
    private Integer hadSignNum = 0;  // 已绑卡--用户人数
    private Integer hadAgentNum = 0;  // 已代理--用户人数
    private Integer hadLostNum = 0;  //流失客户
    private Integer noFundNum = 0;  // 总资产为零--用户人数
    //管理员特有
    private Integer followUpNum = 0; // 跟进人数量
    private Integer waitAssignCusNum;//待分配用户
    private Double totalFund = 0d;  // 代理商资产总规模
    private String totalFundFm;  // 代理商资产总规模

    public Integer getCusNum() {
        return cusNum;
    }

    public void setCusNum(Integer cusNum) {
        this.cusNum = cusNum;
    }

    public Integer getHadLinkNum() {
        return hadLinkNum;
    }

    public void setHadLinkNum(Integer hadLinkNum) {
        this.hadLinkNum = hadLinkNum;
    }

    public Integer getHadInCashNum() {
        return hadInCashNum;
    }

    public void setHadInCashNum(Integer hadInCashNum) {
        this.hadInCashNum = hadInCashNum;
    }

    public Integer getHadRealNameNum() {
        return hadRealNameNum;
    }

    public void setHadRealNameNum(Integer hadRealNameNum) {
        this.hadRealNameNum = hadRealNameNum;
    }

    public Integer getHadSignNum() {
        return hadSignNum;
    }

    public void setHadSignNum(Integer hadSignNum) {
        this.hadSignNum = hadSignNum;
    }

    public Integer getHadAgentNum() {
        return hadAgentNum;
    }

    public void setHadAgentNum(Integer hadAgentNum) {
        this.hadAgentNum = hadAgentNum;
    }

    public Integer getHadLostNum() {
        return hadLostNum;
    }

    public void setHadLostNum(Integer hadLostNum) {
        this.hadLostNum = hadLostNum;
    }

    public Integer getNoFundNum() {
        return noFundNum;
    }

    public void setNoFundNum(Integer noFundNum) {
        this.noFundNum = noFundNum;
    }

    public Integer getFollowUpNum() {
        return followUpNum;
    }

    public void setFollowUpNum(Integer followUpNum) {
        this.followUpNum = followUpNum;
    }

    public Integer getWaitAssignCusNum() {
        return waitAssignCusNum;
    }

    public void setWaitAssignCusNum(Integer waitAssignCusNum) {
        this.waitAssignCusNum = waitAssignCusNum;
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