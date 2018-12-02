package com.dayi.follow.vo.agent;

import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.AgentIntenTypeEnum;
import com.dayi.follow.util.CheckIdCardUtils;
import com.dayi.follow.util.Misc;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class AgentListVo {
    private Integer id;//代理商id

    private String linkPerson;//姓名
    private String linkPersonFm;//联系人的姓名(格式化：男的：先生，女的：女士）

    private Date createDate;//注册时间

    private String mobile;// //手机号码

    private String idCard;//身份证号,是否为空-用于判断是否实名
    private Date cardValidDate;//实名认证时间

    private boolean bankSign;//是否绑卡,同时作为是否开通结算银行的条件
    private Date bankSignDate; //绑卡时间

    private BigDecimal inCash;//入金，是否为空-用于是否入金判断
    private Date fristInCashDate; //首次入金时间

    private BigDecimal dayInCash;//当日累计入金
    private String dayInCashFm;//当日累计入金(格式化：显示前两位和小数点位，其余用*标识），用于是否入金判断
    private Date dayLastInCashTime;   // 当日最后一笔入金时间
    private String dayLastInCashTimeFm;  // 当日最后一笔入金时间（格式化）

    private BigDecimal dayOutCash; // 当日申请出金
    private String dayOutCashFm; // 当日申请出金（格式化：显示前两位和小数点位，其余用*标识）
    private Date dayLastOutCashTime;//当日最后一笔申请出金时间
    private String dayLastOutCashTimeFm;  // 当日最后一笔申请出金时间（格式化）

    private BigDecimal totalFund;//总资产
    private String totalFundFm; // 总资产（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal recentAgentFund;//最近一天代理金额
    private String recentAgentFundFm; // 最近代理（格式化：显示前两位和小数点位，其余用*标识）
    private Date recentAgentDate;//最近代理时间
    private String recentAgentDateFm; // 最近代理时间（格式化）

    private Integer customerType;//客户类型(1：未联系, 2：已开户, 3：可被跟进, 4：无意向, 5：流失, 6：无效)
    private String customerTypeStr;//客户类型字符串

    private Integer cusIntentionType;//客户意向度
    private String cusIntentionTypeStr;//客户意向度字符串

    private Date lastLoginDate;//最后登录时间

    private String bankName;//当前结算银行

    private String contactContent;//最近联系内容
    private Date contactDate;//最近联系时间

    private String followUp;//跟进人

    private String recordInviteCode;      //邀请码

    private String flowUpInviteCode;//客户跟进人的邀请码

    private Integer flowId;//跟进人id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public String getLinkPersonFm() {
        if (!StringUtils.isBlank(linkPerson) && !StringUtils.isBlank(idCard)) {
            String substring = linkPerson.substring(0, 1);//截取姓氏
            String nameFormat = substring + CheckIdCardUtils.getCnGenderByIdCard(idCard);
            linkPersonFm = nameFormat;
        }
        return linkPersonFm;
    }

    public void setLinkPersonFm(String linkPersonFm) {
        this.linkPersonFm = linkPersonFm;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public BigDecimal getInCash() {
        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public Date getFristInCashDate() {
        return fristInCashDate;
    }

    public void setFristInCashDate(Date fristInCashDate) {
        this.fristInCashDate = fristInCashDate;
    }

    public BigDecimal getDayInCash() {
        return dayInCash;
    }

    public void setDayInCash(BigDecimal dayInCash) {
        this.dayInCash = dayInCash;
    }

    public String getDayInCashFm() {
        if(this.dayInCash != null){
            dayInCashFm = Misc.parseMoney(dayInCash.doubleValue(), 2);
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

    public String getDayLastInCashTimeFm() {
        if(dayLastInCashTime != null){
            dayLastInCashTimeFm = new DateTime(dayLastInCashTime).toString("HH:mm:ss");
        }
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
        if(this.dayOutCash != null){
            dayOutCashFm = Misc.parseMoney(dayOutCash.doubleValue(), 2);
        }
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
        if(dayLastOutCashTime != null){
            dayLastOutCashTimeFm = new DateTime(dayLastOutCashTime).toString("HH:mm:ss");
        }
        return dayLastOutCashTimeFm;
    }

    public void setDayLastOutCashTimeFm(String dayLastOutCashTimeFm) {
        this.dayLastOutCashTimeFm = dayLastOutCashTimeFm;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public String getTotalFundFm() {
        if(this.totalFund != null){
            totalFundFm = Misc.parseMoney(totalFund.doubleValue(), 2);
        }
        return totalFundFm;
    }

    public void setTotalFundFm(String totalFundFm) {
        this.totalFundFm = totalFundFm;
    }

    public BigDecimal getRecentAgentFund() {
        return recentAgentFund;
    }

    public void setRecentAgentFund(BigDecimal recentAgentFund) {
        this.recentAgentFund = recentAgentFund;
    }

    public String getRecentAgentFundFm() {
        if(this.recentAgentFund != null){
            recentAgentFundFm = Misc.parseMoney(recentAgentFund.doubleValue(), 2);
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
        if(recentAgentDate != null){
            recentAgentDateFm = new DateTime(recentAgentDate).toString("yyyy-MM-dd HH:mm:ss");
        }
        return recentAgentDateFm;
    }

    public void setRecentAgentDateFm(String recentAgentDateFm) {
        this.recentAgentDateFm = recentAgentDateFm;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCustomerTypeStr() {
        if (customerType != null) {
            switch (customerType) {
                case (1):
                    customerTypeStr = AgentCusTypeEnum.NOT_LINK.getName();
                    break;
                case (2):
                    customerTypeStr = AgentCusTypeEnum.OPEN_ACCOUNT.getName();
                    break;
                case (3):
                    customerTypeStr = AgentCusTypeEnum.CAN_FOLLOWUP.getName();
                    break;
                case (4):
                    customerTypeStr = AgentCusTypeEnum.NO_INTENTION.getName();
                    break;
                case (5):
                    customerTypeStr = AgentCusTypeEnum.LOST.getName();
                    break;
                case (6):
                    customerTypeStr = AgentCusTypeEnum.INVALID.getName();
                    break;
            }
        }
        return customerTypeStr;
    }

    public void setCustomerTypeStr(String customerTypeStr) {
        this.customerTypeStr = customerTypeStr;
    }

    public Integer getCusIntentionType() {
        return cusIntentionType;
    }

    public void setCusIntentionType(Integer cusIntentionType) {
        this.cusIntentionType = cusIntentionType;
    }

    public String getCusIntentionTypeStr() {
        if (cusIntentionType != null) {
            switch (cusIntentionType) {
                case (1):
                    cusIntentionTypeStr = AgentIntenTypeEnum.STRONG.getName();
                    break;
                case (2):
                    cusIntentionTypeStr = AgentIntenTypeEnum.MIDDLE.getName();
                    break;
                case (3):
                    cusIntentionTypeStr = AgentIntenTypeEnum.WEAK.getName();
                    break;
                case (4):
                    cusIntentionTypeStr = AgentIntenTypeEnum.NO.getName();
                    break;
            }
        }
        return cusIntentionTypeStr;
    }

    public void setCusIntentionTypeStr(String cusIntentionTypeStr) {
        this.cusIntentionTypeStr = cusIntentionTypeStr;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getContactContent() {
        return contactContent;
    }

    public void setContactContent(String contactContent) {
        this.contactContent = contactContent;
    }

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public String getRecordInviteCode() {
        return recordInviteCode;
    }

    public void setRecordInviteCode(String recordInviteCode) {
        this.recordInviteCode = recordInviteCode;
    }

    public String getFlowUpInviteCode() {
        return flowUpInviteCode;
    }

    public void setFlowUpInviteCode(String flowUpInviteCode) {
        this.flowUpInviteCode = flowUpInviteCode;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }
}