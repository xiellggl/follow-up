package com.dayi.follow.vo.index;

//客户状态
public class CusStatusVo {
    private long cusNum = 0;  // 客户总数
    private long hadLinkNum = 0;  // 已联系--用户人数
    private long hadInCashNum = 0;  // 已入金--用户人数
    private long hadRealNameNum = 0; // 已实名认证--用户人数
    private long hadSignNum = 0;  // 已绑卡--用户人数
    private long hadAgentNum = 0;  // 已代理--用户人数
    private long hadLostNum = 0;  //流失客户
    private long noFundNum = 0;  // 总资产为零--用户人数

    public long getCusNum() {
        return cusNum;
    }

    public void setCusNum(long cusNum) {
        this.cusNum = cusNum;
    }

    public long getHadLinkNum() {
        return hadLinkNum;
    }

    public void setHadLinkNum(long hadLinkNum) {
        this.hadLinkNum = hadLinkNum;
    }

    public long getHadInCashNum() {
        return hadInCashNum;
    }

    public void setHadInCashNum(long hadInCashNum) {
        this.hadInCashNum = hadInCashNum;
    }

    public long getHadRealNameNum() {
        return hadRealNameNum;
    }

    public void setHadRealNameNum(long hadRealNameNum) {
        this.hadRealNameNum = hadRealNameNum;
    }

    public long getHadSignNum() {
        return hadSignNum;
    }

    public void setHadSignNum(long hadSignNum) {
        this.hadSignNum = hadSignNum;
    }

    public long getHadAgentNum() {
        return hadAgentNum;
    }

    public void setHadAgentNum(long hadAgentNum) {
        this.hadAgentNum = hadAgentNum;
    }

    public long getHadLostNum() {
        return hadLostNum;
    }

    public void setHadLostNum(long hadLostNum) {
        this.hadLostNum = hadLostNum;
    }

    public long getNoFundNum() {
        return noFundNum;
    }

    public void setNoFundNum(long noFundNum) {
        this.noFundNum = noFundNum;
    }
}