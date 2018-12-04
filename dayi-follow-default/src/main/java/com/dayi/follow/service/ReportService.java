package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */


import com.dayi.follow.vo.report.ReportDailyVo;
import com.dayi.mybatis.support.Page;

import java.util.List;

/**
 * 部门 业务接口类
 */
public interface ReportService {

    Page findDaily(Page page, String followId, String betweenDate);

    Page findTeamDaily(Page page, String deptId, String betweenDate);

    Page findTeamDailyDetail(Page page, String deptId);

    List<ReportDailyVo> exportTeamDailyDetail(String deptId);

    ReportDailyVo getWeek(String followId, String betweenDate);

    Page findTeamWeek(Page page, String deptId, String betweenDate);


}
