package com.dayi.follow.vo.report;


//报表的日期vo
public class DateVo {

    //周报

    private String startDate;//开始时间
    private String endDate;//结束时间

    private String thisWeekStart;//这周开始时间
    private String thisWeekEnd;//这周结束时间

    private String lastWeekStart;//上周开始时间
    private String lastWeekEnd;//上周结束时间

    //月报

    private String month;//月份
    private String thisMonth;//这月

    private String lastMonth;//上月


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getThisWeekStart() {
        return thisWeekStart;
    }

    public void setThisWeekStart(String thisWeekStart) {
        this.thisWeekStart = thisWeekStart;
    }

    public String getThisWeekEnd() {
        return thisWeekEnd;
    }

    public void setThisWeekEnd(String thisWeekEnd) {
        this.thisWeekEnd = thisWeekEnd;
    }

    public String getLastWeekStart() {
        return lastWeekStart;
    }

    public void setLastWeekStart(String lastWeekStart) {
        this.lastWeekStart = lastWeekStart;
    }

    public String getLastWeekEnd() {
        return lastWeekEnd;
    }

    public void setLastWeekEnd(String lastWeekEnd) {
        this.lastWeekEnd = lastWeekEnd;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}