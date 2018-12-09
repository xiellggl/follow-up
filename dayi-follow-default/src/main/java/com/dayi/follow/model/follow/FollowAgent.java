package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/7/20
 */
public class FollowAgent extends BaseModel {
    @NotBlank(message = "请选择跟进人！")
    private String followId;//跟进人ID
    @NotNull(message = "请选择代理商！")
    private Integer agentId;//代理商ID
    private String followUpBefore;//之前跟进人
    private Date assignDateBefore;//跟进人变更时间

    private Integer customerType = 1;//客户类型
    @Transient
    private String customerTypeStr;//客户类型字符串

    private Integer cusIntentionType;//客户意向度
    @Transient
    private String cusIntentionTypeStr;//客户意向度字符串

    private BigDecimal agentFundBefore;//变更跟进人前代理资金
    private BigDecimal totalFundBefore;//变更跟进人前总资产

    private Date assignDate;//分配跟进人时间

    public BigDecimal getAgentFundBefore() {
        return agentFundBefore;
    }

    public void setAgentFundBefore(BigDecimal agentFundBefore) {
        this.agentFundBefore = agentFundBefore;
    }

    public BigDecimal getTotalFundBefore() {
        return totalFundBefore;
    }

    public void setTotalFundBefore(BigDecimal totalFundBefore) {
        this.totalFundBefore = totalFundBefore;
    }

    public Date getAssignDateBefore() {
        return assignDateBefore;
    }

    public void setAssignDateBefore(Date assignDateBefore) {
        this.assignDateBefore = assignDateBefore;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getFollowUpBefore() {
        return followUpBefore;
    }

    public void setFollowUpBefore(String followUpBefore) {
        this.followUpBefore = followUpBefore;
    }


    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getCusIntentionType() {
        return cusIntentionType;
    }

    public void setCusIntentionType(Integer cusIntentionType) {
        this.cusIntentionType = cusIntentionType;
    }

    public String getCustomerTypeStr() {
        return customerTypeStr;
    }

    public void setCustomerTypeStr(String customerTypeStr) {
        this.customerTypeStr = customerTypeStr;
    }

    public String getCusIntentionTypeStr() {
        return cusIntentionTypeStr;
    }

    public void setCusIntentionTypeStr(String cusIntentionTypeStr) {
        this.cusIntentionTypeStr = cusIntentionTypeStr;
    }
}