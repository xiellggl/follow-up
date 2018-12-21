package com.dayi.follow.model.follow;

import javax.persistence.Table;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class Organization {
    private Integer id;
    private Integer secondIncomeSwitch; /**二级收益开关(1：开启, -1:关闭)*/
    private Integer switchStatus; //二级资管状态
    private Integer orgType;//机构类型（1=经济会员，2=综合会员4=创客,5=城市服务商）
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