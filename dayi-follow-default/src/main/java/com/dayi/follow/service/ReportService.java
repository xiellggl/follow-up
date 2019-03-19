package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */


import com.dayi.follow.model.follow.SourceReport;
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
    List<ReportDailyVo> exportTeamDailyDetail(String deptId, String date);

    //个人周报
    MyWeekVo getWeek(String followId, String betweenDate);

    //团队周报
    TeamWeekVo countTeamWeek(String deptId, String betweenDate);

    //个人月报
    MyMonthVo getMonth(String followId, String month);

    //团队月报
    TeamMonthVo countTeamMonth(String deptId, String month);

    //管理员日报
    List findAdminDaily(String betweenDate);

    //管理员日报详情
    List findAdminDailyDetail(String date);

    //管理员日报详情列表
    List<ReportDailyVo> findAdminDailyDetailList(String deptId, String date);

    //管理员周报
    AdminWeekVo countAdminWeek(Page page, String betweenDate);

    //管理员周报导出
    List<WeekVo> findAdminWeekList(String betweenDate);

    //管理员月报
    AdminMonthVo countAdminMonth(Page page, String month);

    //管理员月报导出
    List<MonthVo> findAdminMonthList(String deptId, String month);
}
