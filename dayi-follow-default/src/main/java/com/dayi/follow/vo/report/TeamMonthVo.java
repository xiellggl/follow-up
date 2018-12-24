package com.dayi.follow.vo.report;


import java.util.List;

//周报
public class TeamMonthVo {
    private List<ReportVo>reportVos;

    private String month;//月份
    private String thisMonth;//这月

    private String lastMonth;//上月

    public List<ReportVo> getReportVos() {
        return reportVos;
    }

    public void setReportVos(List<ReportVo> reportVos) {
        this.reportVos = reportVos;
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