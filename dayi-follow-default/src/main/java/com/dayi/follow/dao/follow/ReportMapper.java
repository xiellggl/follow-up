package com.dayi.follow.dao.follow;

import com.dayi.follow.base.BaseVo;
import com.dayi.follow.vo.report.AdminMonthVo;
import com.dayi.follow.vo.report.ReportDailyVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface ReportMapper extends BaseMapper<BaseVo> {

    Page<ReportDailyVo> findDaily(Page page, @Param("followIds") List<String> followIds, @Param("startDate") String startDate, @Param("endDate") String endDate);

    Page<ReportDailyVo> findTeamDaily(Page page, @Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    Page<ReportDailyVo> findTeamDailyDetail(Page page, @Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ReportDailyVo> findTeamDailyDetailList(@Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    ReportDailyVo getWeek(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ReportDailyVo> findTeamWeek(@Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    ReportDailyVo getMonth(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ReportDailyVo> findTeamMonth(@Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员日报
    Page<ReportDailyVo> findAdminDaily(Page page, @Param("followIds") List<String> followIds, @Param("deptName") String deptName, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员日报详情
    Page<ReportDailyVo> findAdminDailyDetail(Page page, @Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员周报
    Page<ReportDailyVo> findAdminWeekSum(Page page, @Param("followIds") List<String> followIds, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员周报
    Page<ReportDailyVo> findAdminWeekPer(Page page, @Param("followIds") List<String> followIds, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员月报
    Page<AdminMonthVo> findAdminMonth(Page page, @Param("followIds") List<String> followIds, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //得到月最后一天的管理资金
    double getLastManageFund(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
