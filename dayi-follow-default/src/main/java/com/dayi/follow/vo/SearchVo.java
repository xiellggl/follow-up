package com.dayi.follow.vo;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class SearchVo {
    private Integer bankSign; //银行是否已签约（0=未签约，1=已签约）
    private Integer idCardValidate; //是否实名
    private Integer inCash; //是否入金
    private String mobile; //手机号码
    private String inviteCode;// //邀请码
    private List<String> bankType;//银行类型，1-中信银行，4-广发银行，5-华夏银行

    private String bankTypeStr;//银行类型字符串

    private String followUp;// 跟进人
    private Integer todayInCash; //今日入金状态
    private Integer waitToLinkToday; //是否查询今日待联系
    private Integer todayOutCash; //今日出金状态
    private Integer customerType; //客户类型
    private Integer totalFound; //资产总规模
    private Integer makerNum; //创客号

    public String getBankTypeStr() {
        return bankTypeStr;
    }

    public void setBankTypeStr(String bankTypeStr) {
        this.bankTypeStr = bankTypeStr;
    }

    public Integer getBankSign() {
        return bankSign;
    }

    public void setBankSign(Integer bankSign) {
        this.bankSign = bankSign;
    }

    public Integer getIdCardValidate() {
        return idCardValidate;
    }

    public void setIdCardValidate(Integer idCardValidate) {
        this.idCardValidate = idCardValidate;
    }

    public Integer getInCash() {
        return inCash;
    }

    public void setInCash(Integer inCash) {
        this.inCash = inCash;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public List<String> getBankType() {
        return bankType;
    }

    public void setBankType(List<String> bankType) {
        this.bankType = bankType;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public Integer getTodayInCash() {
        return todayInCash;
    }

    public void setTodayInCash(Integer todayInCash) {
        this.todayInCash = todayInCash;
    }

    public Integer getWaitToLinkToday() {
        return waitToLinkToday;
    }

    public void setWaitToLinkToday(Integer waitToLinkToday) {
        this.waitToLinkToday = waitToLinkToday;
    }

    public Integer getTodayOutCash() {
        return todayOutCash;
    }

    public void setTodayOutCash(Integer todayOutCash) {
        this.todayOutCash = todayOutCash;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getTotalFound() {
        return totalFound;
    }

    public void setTotalFound(Integer totalFound) {
        this.totalFound = totalFound;
    }

    public Integer getMakerNum() {
        return makerNum;
    }

    public void setMakerNum(Integer makerNum) {
        this.makerNum = makerNum;
    }
}