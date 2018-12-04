package com.dayi.follow.vo.org;

import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.AgentIntenTypeEnum;
import com.dayi.follow.util.CheckIdCardUtils;
import com.dayi.follow.util.Misc;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 创客列表
 * @author xiell
 * @date 2018/11/14
 */
public class OrgListVo {
    private Integer id;

    private String linkPerson;//姓名
    private String linkPersonFm;

    private Integer age;//年龄
    private String idCard;//身份证

    private Date createDate;//注册时间，创建时间

    private Integer deadLine;//创客会员期限
    private Date expirationDate;//到期时间
    private String deadLineStr; //创客会员期限字符串

    private Integer agentNum;//代理商数量

    private Integer validAgentNum;//有效代理商

    private BigDecimal manageFund;//管理资产规模
    private String manageFundFm;//管理资产规模

    private String inviteCode;//邀请码

    private String followUp;//跟进人

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

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public BigDecimal getManageFund() {
        return manageFund;
    }

    public void setManageFund(BigDecimal manageFund) {
        this.manageFund = manageFund;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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
}