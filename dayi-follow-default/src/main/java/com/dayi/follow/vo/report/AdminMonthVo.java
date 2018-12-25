package com.dayi.follow.vo.report;


import java.math.BigDecimal;
import java.util.List;

//管理员月报
public class AdminMonthVo {
    private String month;//月份
    private String thisMonth;//这月

    private String lastMonth;//上月

    private List<MonthVo> monthVos;

    public List<MonthVo> getMonthVos() {
        return monthVos;
    }

    public void setMonthVos(List<MonthVo> monthVos) {
        this.monthVos = monthVos;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(String thisMonth) {
        this.thisMonth = thisMonth;
    }

    public String getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(String lastMonth) {
        this.lastMonth = lastMonth;
    }
}