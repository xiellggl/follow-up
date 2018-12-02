package com.dayi.follow.vo.agent;

import com.dayi.follow.model.follow.Agent;

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

    private double inCash;      // 是否入金

    private Date createDate;       // 注册时间

    private Date followDate;//分配跟进人时间

    private Integer status;//状态
    private String statusStr;//状态

    private double totalFund;//总资产
    private String totalFundFm; // 总资产（格式化：显示前两位和小数点位，其余用*标识）

    private double agentFund;//代理中的资金
    private String agentFundFm;//

    private double frozenFund;//冻结货款
    private String frozenFundFm;//

    private double useableFund;  // 可用余额
    private String useableFundFm;      // 可用余额（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal recentAgentFund;//最近一天代理金额
    private String recentAgentFundFm; // 最近代理（格式化：显示前两位和小数点位，其余用*标识）
    private Date recentAgentDate;//最近代理时间
    private String recentAgentDateFm; // 最近代理时间（格式化）

    private String bankOpen;//已开通结算银行

    private double dayInCash;//当日累计入金
    private String dayInCashFm;//当日累计入金(格式化：显示前两位和小数点位，其余用*标识），用于是否入金判断
    private Date dayLastInCashTime;   // 当日最后一笔入金时间
    private String dayLastInCashTimeFm;  // 当日最后一笔入金时间（格式化）

    private double dayApplyOutCash; // 当日申请出金
    private String dayApplyOutCashFm; // 当日申请出金（格式化：显示前两位和小数点位，其余用*标识）
    private Date dayLastApplyOutCashTime;//当日最后一笔申请出金时间
    private String dayLastApplyOutCashTimeFm;  //当日最后一笔申请出金时间（格式化）

    private double dayOutCash;  // 当日实际出金
    private String dayOutCashFm;      // 当日实际出金（格式化）

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        this.statusStr = "禁用";
        if (this.status == Agent.Status_Locked.id) {
            this.statusStr = "锁定";
        } else if (this.status == Agent.Status_Normal.id) {
            this.statusStr = "启用";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public double getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(double totalFund) {
        this.totalFund = totalFund;
    }

    public String getTotalFundFm() {
        return totalFundFm;
    }

    public void setTotalFundFm(String totalFundFm) {
        this.totalFundFm = totalFundFm;
    }

    public double getAgentFund() {
        return agentFund;
    }

    public void setAgentFund(double agentFund) {
        this.agentFund = agentFund;
    }

    public String getAgentFundFm() {
        return agentFundFm;
    }

    public void setAgentFundFm(String agentFundFm) {
        this.agentFundFm = agentFundFm;
    }

    public double getFrozenFund() {
        return frozenFund;
    }

    public void setFrozenFund(double frozenFund) {
        this.frozenFund = frozenFund;
    }

    public String getFrozenFundFm() {
        return frozenFundFm;
    }

    public void setFrozenFundFm(String frozenFundFm) {
        this.frozenFundFm = frozenFundFm;
    }

    public double getUseableFund() {
        return useableFund;
    }

    public void setUseableFund(double useableFund) {
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

    public double getDayInCash() {
        return dayInCash;
    }

    public void setDayInCash(double dayInCash) {
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

    public double getDayOutCash() {
        return dayOutCash;
    }

    public void setDayOutCash(double dayOutCash) {
        this.dayOutCash = dayOutCash;
    }

    public String getDayOutCashFm() {
        return dayOutCashFm;
    }

    public void setDayOutCashFm(String dayOutCashFm) {
        this.dayOutCashFm = dayOutCashFm;
    }

    public double getDayApplyOutCash() {
        return dayApplyOutCash;
    }

    public void setDayApplyOutCash(double dayApplyOutCash) {
        this.dayApplyOutCash = dayApplyOutCash;
    }

    public String getDayApplyOutCashFm() {
        return dayApplyOutCashFm;
    }

    public void setDayApplyOutCashFm(String dayApplyOutCashFm) {
        this.dayApplyOutCashFm = dayApplyOutCashFm;
    }

    public Date getDayLastApplyOutCashTime() {
        return dayLastApplyOutCashTime;
    }

    public void setDayLastApplyOutCashTime(Date dayLastApplyOutCashTime) {
        this.dayLastApplyOutCashTime = dayLastApplyOutCashTime;
    }

    public String getDayLastApplyOutCashTimeFm() {
        return dayLastApplyOutCashTimeFm;
    }

    public void setDayLastApplyOutCashTimeFm(String dayLastApplyOutCashTimeFm) {
        this.dayLastApplyOutCashTimeFm = dayLastApplyOutCashTimeFm;
    }

    public double getInCash() {
        return inCash;
    }

    public void setInCash(double inCash) {
        this.inCash = inCash;
    }
}