package com.dayi.follow.vo;

import com.dayi.follow.enums.LoginTypeEnum;
import com.dayi.follow.util.IPUtil;
import org.apache.commons.lang3.StringUtils;

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
        if (loginType != null) {
            loginTypeStr = LoginTypeEnum.getCnameByKey(loginType);
        }
        return loginTypeStr;
    }

    public void setLoginTypeStr(String loginTypeStr) {
        this.loginTypeStr = loginTypeStr;
    }

    public String getAddress() {
        if (loginIp != null) {
            address = IPUtil.getAddressByIp(loginIp);
            if (!StringUtils.isBlank(address)) {
                address = address.substring(0, address.length() - 2);//去掉运营商名字
            }
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}