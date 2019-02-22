package com.dayi.follow.model.follow;

import com.dayi.common.util.NameItem;

import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class Agent {
    private Integer id;
    private Integer inviteLevel;//邀请等级
    private String idCard;          //身份证号码
    private String linkPerson;      //联系人
    private String mobile;          //手机号码
    private String recordInviteCode; //注册邀请码记录
    private Date createDate;// 创建日期
    private Date cardValidDate;     //实名认证时间
    private boolean bankSign;      //银行是否已签约（0=未签约，1=已签约）,现在也用来判断是否绑卡
    private Date bankSignDate;      //绑卡时间
    private Integer status;         //会员状态（-2=禁用,-1=锁定,0=待审核,1=正常）
    private String idCardAddr;      //身份证所在地
    private String ucId;//用户中心id
    private Integer lockType;//锁定类型





    /**
     * 会员状态：-2--禁用；-1--锁定；0--待审核；1--正常
     */
    public final static NameItem Status_Forbid = NameItem.valueOf("禁用", -2);
    public final static NameItem Status_Locked = NameItem.valueOf("锁定", -1);
    public final static NameItem Status_Auditing = NameItem.valueOf("待审核", 0);
    public final static NameItem Status_Normal = NameItem.valueOf("正常", 1);


    public Integer getInviteLevel() {
        return inviteLevel;
    }

    public void setInviteLevel(Integer inviteLevel) {
        this.inviteLevel = inviteLevel;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRecordInviteCode() {
        return recordInviteCode;
    }

    public void setRecordInviteCode(String recordInviteCode) {
        this.recordInviteCode = recordInviteCode;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdCardAddr() {
        return idCardAddr;
    }

    public void setIdCardAddr(String idCardAddr) {
        this.idCardAddr = idCardAddr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUcId() {
        return ucId;
    }

    public void setUcId(String ucId) {
        this.ucId = ucId;
    }

    public Integer getLockType() {
        return lockType;
    }

    public void setLockType(Integer lockType) {
        this.lockType = lockType;
    }
}