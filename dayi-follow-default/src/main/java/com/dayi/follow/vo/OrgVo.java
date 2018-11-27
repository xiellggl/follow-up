package com.dayi.follow.vo;

import javax.persistence.Table;

/**
 * @author xiell
 * @date 2018/11/14
 */
@Table(name = "Organization")
public class OrgVo {
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
}