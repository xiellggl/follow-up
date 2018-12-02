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
    private Integer orgId;//创客ID
    private String followUpBefore;//之前跟进人
    private Date followDateBefore;//跟进人变更时间
    private Date followDate;//分配跟进人时间

    private String createBy;//创建人
    private String updateBy;//更新人

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}