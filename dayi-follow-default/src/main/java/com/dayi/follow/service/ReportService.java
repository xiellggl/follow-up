package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */


import com.dayi.follow.vo.report.AdminMonthVo;
import com.dayi.follow.vo.report.AdminWeekVo;
import com.dayi.follow.vo.report.ReportDailyVo;
import com.dayi.mybatis.support.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 部门 业务接口类
 */
public interface ReportService {
    //个人日报
    Page findDaily(Page page, String followId, String betweenDate);

    //团队日报
    Page findTeamDaily(Page page, String deptId, String betweenDate);

    //团队日报详情
    Page findTeamDailyDetail(Page page, String deptId,String date);

    //导出团队日报详情
    List<ReportDailyVo> exportTeamDailyDetail(String deptId,String date);

    //个人周报
    ReportDailyVo getWeek(String followId, String betweenDate);

    //团队周报
    List<ReportDailyVo> findTeamWeek(String deptId, String betweenDate);

    //个人月报
    ReportDailyVo getMonth(String followId, String month);

    //团队月报
    List<ReportDailyVo> findTeamMonth(String deptId, String month);

    //管理员日报
    Page<ReportDailyVo> findAdminDaily(Page page, String deptId, String deptName, String betweenDate);

    //管理员日报详情
    Page findAdminDailyDetail(Page page, String deptId, String date);

    //管理员日报详情列表
    List<ReportDailyVo>findAdminDailyDetailList(String deptId, String date);

    //管理员周报
    Page<AdminWeekVo> findAdminWeek(Page page, String deptId, String betweenDate);

    //管理员周报导出
    List<AdminWeekVo> findAdminWeekList(String deptId,  String betweenDate);

    //管理员月报
    Page<AdminMonthVo> findAdminMonth(Page page, String deptId, String month);

    //管理员月报导出
    List<AdminMonthVo> findAdminMonthList(String deptId, String month);
}
