package com.dayi.follow.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class OrgTeamListVo {

    private String linkPerson;//姓名
    private String linkPersonFm;

    private String mobile;//手机号

    private Integer age;//年龄

    private Date createDate;//注册时间，创建时间

    private Integer deadLine;//创客会员期限
    private String deadLineStr; //创客会员期限字符串

    private Integer agentNum;//代理商数量

    private Integer validAgentNum;//有效代理商

    private BigDecimal manageFund;//管理资产规模
    private String manageFundFm;//管理资产规模

    private String inviteCode;//邀请码

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Integer deadLine) {
        this.deadLine = deadLine;
    }

    public String getDeadLineStr() {
        return deadLineStr;
    }

    public void setDeadLineStr(String deadLineStr) {
        this.deadLineStr = deadLineStr;
    }

    public Integer getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(Integer agentNum) {
        this.agentNum = agentNum;
    }

    public Integer getValidAgentNum() {
        return validAgentNum;
    }

    public void setValidAgentNum(Integer validAgentNum) {
        this.validAgentNum = validAgentNum;
    }

    public String getManageFundFm() {
        return manageFundFm;
    }

    public void setManageFundFm(String manageFundFm) {
        this.manageFundFm = manageFundFm;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public BigDecimal getManageFund() {
        return manageFund;
    }

    public void setManageFund(BigDecimal manageFund) {
        this.manageFund = manageFund;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}