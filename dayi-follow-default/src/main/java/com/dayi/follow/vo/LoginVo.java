package com.dayi.follow.vo;

import org.hibernate.validator.constraints.NotBlank;

public class LoginVo {
    @NotBlank(message = "手机号码不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String captcha;
    //手机app登录 手机设备唯一标识（判断是否是不同手机登录）
    private String deviceId;
    //手机app登录的 TOKEN
    private String token;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //--------------------------------------------------------
    private String id;
    private String name;
    private String mobile;
    private String cookie;
    private Integer disable;
    private String deptId;     // 所属部门ID
    private String deptName;     // 所属部门名称
    private Integer isManager;  // 是否负责人：0--否；1--是
    private Integer isAdmin;    // 是否管理员：0--否；1--是
    private String chargeDeptId;//负责部门ID
    private String date;//日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getChargeDeptId() {
        return chargeDeptId;
    }

    public void setChargeDeptId(String chargeDeptId) {
        this.chargeDeptId = chargeDeptId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}