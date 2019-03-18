package com.dayi.follow.vo.followup;

import com.dayi.follow.enums.MemberStatusEnum;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.util.Misc;

import java.math.BigDecimal;
import java.util.Date;

//跟进人列表
public class FollowUpListVo {
    private String id;

    private String name;  // 姓名
    private String userName;    // 账号
    private String inviteCode;  // 邀请码
    private String deptName;//部门名字

    private Integer disable;//状态-1开启0-禁用
    private String status;//状态对应字符串

    private int agentNum;//代理商数量

    private int orgNum;//创客数量

    private BigDecimal agentFund;//代理商资金
    private String agentFundFm;//格式化代理资金

    private BigDecimal orgFund;//创客资金
    private String orgFundFm;//格式化创客资金

    private BigDecimal hisMaxFund;//历史最高资金规模
    private String hisMaxFundFm;//历史最高资金规模

    private Date createTime;//创建时间
    private String createTimeFm;

    //加于客户分配的跟进人列表
    private String mobile;//手机
    private int cusNum;//客户名字

    public String getAgentFundFm() {
        if (agentFund != BigDecimal.ZERO && agentFund != null) {
            agentFundFm = Misc.parseMoney(agentFund.doubleValue(), 2);
        } else {
            agentFundFm = "0.00";
        }
        return agentFundFm;
    }

    public void setAgentFundFm(String agentFundFm) {
        this.agentFundFm = agentFundFm;
    }

    public String getOrgFundFm() {
        if (this.orgFund != BigDecimal.ZERO && orgFund != null) {
            orgFundFm = Misc.parseMoney(orgFund.doubleValue(), 2);
        } else {
            orgFundFm = "0.00";
        }
        return orgFundFm;
    }

    public void setOrgFundFm(String orgFundFm) {
        this.orgFundFm = orgFundFm;
    }

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
        if (this.getDisable() != null) {
            if (this.getDisable().intValue() == MemberStatusEnum.ENABLE.getValue()) {
                return MemberStatusEnum.ENABLE.getName();
            } else {
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

    public BigDecimal getAgentFund() {
        return agentFund;
    }

    public void setAgentFund(BigDecimal agentFund) {
        this.agentFund = agentFund;
    }

    public BigDecimal getOrgFund() {
        return orgFund;
    }

    public void setOrgFund(BigDecimal orgFund) {
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

    public BigDecimal getHisMaxFund() {
        return hisMaxFund;
    }

    public void setHisMaxFund(BigDecimal hisMaxFund) {
        this.hisMaxFund = hisMaxFund;
    }

    public String getHisMaxFundFm() {
        if (hisMaxFund != BigDecimal.ZERO && hisMaxFund != null) {
            hisMaxFundFm = Misc.parseMoney(hisMaxFund.doubleValue(), 2);
        } else {
            hisMaxFundFm = "0.00";
        }
        return hisMaxFundFm;
    }

    public void setHisMaxFundFm(String hisMaxFundFm) {
        this.hisMaxFundFm = hisMaxFundFm;
    }
}