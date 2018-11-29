package com.dayi.follow.model.follow;

import javax.persistence.Table;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class Organization {
    private Integer id;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer switchStatus; //二级资管状态

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
    }

    /**二级收益开关(1：开启, -1:关闭)*/
    private Integer secondIncomeSwitch;

    public Integer getSecondIncomeSwitch() {
        return secondIncomeSwitch;
    }

    public void setSecondIncomeSwitch(Integer secondIncomeSwitch) {
        this.secondIncomeSwitch = secondIncomeSwitch;
    }
}