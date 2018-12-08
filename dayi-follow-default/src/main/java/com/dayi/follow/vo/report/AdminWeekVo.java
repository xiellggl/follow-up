package com.dayi.follow.vo.report;


import java.math.BigDecimal;
//管理员周报
public class AdminWeekVo {
    private String deptName;//部门名称

    private String name;//姓名

    private Integer monOpen;  // 周一有效新开户
    private BigDecimal monInCash;  // 周一入金代理

    private Integer tueOpen;  // 周二有效新开户
    private BigDecimal tueInCash;  // 周二入金代理

    private Integer wedOpen;  // 周三有效新开户
    private BigDecimal wedInCash;  // 周三入金代理

    private Integer thuOpen;  // 周四有效新开户
    private BigDecimal thuInCash;  // 周四入金代理

    private Integer friOpen;  // 周五有效新开户
    private BigDecimal friInCash;  // 周五入金代理

    private Integer openNum;  // 周有效新开户

    private BigDecimal inCash;//入金总额

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

    public Integer getMonOpen() {
        return monOpen;
    }

    public void setMonOpen(Integer monOpen) {
        this.monOpen = monOpen;
    }

    public BigDecimal getMonInCash() {
        return monInCash;
    }

    public void setMonInCash(BigDecimal monInCash) {
        this.monInCash = monInCash;
    }

    public Integer getTueOpen() {
        return tueOpen;
    }

    public void setTueOpen(Integer tueOpen) {
        this.tueOpen = tueOpen;
    }

    public BigDecimal getTueInCash() {
        return tueInCash;
    }

    public void setTueInCash(BigDecimal tueInCash) {
        this.tueInCash = tueInCash;
    }

    public Integer getWedOpen() {
        return wedOpen;
    }

    public void setWedOpen(Integer wedOpen) {
        this.wedOpen = wedOpen;
    }

    public BigDecimal getWedInCash() {
        return wedInCash;
    }

    public void setWedInCash(BigDecimal wedInCash) {
        this.wedInCash = wedInCash;
    }

    public Integer getThuOpen() {
        return thuOpen;
    }

    public void setThuOpen(Integer thuOpen) {
        this.thuOpen = thuOpen;
    }

    public BigDecimal getThuInCash() {
        return thuInCash;
    }

    public void setThuInCash(BigDecimal thuInCash) {
        this.thuInCash = thuInCash;
    }

    public Integer getFriOpen() {
        return friOpen;
    }

    public void setFriOpen(Integer friOpen) {
        this.friOpen = friOpen;
    }

    public BigDecimal getFriInCash() {
        return friInCash;
    }

    public void setFriInCash(BigDecimal friInCash) {
        this.friInCash = friInCash;
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

}