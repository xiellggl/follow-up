package com.dayi.follow.service.impl;

import com.dayi.common.util.BigDecimals;
import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.dao.follow.*;
import com.dayi.follow.model.follow.SourceReport;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.ReportService;
import com.dayi.follow.vo.report.*;
import com.dayi.mybatis.support.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    FollowOrgMapper followOrgMapper;
    @Resource
    FollowAgentMapper followAgentMapper;
    @Resource
    FollowUpLogMapper followUpLogMapper;
    @Resource
    OrgMapper orgMapper;
    @Resource
    SourceReportMapper sourceReportMapper;
    @Resource
    CountService countService;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;


    @Override
    public Page findDaily(Page page, String followId, String betweenDate) {
        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = betweenDate.split(" - ");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        page = reportMapper.findDaily(page, followIds, startDate, endDate);

        return page;
    }

    @Override
    public Page findTeamDaily(Page page, String deptId, String betweenDate) {

        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = betweenDate.split(" - ");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        page = reportMapper.findTeamDaily(page, deptId, startDate, endDate);

        return page;
    }

    @Override
    public Page findTeamDailyDetail(Page page, String deptId, String date) {
        if (StringUtils.isBlank(deptId) || StringUtils.isBlank(date)) return page;
        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        return reportMapper.findTeamDailyDetail(page, deptId, startDate, endDate);
    }

    @Override
    public List<ReportVo> exportTeamDailyDetail(String deptId, String date) {
        List<ReportVo> list = new ArrayList<>();
        if (StringUtils.isBlank(deptId) || StringUtils.isBlank(date)) return list;

        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        return reportMapper.findTeamDailyDetail(deptId, startDate, endDate);
    }

    //个人周报
    @Override
    public WeekVo getWeek(String followId, String betweenDate) {
        WeekVo weekVo = new WeekVo(betweenDate);

        List items = new ArrayList();

        ReportVo week = reportMapper.sumByTime(followId, weekVo.getStartDateHMS(), weekVo.getEndDateHMS());

        items.add(week);

        weekVo.setItems(items);
        return weekVo;
    }

    //团队周报
    @Override
    public WeekVo getTeamWeek(String deptId, String betweenDate) {
        WeekVo weekVo = new WeekVo(betweenDate);

        List<ReportVo> reportVos = reportMapper.findTeamByTime(deptId, weekVo.getStartDateHMS(), weekVo.getEndDateHMS());

        weekVo.setItems(reportVos);

        return weekVo;
    }

    @Override
    public MonthVo getMonth(String followId, String month) {
        MonthVo monthVo = new MonthVo(month);

        List list = new ArrayList();

        ReportVo rp = reportMapper.sumByTime(followId, monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());

        list.add(rp);

        monthVo.setItems(list);

        return monthVo;

    }

    @Override
    public MonthVo getTeamMonth(String deptId, String month) {
        MonthVo monthVo = new MonthVo(month);

        List<ReportVo> reportVos = reportMapper.findTeamByTime(deptId, monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());

        monthVo.setItems(reportVos);

        return monthVo;
    }

    @Override
    public List findAdminDaily(String date) {
        if (StringUtils.isBlank(date)) return null;

        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        List list = sourceReportMapper.findByTime(null, startDate, endDate);
        return SourceReportVo.sum(list);
    }

    @Override
    public List<AdminDetailVo> findAdminDailyDetail(String date) {
        if (StringUtils.isBlank(date)) return null;

        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        List<AdminDetailVo> totalList = reportMapper.findAdminDetailTotal(startDate, endDate);

        for (AdminDetailVo vo : totalList) {
            List perList = reportMapper.findAdminDetailPer(vo.getDeptId(), startDate, endDate);
            vo.setpList(perList);
        }

        return totalList;
    }

    @Override
    public List<ReportVo> findAdminDailyDetailList(String date) {
        List<ReportVo> reportVos = new ArrayList<>();

        List<AdminDetailVo> list = this.findAdminDailyDetail(date);
        //进行处理，将子集合取出来
        for (AdminDetailVo adv : list) {
            List<ReportVo> reportVos1 = adv.getpList();
            reportVos.add(adv);
            reportVos.addAll(reportVos1);
        }
        return reportVos;
    }

    @Override
    public WeekVo findAdminWeek(String betweenDate) {
        WeekVo weekVo = new WeekVo(betweenDate);

        SourceReportVo zg = sourceReportMapper.sumByTime(SourceReport.TYPE_ZG.getId(), weekVo.getStartDateHMS(), weekVo.getEndDateHMS());
        SourceReportVo maker = sourceReportMapper.sumByTime(SourceReport.TYPE_MAKER.getId(), weekVo.getStartDateHMS(), weekVo.getEndDateHMS());
        SourceReportVo other = sourceReportMapper.sumByTime(SourceReport.TYPE_OTHER.getId(), weekVo.getStartDateHMS(), weekVo.getEndDateHMS());

        weekVo.addSRItem(zg);
        weekVo.addSRItem(maker);
        weekVo.addSRItem(other);

        return weekVo;
    }

    @Override
    public List<AdminDetailVo> findAdminWeekDetail(String betweenDate) {
        WeekVo weekVo = new WeekVo(betweenDate);

        List<AdminDetailVo> totalList = reportMapper.findAdminDetailTotal(weekVo.getStartDateHMS(), weekVo.getEndDateHMS());

        for (AdminDetailVo vo : totalList) {
            List perList = reportMapper.findAdminDetailPer(vo.getDeptId(), weekVo.getStartDateHMS(), weekVo.getEndDateHMS());
            vo.setpList(perList);
        }

        return totalList;
    }

    @Override
    public List<ReportVo> findAdminWeekDetailList(String betweenDate) {
        List<ReportVo> reportVos = new ArrayList<>();

        List<AdminDetailVo> list = this.findAdminWeekDetail(betweenDate);
        //进行处理，将子集合取出来
        for (AdminDetailVo adv : list) {
            List<ReportVo> reportVos1 = adv.getpList();
            reportVos.add(adv);
            reportVos.addAll(reportVos1);
        }
        return reportVos;
    }

    @Override
    public MonthVo findAdminMonth(String month) {
        MonthVo monthVo = new MonthVo(month);

        SourceReportVo zg = sourceReportMapper.sumByTime(SourceReport.TYPE_ZG.getId(), monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());
        SourceReportVo maker = sourceReportMapper.sumByTime(SourceReport.TYPE_MAKER.getId(), monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());
        SourceReportVo other = sourceReportMapper.sumByTime(SourceReport.TYPE_OTHER.getId(), monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());

        monthVo.addSRItem(zg);
        monthVo.addSRItem(maker);
        monthVo.addSRItem(other);

        return monthVo;
    }

    @Override
    public List<AdminDetailVo> findAdminMonthDetail(String month) {
        MonthVo monthVo = new MonthVo(month);

        List<AdminDetailVo> totalList = reportMapper.findAdminDetailTotal(monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());

        for (AdminDetailVo vo : totalList) {
            List perList = reportMapper.findAdminDetailPer(vo.getDeptId(), monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());
            vo.setpList(perList);
        }
        return totalList;
    }

    @Override
    public List<ReportVo> findAdminMonthDetailList(String month) {
        List<ReportVo> reportVos = new ArrayList<>();

        List<AdminDetailVo> list = this.findAdminMonthDetail(month);
        //进行处理，将子集合取出来
        for (AdminDetailVo adv : list) {
            List<ReportVo> reportVos1 = adv.getpList();
            reportVos.add(adv);
            reportVos.addAll(reportVos1);
        }
        return reportVos;
    }
}