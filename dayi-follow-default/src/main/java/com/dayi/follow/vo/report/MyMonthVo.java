package com.dayi.follow.vo.report;


import java.math.BigDecimal;

//周报
public class MyMonthVo extends ReportVo {

    private String month;//月份
    private String thisMonth;//这月

    private String lastMonth;//上月

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