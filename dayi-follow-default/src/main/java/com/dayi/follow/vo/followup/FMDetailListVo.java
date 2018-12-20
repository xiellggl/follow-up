package com.dayi.follow.vo.followup;

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
 * 跟进人管理中的明细列表,代理商和创客共用
 */
public class FMDetailListVo {
    private Integer id;//id

    private String linkPerson;//姓名

    private String mobile;// //手机号码

    private Date createDate;//注册时间

    private String inviteCode;//邀请码

    private String followUp;//当前跟进人

    private String followUpBefore;//之前跟进人

    private Date changeDate;//变更时间

    private BigDecimal agentCargoBefore;//变更前代理货值

    private BigDecimal agentCargo;//代理货值

    private BigDecimal growthCargo;//净增货值

    private BigDecimal totalFundBefore;//变更前总资产

    private BigDecimal totalFund;//总资产

    private BigDecimal growthFund;//净增总资产

    private BigDecimal interest;//利息（服务费）

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

    public String getMobile() {
        return mobile;
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

    public String getFollowUpBefore() {
        return followUpBefore;
    }

    public void setFollowUpBefore(String followUpBefore) {
        this.followUpBefore = followUpBefore;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public BigDecimal getAgentCargoBefore() {
        return agentCargoBefore;
    }

    public void setAgentCargoBefore(BigDecimal agentCargoBefore) {
        this.agentCargoBefore = agentCargoBefore;
    }

    public BigDecimal getAgentCargo() {
        return agentCargo;
    }

    public void setAgentCargo(BigDecimal agentCargo) {
        this.agentCargo = agentCargo;
    }

    public BigDecimal getGrowthCargo() {
        return growthCargo;
    }

    public void setGrowthCargo(BigDecimal growthCargo) {
        this.growthCargo = growthCargo;
    }

    public BigDecimal getTotalFundBefore() {
        return totalFundBefore;
    }

    public void setTotalFundBefore(BigDecimal totalFundBefore) {
        this.totalFundBefore = totalFundBefore;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public BigDecimal getGrowthFund() {
        return growthFund;
    }

    public void setGrowthFund(BigDecimal growthFund) {
        this.growthFund = growthFund;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }
}