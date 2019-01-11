package com.dayi.follow.vo.agent;

import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.OrgTypeEnum;
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
    private String mobileFm;

    private String bank;//开户银行
    private String realBank;//实际银行


    private String idCard;//身份证
    private String idCardFm;

    private String bankAccount;//银行账户
    private String bankAccountFm;

    private String followUp;//跟进人

    private Date assignDate;//分配时间

    private String inviteCode;//邀请码

    private String followId;

    //加于兼容其他机构商类型

    private Integer orgType;//机构类型

    private String orgTypeStr;//机构类型字符串

    private Integer experienceMaker;// 是否体验创客

    public String getLinkPerson() {
        return linkPerson;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
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

    public String getMobileFm() {
        if (!StringUtils.isBlank(mobile)) {
            mobileFm = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
        }
        return mobileFm;
    }

    public void setMobileFm(String mobileFm) {
        this.mobileFm = mobileFm;
    }

    public String getIdCardFm() {
        if (!StringUtils.isBlank(idCard)) {
            idCardFm = idCard.substring(0, idCard.length() - 4) + "****";
        }
        return idCardFm;
    }

    public void setIdCardFm(String idCardFm) {
        this.idCardFm = idCardFm;
    }

    public String getBankAccountFm() {
        if (!StringUtils.isBlank(bankAccount)) {
            bankAccountFm = bankAccount.substring(0, 3) + "**********" + bankAccount.substring(bankAccount.length() - 3, bankAccount.length());
        }
        return bankAccountFm;
    }

    public void setBankAccountFm(String bankAccountFm) {
        this.bankAccountFm = bankAccountFm;
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

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getOrgTypeStr() {
        if (orgType != null) {
            switch (orgType) {
                case (1):
                    orgTypeStr = OrgTypeEnum.Manager.getName();
                    break;
                case (2):
                    orgTypeStr = OrgTypeEnum.Colligate.getName();
                    break;
                case (4):
                    if (experienceMaker == 1) {
                        orgTypeStr = "体验创客";
                    } else {
                        orgTypeStr = OrgTypeEnum.Maker.getName();
                    }
                    break;
                case (5):
                    orgTypeStr = OrgTypeEnum.CityServer.getName();
                    break;
            }
        }
        return orgTypeStr;
    }

    public void setOrgTypeStr(String orgTypeStr) {
        this.orgTypeStr = orgTypeStr;
    }

    public Integer getExperienceMaker() {
        return experienceMaker;
    }

    public void setExperienceMaker(Integer experienceMaker) {
        this.experienceMaker = experienceMaker;
    }
}