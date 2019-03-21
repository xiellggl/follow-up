package com.dayi.follow.vo.report;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

//周报
public class MonthBaseVo {
    private String month;//月份
    private String thisMonth;//这月
    private String lastMonth;//上月

    private String monthStartHMS;//月份第一天
    private String monthEndHMS;//月份最后一天

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

    public MonthBaseVo(String month) {
        lastMonth = DateTime.now().plusMonths(-1).toString("yyyy-MM");//上月
        thisMonth = DateTime.now().toString("yyyy-MM");//本月

        if (StringUtils.isBlank(month)) {//默认取上个月
            this.month = lastMonth;
        } else {
            this.month = month;
        }

        monthStartHMS = DateTime.parse(this.month).toString("yyyy-MM-dd HH:mm:ss");//本月开始
        monthEndHMS = DateTime.parse(this.month).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束
    }

    public String getMonthStartHMS() {
        return monthStartHMS;
    }

    public void setMonthStartHMS(String monthStartHMS) {
        this.monthStartHMS = monthStartHMS;
    }

    public String getMonthEndHMS() {
        return monthEndHMS;
    }

    public void setMonthEndHMS(String monthEndHMS) {
        this.monthEndHMS = monthEndHMS;
    }
}