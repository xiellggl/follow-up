package com.dayi.follow.vo.report;


import com.dayi.follow.util.Misc;

import java.math.BigDecimal;
//日报数据,周报，月报都有用到
public class ReportDailyVo {

    private Integer openAccountNum;//新开户数

    private BigDecimal inCash;//入金总额
    private String inCashFm;//入金总额（格式化：显示前两位和小数点位，其余用*标识）

    private Integer inCashNum;//入金人数

    private BigDecimal outCash;//提现转出总额
    private String outCashFm;//提现转出总额（格式化：显示前两位和小数点位，其余用*标识）

    private Integer outCashNum;//出金人数

    private String date;//日报日期
    private String deptName;//部门名称

    private String deptId;//部门id

    private String name;//姓名

    private BigDecimal manageFund;//管理资金规模
    private String manageFundFm;//管理资金规模

    private BigDecimal manageGrowthFund;//管理资金规模净增
    private String manageGrowthFundFm;//管理资金规模净增

    private BigDecimal makerFund;//创客管理资金规模
    private String makerFundFm;//创客管理资金规模

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Integer getOpenAccountNum() {
        return openAccountNum;
    }

    public void setOpenAccountNum(Integer openAccountNum) {
        this.openAccountNum = openAccountNum;
    }

    public BigDecimal getInCash() {
        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public String getInCashFm() {
        if (this.inCash != null) {
            inCashFm = Misc.parseMoney(inCash.doubleValue(), 2);
        }else {
            inCashFm="0.00";
        }
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

    public BigDecimal getManageFund() {
        return manageFund;
    }

    public void setManageFund(BigDecimal manageFund) {
        this.manageFund = manageFund;
    }

    public String getManageFundFm() {
        if (this.manageFund != null) {
            manageFundFm = Misc.parseMoney(manageFund.doubleValue(), 2);
        }else {
            manageFundFm="0.00";
        }
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
        if (this.manageGrowthFund != null) {
            manageGrowthFundFm = Misc.parseMoney(manageGrowthFund.doubleValue(), 2);
        }else {
            manageGrowthFundFm="0.00";
        }
        return manageGrowthFundFm;
    }

    public void setManageGrowthFundFm(String manageGrowthFundFm) {
        this.manageGrowthFundFm = manageGrowthFundFm;
    }

    public BigDecimal getOutCash() {
        return outCash;
    }

    public void setOutCash(BigDecimal outCash) {
        this.outCash = outCash;
    }

    public String getOutCashFm() {
        if (this.outCash != null) {
            outCashFm = Misc.parseMoney(outCash.doubleValue(), 2);
        }else {
            outCashFm="0.00";
        }
        return outCashFm;
    }

    public void setOutCashFm(String outCashFm) {
        this.outCashFm = outCashFm;
    }

    public Integer getOutCashNum() {
        return outCashNum;
    }

    public void setOutCashNum(Integer outCashNum) {
        this.outCashNum = outCashNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMakerFund() {
        return makerFund;
    }

    public void setMakerFund(BigDecimal makerFund) {
        this.makerFund = makerFund;
    }

    public String getMakerFundFm() {
        if (this.makerFund != null) {
            makerFundFm = Misc.parseMoney(makerFund.doubleValue(), 2);
        }else {
            makerFundFm="0.00";
        }
        return makerFundFm;
    }

    public void setMakerFundFm(String makerFundFm) {
        this.makerFundFm = makerFundFm;
    }


}