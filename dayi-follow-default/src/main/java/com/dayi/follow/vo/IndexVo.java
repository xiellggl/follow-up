package com.dayi.follow.vo;


import com.dayi.follow.util.Misc;

import java.math.BigDecimal;

public class IndexVo {
    //ka创客数据


    //今日待联系代理商
    private Integer waitLinkNum;//今日待联系代理商


    private String date;//日期
    private Integer deptId;//部门（团队）ID
    private String deptName;//部门名称


    public Integer getWaitLinkNum() {
        return waitLinkNum;
    }

    public void setWaitLinkNum(Integer waitLinkNum) {
        this.waitLinkNum = waitLinkNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}