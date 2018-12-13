package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/7/20
 */
public class AgentContact extends BaseModel {
    @NotNull(message = "代理商ID不能为空！")
    private Integer agentId;//代理商ID
    @NotNull(message = "联系类型不能为空！")
    private Integer contactType;//联系类型
    @NotNull(message = "客户类型不能为空！")
    private Integer customerType;//客户类型
    @NotNull(message = "客户意向度不能为空！")
    private Integer cusIntentionType;//客户意向度
    private Date nextContactTime;//下次联系时间
    private String content;//内容
    private String followId;//跟进人ID


    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
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

    public Date getNextContactTime() {
        return nextContactTime;
    }

    public void setNextContactTime(Date nextContactTime) {
        this.nextContactTime = nextContactTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }
}