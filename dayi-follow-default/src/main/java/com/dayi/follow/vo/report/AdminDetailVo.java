package com.dayi.follow.vo.report;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminDetailVo {

    List<ReportVo> pList = new ArrayList<>();

    private String deptId;

    private String deptName;
    private Date date;

    private Integer openAccountNum;//新开户数
    private Integer orgNum;//新签创客

    private BigDecimal inCash;//入金总额
    private String inCashFm;//入金总额（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal outCash;//提现转出总额
    private String outCashFm;//提现转出总额（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal manageFund;//管理资产规模
    private String manageFundFm;//管理资产规模（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal manageGrowthFund;//资产规模净增
    private String manageGrowthFundFm;//资产规模净增（格式化：显示前两位和小数点位，其余用*标识）

    private BigDecimal makerFund;//创客管理资产规模
    private String makerFundFm;//创客管理资产规模

    public List<ReportVo> getpList() {
        return pList;
    }

    public void setpList(List<ReportVo> pList) {
        this.pList = pList;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getOpenAccountNum() {
        return openAccountNum;
    }

    public void setOpenAccountNum(Integer openAccountNum) {
        this.openAccountNum = openAccountNum;
    }

    public Integer getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(Integer orgNum) {
        this.orgNum = orgNum;
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

    public BigDecimal getOutCash() {
        return outCash;
    }

    public void setOutCash(BigDecimal outCash) {
        this.outCash = outCash;
    }

    public String getOutCashFm() {
        return outCashFm;
    }

    public void setOutCashFm(String outCashFm) {
        this.outCashFm = outCashFm;
    }

    public BigDecimal getManageFund() {
        return manageFund;
    }

    public void setManageFund(BigDecimal manageFund) {
        this.manageFund = manageFund;
    }

    public String getManageFundFm() {
        return manageFundFm;
    }

    public void setManageFundFm(String manageFundFm) {
        this.manageFundFm = manageFundFm;
    }

    public BigDecimal getManageGrowthFund() {
        return manageGrowthFund;
    }

    public void setManageGrowthFund(BigDecimal manageGrowthFund) {
        this.manageGrowthFund = manageGrowthFund;
    }

    public String getManageGrowthFundFm() {
        return manageGrowthFundFm;
    }

    public void setManageGrowthFundFm(String manageGrowthFundFm) {
        this.manageGrowthFundFm = manageGrowthFundFm;
    }

    public BigDecimal getMakerFund() {
        return makerFund;
    }

    public void setMakerFund(BigDecimal makerFund) {
        this.makerFund = makerFund;
    }

    public String getMakerFundFm() {
        return makerFundFm;
    }

    public void setMakerFundFm(String makerFundFm) {
        this.makerFundFm = makerFundFm;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}