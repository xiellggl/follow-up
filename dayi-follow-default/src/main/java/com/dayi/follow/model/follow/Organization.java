package com.dayi.follow.model.follow;

import com.dayi.follow.util.CheckIdCardUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class Organization {
    private Integer id;
    private Integer secondIncomeSwitch;//二级收益开关(1：开启, -1:关闭)

    private Integer switchStatus; //二级资管状态
    private Integer orgType;//机构类型（1=经济会员，2=综合会员4=创客,5=城市服务商）
    private String idCard;//身份证
    private String linkPerson;//联系人

    @Transient
    private String linkPersonFm;//联系人

    public String getLinkPersonFm() {
        if (!StringUtils.isBlank(idCard)) {
            String substring = linkPerson.substring(0, 1);//截取姓氏
            linkPersonFm = substring + CheckIdCardUtils.getCnGenderByIdCard(idCard);

        }
        return linkPersonFm;
    }

    public void setLinkPersonFm(String linkPersonFm) {
        this.linkPersonFm = linkPersonFm;
    }

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
    }

    public Integer getSecondIncomeSwitch() {
        return secondIncomeSwitch;
    }

    public void setSecondIncomeSwitch(Integer secondIncomeSwitch) {
        this.secondIncomeSwitch = secondIncomeSwitch;
    }
}