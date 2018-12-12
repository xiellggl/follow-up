package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;
import org.springframework.beans.BeanUtils;

import javax.persistence.Transient;
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

    @Transient
    private String followUp;


    public Integer getCusIntentionType() {
        return cusIntentionType;
    }

    public void setCusIntentionType(Integer cusIntentionType) {
        this.cusIntentionType = cusIntentionType;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public Integer getContactType() {
        return contactType;
    }

    public Integer getCustomerType() {
        return customerType;
    }


    public Date getNextContactTime() {
        return nextContactTime;
    }

    public String getContent() {
        return content;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public void setNextContactTime(Date nextContactTime) {
        this.nextContactTime = nextContactTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /** 修改 -- 设置属性 */
    public void setModifyProperty(AgentContact agentContact) {
        if(agentContact != null){ // 更新页面修改属性值
            BeanUtils.copyProperties(agentContact, this);
        }
        this.setUpdateTime(new Date());
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }
}