package com.dayi.follow.vo;

import com.dayi.follow.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class SearchVo {
    //加于我的客户-代理商
    private Integer idCardValidate; //是否实名

    private Integer bankSign; //银行是否已签约（0=未签约，1=已签约）

    private List<String> bankType;//银行类型，1-中信银行，4-广发银行，5-华夏银行
    private String bankTypeStr;//银行类型字符串

    private Integer totalFound; //资产总规模

    private Integer inCash; //是否入金

    private Integer todayInCash; //今日入金状态

    private Integer todayOutCash; //今日出金状态

    private Integer customerType; //客户类型

    private String mobile; //手机号码

    private String inviteCode;// //邀请码

    //加于团队客户代理商
    private String followUp;// 跟进人

    //加于首页
    private Integer waitToLinkToday; //是否查询今日待联系

    //加于代理商分配
    private String createDate;//注册时间
    private String createDateStart;//注册时间开始
    private String createDateEnd;//注册时间结束

    private String cusName;//客户名字
    private Integer assignStatus;//分配状态

    private String assignDate;//分配时间
    private String assignDateStart;//分配时间开始
    private String assignDateEnd;//分配时间结束

    private Integer agentId;//代理商id

    //加于跟进人管理的查看明细
    private String followUpBefore;//之前跟进人
    private String changeDate;//变更日期
    private String changeDateStart;//分配时间开始
    private String changeDateEnd;//分配时间结束

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    //加于创客分配
    private Integer orgType;//机构类型

    //加于公海列表
    private String warehouseDate;//入库时间
    private String warehouseDateStart;//注册时间开始
    private String warehouseDateEnd;//注册时间结束

    public String getWarehouseDate() {
        return warehouseDate;
    }

    public void setWarehouseDate(String warehouseDate) {
        this.warehouseDate = warehouseDate;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getFollowUpBefore() {
        return followUpBefore;
    }

    public void setFollowUpBefore(String followUpBefore) {
        this.followUpBefore = followUpBefore;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeDateStart() {
        return CommonUtils.getStart(changeDate);
    }

    public void setChangeDateStart(String changeDateStart) {
        this.changeDateStart = changeDateStart;
    }

    public String getChangeDateEnd() {
        return CommonUtils.getEnd(changeDate);
    }

    public String getWarehouseDateStart() {
        return CommonUtils.getStart(warehouseDate);
    }

    public void setWarehouseDateStart(String warehouseDateStart) {
        this.warehouseDateStart = warehouseDateStart;
    }

    public String getWarehouseDateEnd() {
        return CommonUtils.getEnd(warehouseDate);
    }

    public void setWarehouseDateEnd(String warehouseDateEnd) {
        this.warehouseDateEnd = warehouseDateEnd;
    }

    public void setChangeDateEnd(String changeDateEnd) {
        this.changeDateEnd = changeDateEnd;
    }

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Integer getAssignStatus() {
        return assignStatus;
    }

    public void setAssignStatus(Integer assignStatus) {
        this.assignStatus = assignStatus;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getCreateDateStart() {
        return CommonUtils.getStart(createDate);
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    public String getCreateDateEnd() {
        return CommonUtils.getEnd(createDate);
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public String getAssignDateStart() {
        return CommonUtils.getStart(assignDate);
    }

    public void setAssignDateStart(String assignDateStart) {
        this.assignDateStart = assignDateStart;
    }

    public String getAssignDateEnd() {
        return CommonUtils.getEnd(assignDate);
    }

    public void setAssignDateEnd(String assignDateEnd) {
        this.assignDateEnd = assignDateEnd;
    }
}