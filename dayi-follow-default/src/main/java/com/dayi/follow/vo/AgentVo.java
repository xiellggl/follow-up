package com.dayi.follow.vo;

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
public class AgentVo {
    private Integer inviteLevel;//邀请等级
    private String idCard;          //身份证号码
    private String linkPerson;      //联系人
    private String mobile;          //手机号码
    private String recordInviteCode; //注册邀请码记录
    private Date createDate;// 创建日期

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
}