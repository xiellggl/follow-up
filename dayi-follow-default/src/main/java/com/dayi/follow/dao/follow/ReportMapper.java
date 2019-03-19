package com.dayi.follow.dao.follow;

import com.dayi.follow.base.BaseVo;
import com.dayi.follow.vo.report.*;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface ReportMapper extends BaseMapper<BaseVo> {

    Page<ReportDailyVo> findDaily(Page page, @Param("followIds") List<String> followIds, @Param("startDate") String startDate, @Param("endDate") String endDate);

    Page<ReportDailyVo> findTeamDaily(Page page, @Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    Page<ReportDailyVo> findTeamDailyDetail(Page page, @Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ReportDailyVo> findTeamDailyDetail(@Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    MyWeekVo getWeek(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ReportVo> findTeamWeek(@Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    MyMonthVo getMonth(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ReportVo> findTeamMonth(@Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //获取指定时间内的报表统计
    ReportVo getReport(@Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员日报详情
    Page<ReportDailyVo> findAdminDailyDetail(Page page, @Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //查找管理员日报详情列表
    List<ReportDailyVo> findAdminDailyDetail(@Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员周报
    Page<WeekVo> findAdminWeekSum(Page page, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员周报
    List<WeekVo> findAdminWeekSum(@Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员周报
    Page<WeekVo> findAdminWeekPer(Page page, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员月报
    Page<MonthVo> findAdminMonth(Page page, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员月报
    List<MonthVo> findAdminMonth(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
