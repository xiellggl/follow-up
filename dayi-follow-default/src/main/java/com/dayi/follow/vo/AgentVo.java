package com.dayi.follow.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class AgentVo {
    private String id;
    private String linkPerson;//姓名
    private String bankName;//银行名称
    private Date cardValidDate;//实名认证时间
    private Date bankSignDate; //绑卡时间
    private String mobile;// //手机号码
    private Integer idCardValidate; //身份证认证状态（-1=认证失败，0=未认证，1=已认证待审核，2=认证成功）
    private Integer cancelStatus;   // 解绑销户状态：-9--正常情况（未签约或已签约）；2--解绑审核中；3--已解绑
    private Integer auditStatus;    //审核状态（-2=审核失败，-1=未提交审核，0=待审核，1=审核成功）
    private boolean bankSign;       //银行是否已签约（0=未签约，1=已签约）
    private String recordInviteCode;      //邀请码
    private String idCard;          //身份证号码
    private Integer linkStatus; //联系状态
    private Date flowDate;//分配时间
    private String followUp;//跟进人
    private BigDecimal inCashAccu;//累计入金
    private BigDecimal todayInCash;//当日累计入金
    private Date fristInCashDate; //首次入金时间
    private BigDecimal totalFund;  // 总资产
    private BigDecimal outCashFrozen;  // 当日累计申请出金金额
    private String bankIds;//已开通结算银行id
    private Integer customerType;//客户类型(1：未联系, 2：已开户, 3：可被跟进, 4：无意向, 5：流失, 6：无效)
    private String bankAccount;     //银行账户
    private String ucId;            //用户中心id
    private Integer cusIntentionType;//客户意向度
    private String flowUpInviteCode;//此客户跟进人的邀请码
    private Integer flowId;//跟进人id
    private Date lastInCashDate;   // 当日最后一笔入金时间
    private Date lastOutCashFrozenDate;     // 当日最后一笔申请出金时间
    private Date createDate;

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCardValidDate() {
        return cardValidDate;
    }

    public void setCardValidDate(Date cardValidDate) {
        this.cardValidDate = cardValidDate;
    }

    public Date getBankSignDate() {
        return bankSignDate;
    }

    public void setBankSignDate(Date bankSignDate) {
        this.bankSignDate = bankSignDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIdCardValidate() {
        return idCardValidate;
    }

    public void setIdCardValidate(Integer idCardValidate) {
        this.idCardValidate = idCardValidate;
    }

    public Integer getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(Integer cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public boolean isBankSign() {
        return bankSign;
    }

    public void setBankSign(boolean bankSign) {
        this.bankSign = bankSign;
    }

    public String getRecordInviteCode() {
        return recordInviteCode;
    }

    public void setRecordInviteCode(String recordInviteCode) {
        this.recordInviteCode = recordInviteCode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
    }

    public Date getFlowDate() {
        return flowDate;
    }

    public void setFlowDate(Date flowDate) {
        this.flowDate = flowDate;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public BigDecimal getInCashAccu() {
        return inCashAccu;
    }

    public void setInCashAccu(BigDecimal inCashAccu) {
        this.inCashAccu = inCashAccu;
    }

    public BigDecimal getTodayInCash() {
        return todayInCash;
    }

    public void setTodayInCash(BigDecimal todayInCash) {
        this.todayInCash = todayInCash;
    }

    public Date getFristInCashDate() {
        return fristInCashDate;
    }

    public void setFristInCashDate(Date fristInCashDate) {
        this.fristInCashDate = fristInCashDate;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public BigDecimal getOutCashFrozen() {
        return outCashFrozen;
    }

    public void setOutCashFrozen(BigDecimal outCashFrozen) {
        this.outCashFrozen = outCashFrozen;
    }

    public String getBankIds() {
        return bankIds;
    }

    public void setBankIds(String bankIds) {
        this.bankIds = bankIds;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getUcId() {
        return ucId;
    }

    public void setUcId(String ucId) {
        this.ucId = ucId;
    }

    public Integer getCusIntentionType() {
        return cusIntentionType;
    }

    public void setCusIntentionType(Integer cusIntentionType) {
        this.cusIntentionType = cusIntentionType;
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

    public Date getLastInCashDate() {
        return lastInCashDate;
    }

    public void setLastInCashDate(Date lastInCashDate) {
        this.lastInCashDate = lastInCashDate;
    }

    public Date getLastOutCashFrozenDate() {
        return lastOutCashFrozenDate;
    }

    public void setLastOutCashFrozenDate(Date lastOutCashFrozenDate) {
        this.lastOutCashFrozenDate = lastOutCashFrozenDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}