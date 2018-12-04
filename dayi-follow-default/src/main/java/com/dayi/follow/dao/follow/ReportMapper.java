package com.dayi.follow.dao.follow;

import com.dayi.follow.vo.DailyVo;
import com.dayi.follow.vo.index.IndexVo;
import com.dayi.follow.vo.report.ReportDailyVo;
import com.dayi.mybatis.support.BaseMapper;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface ReportMapper extends BaseMapper<IndexVo> {

    List<ReportDailyVo> findDaily(List<String> followIds, String startDate, String endDate, Integer limitStart, Integer limitEnd);
    long findDailyCount(List<String>  followIds, String startDate, String endDate);

    List<ReportDailyVo> findTeamDaily(String deptId, String startDate, String endDate, Integer limitStart, Integer limitEnd);
    long findTeamDailyCount(String deptId, String startDate, String endDate);


    ReportDailyVo getWeek(String followId, String startDate, String endDate);

    List<ReportDailyVo> findTeamWeek(String deptId, String startDate, String endDate, Integer limitStart, Integer limitEnd);
}
