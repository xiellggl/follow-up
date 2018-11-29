package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;

import javax.persistence.Transient;

/**
 * @author xiell
 * @date 2018/7/20
 */
public class OrgContact extends BaseModel {
    private Integer orgId;//创客ID
    private Integer contactType;//联系类型
    private String content;//内容
    private String flowId;//跟进人ID

    @Transient
    private String followUp;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }
}