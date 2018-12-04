package com.dayi.follow.dao.follow;

import com.dayi.follow.base.BaseVo;
import com.dayi.follow.vo.report.AdminMonthVo;
import com.dayi.follow.vo.report.ReportDailyVo;
import com.dayi.mybatis.support.BaseMapper;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface ReportMapper extends BaseMapper<BaseVo> {

    List<ReportDailyVo> findDaily(List<String> followIds, String startDate, String endDate, Integer limitStart, Integer limitEnd);

    long findDailyCount(List<String> followIds, String startDate, String endDate);

    List<ReportDailyVo> findTeamDaily(String deptId, String startDate, String endDate, Integer limitStart, Integer limitEnd);

    long findTeamDailyCount(String deptId, String startDate, String endDate);


    ReportDailyVo getWeek(String followId, String startDate, String endDate);

    List<ReportDailyVo> findTeamWeek(String deptId, String startDate, String endDate);

    ReportDailyVo getMonth(String followId, String startDate, String endDate);

    List<ReportDailyVo> findTeamMonth(String deptId, String startDate, String endDate);

    List<ReportDailyVo> findAdminDaily(List<String> followIds, String deptName, String startDate, String endDate, Integer limitStart, Integer limitEnd);

    int getAdminDailyNum(List<String> followIds, String deptName, String startDate, String endDate);

    //管理员周报
    List<ReportDailyVo> findAdminWeekSum(List<String> followIds, String startDate, String endDate, Integer limitStart, Integer limitEnd);

    //管理员周报
    int findAdminWeekCount(List<String> followIds, String startDate, String endDate);

    //管理员周报
    List<ReportDailyVo> findAdminWeekPer(List<String> followIds, String startDate, String endDate, Integer limitStart, Integer limitEnd);

    //管理员月报
    List<AdminMonthVo> findAdminMonth(List<String> followIds, String startDate, String endDate, Integer limitStart, Integer limitEnd);

    //得到月最后一天的管理资金
    double getLastManageFund(String followId, String startDate, String endDate);
}
