package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/7/20
 */
public class FollowOrg extends BaseModel {
    private String followId;//跟进人ID
    private String agentId;//代理商ID
    private String followUpBefore;//之前跟进人
    private Date followDateBefore;//跟进人变更时间

    private Integer customerType;//客户类型
    @Transient
    private String customerTypeStr;//客户类型字符串

    private Integer cusIntentionType;//客户意向度
    @Transient
    private String cusIntentionTypeStr;//客户意向度字符串


    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getFollowUpBefore() {
        return followUpBefore;
    }

    public void setFollowUpBefore(String followUpBefore) {
        this.followUpBefore = followUpBefore;
    }

    public Date getFollowDateBefore() {
        return followDateBefore;
    }

    public void setFollowDateBefore(Date followDateBefore) {
        this.followDateBefore = followDateBefore;
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