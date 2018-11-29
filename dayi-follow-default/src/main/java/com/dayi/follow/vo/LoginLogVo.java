package com.dayi.follow.vo;

import java.util.Date;

public class LoginLogVo {
    private Date createDate;
    private String loginDateFm;

    private Integer loginType;
    private String loginTypeStr;

    private String loginIp;
    private String address;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginDateFm() {
        return loginDateFm;
    }

    public void setLoginDateFm(String loginDateFm) {
        this.loginDateFm = loginDateFm;
    }

    public String getLoginTypeStr() {
        return loginTypeStr;
    }

    public void setLoginTypeStr(String loginTypeStr) {
        this.loginTypeStr = loginTypeStr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}