package com.dayi.follow.vo;

import com.dayi.follow.util.CheckIdCardUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class AssignListVo {
    private Integer id;

    private String linkPerson;//姓名
    private String linkPersonFm;

    private Date createDate;//注册时间，创建时间

    private String mobile;//手机号

    private String bank;//开户银行
    private String realBank;//实际银行


    private String idCard;//身份证

    private String bankAccount;//银行账户

    private String followUp;//跟进人

    private Date assignDate;//分配时间

    private String inviteCode;//邀请码

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public String getLinkPersonFm() {
        if (!StringUtils.isBlank(idCard)) {
            String substring = linkPerson.substring(0, 1);//截取姓氏
            linkPersonFm = substring + CheckIdCardUtils.getCnGenderByIdCard(idCard);
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

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getRealBank() {
        return realBank;
    }

    public void setRealBank(String realBank) {
        this.realBank = realBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }
}