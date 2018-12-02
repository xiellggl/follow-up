package com.dayi.follow.vo.agent;

import com.dayi.mybatis.support.BaseModel;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author xiell
 * @date 2018/7/20
 */
public class AgentContactVo extends BaseModel {
    private Integer agentId;//代理商ID
    private Integer contactType;//联系类型
    private Integer customerType;//客户类型
    private Integer customerIntentionType;//客户意向度
    private Date nextContactTime;//下次联系时间
    private String content;//内容
    private Integer flowId;//跟进人ID

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public Integer getContactType() {
        return contactType;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public Integer getCustomerIntentionType() {
        return customerIntentionType;
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

    public void setCustomerIntentionType(Integer customerIntentionType) {
        this.customerIntentionType = customerIntentionType;
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
    public void setModifyProperty(AgentContactVo agentContact) {
        if(agentContact != null){ // 更新页面修改属性值
            BeanUtils.copyProperties(agentContact, this);
        }
        this.setUpdateTime(new Date());
    }

}