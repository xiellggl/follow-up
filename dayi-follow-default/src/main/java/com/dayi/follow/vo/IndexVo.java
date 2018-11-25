package com.dayi.follow.vo;


import com.dayi.follow.util.Misc;

import java.math.BigDecimal;

public class IndexVo {
    //ka创客数据
    private Integer kaOrgNum;//KA创客人数
    private Integer kaOrgValidAgentNum;//KA创客有效代理商总数
    private BigDecimal kaOrgManageFund;//KA创客管理资金规模

    //今日待联系代理商
    private Integer waitLinkNum;//今日待联系代理商

    //日报数据
    private String date;//日报日期
    private Integer openNum;//新开户数
    private BigDecimal inCash;//入金总额
    private String inCashFm;//入金总额（格式化：显示前两位和小数点位，其余用*标识）
    private Integer inCashNum;//入金人数
    private BigDecimal applyOutCash;//提现转出总额
    private String applyOutCashFm;//提现转出总额（格式化：显示前两位和小数点位，其余用*标识）
    private Integer outCashNum;//出金人数

    private Integer deptId;//部门（团队）ID
    private String deptName;//部门名称

    public Integer getKaOrgNum() {
        return kaOrgNum;
    }

    public void setKaOrgNum(Integer kaOrgNum) {
        this.kaOrgNum = kaOrgNum;
    }

    public Integer getKaOrgValidAgentNum() {
        return kaOrgValidAgentNum;
    }

    public void setKaOrgValidAgentNum(Integer kaOrgValidAgentNum) {
        this.kaOrgValidAgentNum = kaOrgValidAgentNum;
    }

    public BigDecimal getKaOrgManageFund() {
        return kaOrgManageFund;
    }

    public void setKaOrgManageFund(BigDecimal kaOrgManageFund) {
        this.kaOrgManageFund = kaOrgManageFund;
    }

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

    public Integer getOpenNum() {
        return openNum;
    }

    public void setOpenNum(Integer openNum) {
        this.openNum = openNum;
    }

    public BigDecimal getInCash() {
        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public String getInCashFm() {
        return inCashFm;
    }

    public void setInCashFm(String inCashFm) {
        this.inCashFm = inCashFm;
    }

    public Integer getInCashNum() {
        return inCashNum;
    }

    public void setInCashNum(Integer inCashNum) {
        this.inCashNum = inCashNum;
    }

    public BigDecimal getApplyOutCash() {
        return applyOutCash;
    }

    public void setApplyOutCash(BigDecimal applyOutCash) {
        this.applyOutCash = applyOutCash;
    }

    public String getApplyOutCashFm() {
        return applyOutCashFm;
    }

    public void setApplyOutCashFm(String applyOutCashFm) {
        this.applyOutCashFm = applyOutCashFm;
    }

    public Integer getOutCashNum() {
        return outCashNum;
    }

    public void setOutCashNum(Integer outCashNum) {
        this.outCashNum = outCashNum;
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