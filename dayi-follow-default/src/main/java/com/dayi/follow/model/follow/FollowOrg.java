package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/7/20
 */
public class FollowOrg extends BaseModel {
    @NotBlank(message = "请选择跟进人！")
    private String followId;//跟进人ID
    @NotNull(message = "请选择创客！")
    private Integer orgId;//创客ID
    private String followUpBefore;//之前跟进人
    private Date assignDateBefore;//跟进人变更时间
    private Date assignDate;//分配跟进人时间

    private BigDecimal manageFundBefore;//变更跟进人前的管理资产

    public Date getAssignDateBefore() {
        return assignDateBefore;
    }

    public void setAssignDateBefore(Date assignDateBefore) {
        this.assignDateBefore = assignDateBefore;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public BigDecimal getManageFundBefore() {
        return manageFundBefore;
    }

    public void setManageFundBefore(BigDecimal manageFundBefore) {
        this.manageFundBefore = manageFundBefore;
    }

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

}