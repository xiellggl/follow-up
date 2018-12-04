package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */


import com.dayi.mybatis.support.Page;

/**
 * 部门 业务接口类
 */
public interface ReportService {

    Page findDailyPage(Page page, String followId, String betweenDate);

    Page findTeamDailyPage(Page page, String deptId, String betweenDate);


//    Page findDailyPage(Page page, String followId, String betweenDate);
//
//    Page findTeamDailyPage(Page page, String deptId, String betweenDate);


}
