package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */


import com.dayi.follow.vo.report.*;
import com.dayi.mybatis.support.Page;

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
    Page findTeamDailyDetail(Page page, String deptId, String date);

    //导出团队日报详情
    List<ReportVo> exportTeamDailyDetail(String deptId, String date);

    //个人周报
    WeekVo getWeek(String followId, String betweenDate);

    //团队周报
    WeekVo getTeamWeek(String deptId, String betweenDate);

    //个人月报
    MonthVo getMonth(String followId, String month);

    //团队月报
    MonthVo getTeamMonth(String deptId, String month);


    //管理员日报
    List findAdminDaily(String date);

    //管理员日报详情
    List<AdminDetailVo> findAdminDailyDetail(String date);

    //管理员日报详情列表
    List<ReportVo> findAdminDailyDetailList(String date);


    //管理员周报
    WeekVo findAdminWeek(String betweenDate);

    //管理员周报
    List<AdminDetailVo> findAdminWeekDetail(String betweenDate);

    //管理员周报导出
    List<ReportVo> findAdminWeekDetailList(String betweenDate);


    //管理员月报
    MonthVo findAdminMonth(String month);

    //管理员月报
    List<AdminDetailVo> findAdminMonthDetail(String month);

    //管理员月报导出
    List<ReportVo> findAdminMonthDetailList(String month);
}
