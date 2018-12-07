package com.dayi.follow.vo.index;


import java.math.BigDecimal;

public class DailyVo {
    //日报数据
    private Integer openNum;//新开户数
    private BigDecimal inCash;//入金总额
    private String inCashFm;//入金总额（格式化：显示前两位和小数点位，其余用*标识）
    private Integer inCashNum;//入金人数
    private BigDecimal outCash;//提现转出总额
    private String outCashFm;//提现转出总额（格式化：显示前两位和小数点位，其余用*标识）
    private Integer outCashNum;//出金人数

    private String date;//日报日期
    private Integer deptId;//部门（团队）ID
    private String deptName;//部门名称

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