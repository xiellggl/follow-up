package com.dayi.follow.vo.agent;

import com.dayi.common.util.NameItem;
import com.dayi.follow.enums.MemberStatusEnum;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.util.Misc;
import com.dayi.user.model.User;

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

    private BigDecimal inCash;      // 是否入金

    private Date createDate;       // 注册时间

    private Date assignDate;//分配跟进人时间

    private Integer loginStatus;//账户登录状态
    private String loginStatusStr;//账户登录状态
    private Integer tradeStatus;//账户交易状态
    private String tradeStatusStr;//账户交易状态

    private BigDecimal totalFund;//总资产
    private String totalFundFm; // 总资产（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal agentFund;//代理中的资金
    private String agentFundFm;//

    private BigDecimal frozenFund;//冻结货款
    private String frozenFundFm;//冻结货款

    private BigDecimal useableFund;  // 可用余额
    private String useableFundFm;      // 可用余额（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal recentAgentFund;//最近一天代理金额
    private String recentAgentFundFm; // 最近代理（格式化：显示前两位和小数点位，其余用*标识）
    private Date recentAgentDate;//最近代理时间
    private String recentAgentDateFm; // 最近代理时间（格式化）

    private String bankOpen;//已开通结算银行

    private BigDecimal dayInCash;//当日累计入金
    private String dayInCashFm;//当日累计入金(格式化：显示前两位和小数点位，其余用*标识），用于是否入金判断
    private Date dayLastInCashTime;   // 当日最后一笔入金时间

    private BigDecimal dayApplyOutCash; // 当日申请出金
    private String dayApplyOutCashFm; // 当日申请出金（格式化：显示前两位和小数点位，其余用*标识）
    private Date dayLastApplyOutCashTime;//当日最后一笔申请出金时间

    private BigDecimal dayOutCash;  // 当日实际出金
    private String dayOutCashFm;      // 当日实际出金（格式化）

    private BigDecimal assignFund;//分配货款
    private String assignFundFm;

    private BigDecimal hisMaxFund;//历史最高货款
    private String hisMaxFundFm;

    public BigDecimal getAssignFund() {
        return assignFund;
    }

    public String getAssignFundFm() {
        if (this.assignFund != BigDecimal.ZERO && assignFund != null) {
            assignFundFm = Misc.parseMoney(assignFund.doubleValue(), 2) + " 元";
        } else {
            assignFundFm = "0.00 元";
        }
        return assignFundFm;
    }

    public BigDecimal getHisMaxFund() {
        return hisMaxFund;
    }

    public String getHisMaxFundFm() {
        if (this.hisMaxFund != BigDecimal.ZERO && hisMaxFund != null) {
            hisMaxFundFm = Misc.parseMoney(hisMaxFund.doubleValue(), 2) + " 元";
        } else {
            hisMaxFundFm = "0.00 元";
        }
        return hisMaxFundFm;
    }

    public void setAssignFund(BigDecimal assignFund) {
        this.assignFund = assignFund;
    }

    public void setAssignFundFm(String assignFundFm) {
        this.assignFundFm = assignFundFm;
    }

    public void setHisMaxFund(BigDecimal hisMaxFund) {
        this.hisMaxFund = hisMaxFund;
    }

    public void setHisMaxFundFm(String hisMaxFundFm) {
        this.hisMaxFundFm = hisMaxFundFm;
    }

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

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public String getTotalFundFm() {
        if (this.totalFund != BigDecimal.ZERO && totalFund != null) {
            totalFundFm = Misc.parseMoney(totalFund.doubleValue(), 2) + " 元";
        } else {
            totalFundFm = "0.00 元";
        }
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
        if (this.agentFund != BigDecimal.ZERO && agentFund != null) {
            agentFundFm = Misc.parseMoney(agentFund.doubleValue(), 2) + " 元";
        } else {
            agentFundFm = "0.00 元";
        }
        return agentFundFm;
    }

    public void setAgentFundFm(String agentFundFm) {
        this.agentFundFm = agentFundFm;
    }

    public BigDecimal getFrozenFund() {
        return frozenFund;
    }

    public void setFrozenFund(BigDecimal frozenFund) {
        this.frozenFund = frozenFund;
    }

    public String getFrozenFundFm() {
        if (this.frozenFund != BigDecimal.ZERO && frozenFund != null) {
            frozenFundFm = Misc.parseMoney(frozenFund.doubleValue(), 2) + " 元";
        } else {
            frozenFundFm = "0.00 元";
        }
        return frozenFundFm;
    }

    public void setFrozenFundFm(String frozenFundFm) {
        this.frozenFundFm = frozenFundFm;
    }

    public BigDecimal getUseableFund() {
        return useableFund;
    }

    public void setUseableFund(BigDecimal useableFund) {
        this.useableFund = useableFund;
    }

    public String getUseableFundFm() {
        if (this.useableFund != BigDecimal.ZERO && useableFund != null) {
            useableFundFm = Misc.parseMoney(useableFund.doubleValue(), 2) + " 元";
        } else {
            useableFundFm = "0.00 元";
        }
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
        if (this.recentAgentFund != BigDecimal.ZERO && recentAgentFund != null) {
            recentAgentFundFm = Misc.parseMoney(recentAgentFund.doubleValue(), 2) + " 元";
        } else {
            recentAgentFundFm = "0.00 元";
        }
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
        if (this.dayInCash != BigDecimal.ZERO && dayInCash != null) {
            dayInCashFm = Misc.parseMoney(dayInCash.doubleValue(), 2) + " 元";
        } else {
            dayInCashFm = "0.00 元";
        }
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

    public BigDecimal getDayOutCash() {
        return dayOutCash;
    }

    public void setDayOutCash(BigDecimal dayOutCash) {
        this.dayOutCash = dayOutCash;
    }

    public String getDayOutCashFm() {
        if (this.dayOutCash != BigDecimal.ZERO && dayOutCash != null) {
            dayOutCashFm = Misc.parseMoney(dayOutCash.doubleValue(), 2) + " 元";
        } else {
            dayOutCashFm = "0.00 元";
        }
        return dayOutCashFm;
    }

    public void setDayOutCashFm(String dayOutCashFm) {
        this.dayOutCashFm = dayOutCashFm;
    }

    public BigDecimal getDayApplyOutCash() {
        return dayApplyOutCash;
    }

    public void setDayApplyOutCash(BigDecimal dayApplyOutCash) {
        this.dayApplyOutCash = dayApplyOutCash;
    }

    public String getDayApplyOutCashFm() {
        if (this.dayApplyOutCash != BigDecimal.ZERO && dayApplyOutCash != null) {
            dayApplyOutCashFm = Misc.parseMoney(dayApplyOutCash.doubleValue(), 2) + " 元";
        } else {
            dayApplyOutCashFm = "0.00 元";
        }
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

    public BigDecimal getInCash() {
        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginStatusStr() {
        if (loginStatus == null) return null;
        return User.STATUS_ALL.getItem(loginStatus).getName();
    }

    public void setLoginStatusStr(String loginStatusStr) {
        this.loginStatusStr = loginStatusStr;
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeStatusStr() {
        return MemberStatusEnum.getNameByValue(tradeStatus);
    }

    public void setTradeStatusStr(String tradeStatusStr) {
        this.tradeStatusStr = tradeStatusStr;
    }
}