package com.dayi.follow.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class DetailVo {
    private String linkPerson;     // 名称
    private String linkPersonFm;   // 名称（格式化：只显示姓氏）

    private String mobile;         // 手机号

    private String idCardAddr;     // 身份证所在地

    private String inviteCode;     // 邀请码

    private String dateStr;//出生月日

    private Integer age;//年龄

    private String idCard;//身份证号,是否为空-用于判断是否实名
    private Date cardValidDate;//实名认证时间

    private boolean bankSign;//是否绑卡
    private Date bankSignDate; //绑卡时间

    private boolean isInCash;      // 是否入金

    private Date createDate;       // 注册时间

    private Date followDate;//分配跟进人时间

    private String status;//状态

    private BigDecimal totalFund;//总资产
    private String totalFundFm; // 总资产（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal agentFund;//代理中的资金
    private String agentFundFm;//

    private BigDecimal freezeFund;//冻结货款
    private String freezeFundFm;//

    private BigDecimal useableFund = BigDecimal.valueOf(0);  // 可用余额
    private String useableFundFm;      // 可用余额（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal recentAgentFund;//最近一天代理金额
    private String recentAgentFundFm; // 最近代理（格式化：显示前两位和小数点位，其余用*标识）
    private Date recentAgentDate;//最近代理时间
    private String recentAgentDateFm; // 最近代理时间（格式化）

    private String bankOpen;//已开通结算银行

    private BigDecimal dayInCash;//当日累计入金
    private String dayInCashFm;//当日累计入金(格式化：显示前两位和小数点位，其余用*标识），用于是否入金判断
    private Date dayLastInCashTime;   // 当日最后一笔入金时间
    private String dayLastInCashTimeFm;  // 当日最后一笔入金时间（格式化）

    private BigDecimal dayOutCash; // 当日申请出金
    private String dayOutCashFm; // 当日申请出金（格式化：显示前两位和小数点位，其余用*标识）
    private Date dayLastOutCashTime;//当日最后一笔申请出金时间
    private String dayLastOutCashTimeFm;  //当日最后一笔申请出金时间（格式化）

    private BigDecimal outCash = BigDecimal.valueOf(0);  // 实际出金
    private String outCashFm;      // 实际出金（格式化）

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public String getLinkPersonFm() {
        return linkPersonFm;
    }

    public void setLinkPersonFm(String linkPersonFm) {
        this.linkPersonFm = linkPersonFm;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCardAddr() {
        return idCardAddr;
    }

    public void setIdCardAddr(String idCardAddr) {
        this.idCardAddr = idCardAddr;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getCardValidDate() {
        return cardValidDate;
    }

    public void setCardValidDate(Date cardValidDate) {
        this.cardValidDate = cardValidDate;
    }

    public boolean isBankSign() {
        return bankSign;
    }

    public void setBankSign(boolean bankSign) {
        this.bankSign = bankSign;
    }

    public Date getBankSignDate() {
        return bankSignDate;
    }

    public void setBankSignDate(Date bankSignDate) {
        this.bankSignDate = bankSignDate;
    }

    public boolean isInCash() {
        return isInCash;
    }

    public void setInCash(boolean inCash) {
        isInCash = inCash;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public String getTotalFundFm() {
        return totalFundFm;
    }

    public void setTotalFundFm(String totalFundFm) {
        this.totalFundFm = totalFundFm;
    }

    public BigDecimal getAgentFund() {
        return agentFund;
    }

    public void setAgentFund(BigDecimal agentFund) {
        this.agentFund = agentFund;
    }

    public String getAgentFundFm() {
        return agentFundFm;
    }

    public void setAgentFundFm(String agentFundFm) {
        this.agentFundFm = agentFundFm;
    }

    public BigDecimal getFreezeFund() {
        return freezeFund;
    }

    public void setFreezeFund(BigDecimal freezeFund) {
        this.freezeFund = freezeFund;
    }

    public String getFreezeFundFm() {
        return freezeFundFm;
    }

    public void setFreezeFundFm(String freezeFundFm) {
        this.freezeFundFm = freezeFundFm;
    }

    public BigDecimal getUseableFund() {
        return useableFund;
    }

    public void setUseableFund(BigDecimal useableFund) {
        this.useableFund = useableFund;
    }

    public String getUseableFundFm() {
        return useableFundFm;
    }

    public void setUseableFundFm(String useableFundFm) {
        this.useableFundFm = useableFundFm;
    }

    public BigDecimal getRecentAgentFund() {
        return recentAgentFund;
    }

    public void setRecentAgentFund(BigDecimal recentAgentFund) {
        this.recentAgentFund = recentAgentFund;
    }

    public String getRecentAgentFundFm() {
        return recentAgentFundFm;
    }

    public void setRecentAgentFundFm(String recentAgentFundFm) {
        this.recentAgentFundFm = recentAgentFundFm;
    }

    public Date getRecentAgentDate() {
        return recentAgentDate;
    }

    public void setRecentAgentDate(Date recentAgentDate) {
        this.recentAgentDate = recentAgentDate;
    }

    public String getRecentAgentDateFm() {
        return recentAgentDateFm;
    }

    public void setRecentAgentDateFm(String recentAgentDateFm) {
        this.recentAgentDateFm = recentAgentDateFm;
    }

    public String getBankOpen() {
        return bankOpen;
    }

    public void setBankOpen(String bankOpen) {
        this.bankOpen = bankOpen;
    }

    public BigDecimal getDayInCash() {
        return dayInCash;
    }

    public void setDayInCash(BigDecimal dayInCash) {
        this.dayInCash = dayInCash;
    }

    public String getDayInCashFm() {
        return dayInCashFm;
    }

    public void setDayInCashFm(String dayInCashFm) {
        this.dayInCashFm = dayInCashFm;
    }

    public Date getDayLastInCashTime() {
        return dayLastInCashTime;
    }

    public void setDayLastInCashTime(Date dayLastInCashTime) {
        this.dayLastInCashTime = dayLastInCashTime;
    }

    public String getDayLastInCashTimeFm() {
        return dayLastInCashTimeFm;
    }

    public void setDayLastInCashTimeFm(String dayLastInCashTimeFm) {
        this.dayLastInCashTimeFm = dayLastInCashTimeFm;
    }

    public BigDecimal getDayOutCash() {
        return dayOutCash;
    }

    public void setDayOutCash(BigDecimal dayOutCash) {
        this.dayOutCash = dayOutCash;
    }

    public String getDayOutCashFm() {
        return dayOutCashFm;
    }

    public void setDayOutCashFm(String dayOutCashFm) {
        this.dayOutCashFm = dayOutCashFm;
    }

    public Date getDayLastOutCashTime() {
        return dayLastOutCashTime;
    }

    public void setDayLastOutCashTime(Date dayLastOutCashTime) {
        this.dayLastOutCashTime = dayLastOutCashTime;
    }

    public String getDayLastOutCashTimeFm() {
        return dayLastOutCashTimeFm;
    }

    public void setDayLastOutCashTimeFm(String dayLastOutCashTimeFm) {
        this.dayLastOutCashTimeFm = dayLastOutCashTimeFm;
    }

    public BigDecimal getOutCash() {
        return outCash;
    }

    public void setOutCash(BigDecimal outCash) {
        this.outCash = outCash;
    }

    public String getOutCashFm() {
        return outCashFm;
    }

    public void setOutCashFm(String outCashFm) {
        this.outCashFm = outCashFm;
    }
}