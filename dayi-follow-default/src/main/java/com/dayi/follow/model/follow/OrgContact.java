package com.dayi.follow.model.follow;

import com.dayi.follow.enums.ContactTypeEnum;
import com.dayi.mybatis.support.BaseModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * @author xiell
 * @date 2018/7/20
 */
public class OrgContact extends BaseModel {
    @NotNull(message = "创客ID不能为空")
    private Integer orgId;//创客ID
    @NotNull(message = "联系类型不能为空！")
    private Integer contactType;//联系类型
    @NotBlank(message = "联系内容不能为空！")
    private String content;//内容
    private String followId;//跟进人ID

    @Transient
    private String followUp;
    @Transient
    private String contactTypeStr;
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

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
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
}