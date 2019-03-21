package com.dayi.follow.vo.report;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

//周报
public class WeekBaseVo {
    private String startDate;//开始时间
    private String endDate;//结束时间

    private String startDateHMS;
    private String endDateHMS;

    private String thisWeekStart;//这周开始时间
    private String thisWeekEnd;//这周结束时间

    private String lastWeekStart;//上周开始时间
    private String lastWeekEnd;//上周结束时间

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

    public String getStartDateHMS() {
        return startDateHMS;
    }

    public void setStartDateHMS(String startDateHMS) {
        this.startDateHMS = startDateHMS;
    }

    public String getEndDateHMS() {
        return endDateHMS;
    }

    public void setEndDateHMS(String endDateHMS) {
        this.endDateHMS = endDateHMS;
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

    public WeekBaseVo(String betweenDate) {
        this.lastWeekStart = DateTime.now().plusWeeks(-1).withDayOfWeek(1).toString("yyyy-MM-dd");
        this.lastWeekEnd = DateTime.now().plusWeeks(-1).withDayOfWeek(5).toString("yyyy-MM-dd");
        this.thisWeekStart = DateTime.now().withDayOfWeek(1).toString("yyyy-MM-dd");
        this.thisWeekEnd = DateTime.now().withDayOfWeek(5).toString("yyyy-MM-dd");

        if (StringUtils.isBlank(betweenDate)) {//默认取上个星期
            this.startDate = lastWeekStart;
            this.endDate = DateTime.now().plusWeeks(-1).withDayOfWeek(5).millisOfDay().withMaximumValue().toString("yyyy-MM-dd");

            this.startDateHMS = DateTime.parse(startDate).toString("yyyy-MM-dd HH:mm:ss");
            this.endDateHMS = DateTime.now().plusWeeks(-1).withDayOfWeek(5).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        } else {
            String[] split = betweenDate.split(" - ");
            this.startDate = split[0];
            this.startDateHMS = DateTime.parse(split[0]).toString("yyyy-MM-dd HH:mm:ss");

            this.endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd");
            this.endDateHMS = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }
    }
}