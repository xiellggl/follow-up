package com.dayi.follow.service.impl;

import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.ReportService;
import com.dayi.follow.vo.DailyVo;
import com.dayi.follow.vo.report.ReportDailyVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author xiell
 * @date 2018/11/16
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    FollowUpMapper followUpMapper;
    @Resource
    ReportMapper reportMapper;
    @Resource
    DeptService deptService;
    @Resource
    CountService countService;


    @Override
    public Page findDaily(Page page, String followId, String betweenDate) {
        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = StringUtils.split(betweenDate, ",");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        List<ReportDailyVo> dailyList = reportMapper.findDaily(followIds, startDate, endDate, page.getStartRow(), page.getPageSize());

        long dailyCount = reportMapper.findDailyCount(followIds, startDate, endDate);
        page.setResults(dailyList);
        page.setTotalRecord(dailyCount);
        return page;
    }

    @Override
    public Page findTeamDaily(Page page, String deptId, String betweenDate) {

        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = StringUtils.split(betweenDate, ",");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        List<ReportDailyVo> dailyList = reportMapper.findTeamDaily(deptId, startDate, endDate, page.getStartRow(), page.getPageSize());

        long dailyCount = reportMapper.findTeamDailyCount(deptId, startDate, endDate);
        page.setResults(dailyList);
        page.setTotalRecord(dailyCount);
        return page;
    }

    @Override
    public Page findTeamDailyDetail(Page page, String deptId) {

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        List<ReportDailyVo> daily = reportMapper.findDaily(followIds, null, null, page.getStartRow(), page.getPageSize());
        long dailyCount = reportMapper.findDailyCount(followIds, null, null);
        page.setResults(daily);
        page.setTotalRecord(dailyCount);
        return page;

    }

    @Override
    public List<ReportDailyVo> exportTeamDailyDetail(String deptId) {

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        List<ReportDailyVo> dailyList = reportMapper.findDaily(followIds, null, null, null, null);
        return dailyList;

    }

    //个人周报
    @Override
    public ReportDailyVo getWeek(String followId, String betweenDate) {
        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = StringUtils.split(betweenDate, ",");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }
        return reportMapper.getWeek(followId, startDate, endDate);
    }

    //团队周报
    @Override
    public Page findTeamWeek(Page page, String deptId ,String betweenDate) {



        return null;
    }
}