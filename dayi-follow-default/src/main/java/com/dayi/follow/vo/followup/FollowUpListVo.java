package com.dayi.follow.vo.followup;

import com.dayi.follow.enums.MemberStatusEnum;
import com.dayi.follow.model.follow.Department;

import java.util.Date;
//跟进人列表
public class FollowUpListVo {
    private String id;

    private String name;  // 姓名
    private String userName;    // 账号
    private String inviteCode;  // 邀请码
    private String deptName;

    private Integer disable;//状态
    private String status;

    private int agentNum;

    private int orgNum;

    private double agentFund;

    private double orgFund;

    private Date createTime;
    private String createTimeFm;

    //加于客户分配的跟进人列表
    private String mobile;
    private int cusNum;

    public int getCusNum() {
        return cusNum;
    }

    public void setCusNum(int cusNum) {
        this.cusNum = cusNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public String getStatus() {
        if (this.getDisable()!=null){
            if(this.getDisable().intValue()== MemberStatusEnum.ENABLE.getValue()){
                return MemberStatusEnum.ENABLE.getName();
            }else {
                return MemberStatusEnum.DISABLE.getName();
            }
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(int agentNum) {
        this.agentNum = agentNum;
    }

    public int getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(int orgNum) {
        this.orgNum = orgNum;
    }

    public double getAgentFund() {
        return agentFund;
    }

    public void setAgentFund(double agentFund) {
        this.agentFund = agentFund;
    }

    public double getOrgFund() {
        return orgFund;
    }

    public void setOrgFund(double orgFund) {
        this.orgFund = orgFund;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeFm() {
        return createTimeFm;
    }

    public void setCreateTimeFm(String createTimeFm) {
        this.createTimeFm = createTimeFm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}