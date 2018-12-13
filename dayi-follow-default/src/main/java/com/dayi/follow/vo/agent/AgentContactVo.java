package com.dayi.follow.vo.agent;

import com.dayi.component.lock.utils.EnumUtils;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.AgentIntenTypeEnum;
import com.dayi.follow.enums.ContactTypeEnum;
import com.dayi.mybatis.support.BaseModel;
import org.apache.commons.collections.EnumerationUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/7/20
 */
public class AgentContactVo extends BaseModel {
    private Integer agentId;//代理商ID
    private Integer contactType;//联系类型
    private Integer customerType;//客户类型
    private Integer cusIntentionType;//客户意向度
    private Date nextContactTime;//下次联系时间
    private String content;//内容
    private String followId;//跟进人ID

    @Transient
    private String customerTypeStr;//客户类型字符串
    @Transient
    private String cusIntentionTypeStr;//客户意向度字符串
    @Transient
    private String contactTypeStr;//联系类型字符串
    @Transient
    private String followUp;//跟进人

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

    public Integer getCusIntentionType() {
        return cusIntentionType;
    }

    public void setCusIntentionType(Integer cusIntentionType) {
        this.cusIntentionType = cusIntentionType;
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

    public String getCustomerTypeStr() {
        if (customerType != null) {
            switch (customerType) {
                case (1):
                    customerTypeStr = AgentCusTypeEnum.NOT_LINK.getName();
                    break;
                case (2):
                    customerTypeStr = AgentCusTypeEnum.OPEN_ACCOUNT.getName();
                    break;
                case (3):
                    customerTypeStr = AgentCusTypeEnum.CAN_FOLLOWUP.getName();
                    break;
                case (4):
                    customerTypeStr = AgentCusTypeEnum.NO_INTENTION.getName();
                    break;
                case (5):
                    customerTypeStr = AgentCusTypeEnum.LOST.getName();
                    break;
                case (6):
                    customerTypeStr = AgentCusTypeEnum.INVALID.getName();
                    break;
            }
        }
        return customerTypeStr;
    }

    public void setCustomerTypeStr(String customerTypeStr) {
        this.customerTypeStr = customerTypeStr;
    }

    public String getCusIntentionTypeStr() {
        if (cusIntentionType != null) {
            switch (cusIntentionType) {
                case (1):
                    cusIntentionTypeStr = AgentIntenTypeEnum.STRONG.getName();
                    break;
                case (2):
                    cusIntentionTypeStr = AgentIntenTypeEnum.MIDDLE.getName();
                    break;
                case (3):
                    cusIntentionTypeStr = AgentIntenTypeEnum.WEAK.getName();
                    break;
                case (4):
                    cusIntentionTypeStr = AgentIntenTypeEnum.NO.getName();
                    break;
            }
        }
        return cusIntentionTypeStr;
    }

    public void setCusIntentionTypeStr(String cusIntentionTypeStr) {
        this.cusIntentionTypeStr = cusIntentionTypeStr;
    }

    public String getContactTypeStr() {
        if (contactType != null) {
            switch (contactType) {
                case (1):
                    contactTypeStr = ContactTypeEnum.PHONE.getName();
                    break;
                case (2):
                    contactTypeStr = ContactTypeEnum.WECHAT.getName();
                    break;
                case (3):
                    contactTypeStr = ContactTypeEnum.QQ.getName();
                    break;
                case (4):
                    contactTypeStr = ContactTypeEnum.EMAIL.getName();
                    break;
                case (5):
                    contactTypeStr = ContactTypeEnum.MESSAGE.getName();
                    break;
            }
        }
        return contactTypeStr;
    }

    public void setContactTypeStr(String contactTypeStr) {
        this.contactTypeStr = contactTypeStr;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }


}