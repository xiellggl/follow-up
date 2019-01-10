package com.dayi.follow.service.impl;

import com.dayi.common.util.BigDecimals;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.ReportService;
import com.dayi.follow.vo.report.*;
import com.dayi.mybatis.support.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
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
    CountService countService;


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

        page = reportMapper.findTeamDailyDetail(page, deptId, startDate, endDate);
        return page;

    }

    @Override
    public List<ReportDailyVo> exportTeamDailyDetail(String deptId, String date) {
        List<ReportDailyVo> list = new ArrayList<ReportDailyVo>();
        if (StringUtils.isBlank(deptId) || StringUtils.isBlank(date)) return list;

        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        list = reportMapper.findTeamDailyDetail(deptId, startDate, endDate);
        return list;

    }

    //个人周报
    @Override
    public MyWeekVo getWeek(String followId, String betweenDate) {
        MyWeekVo weekVo;

        DateVo dateVo = this.doWeekDate(betweenDate);

        weekVo = reportMapper.getWeek(followId, dateVo.getStartDate(), dateVo.getEndDate());

        dateVo.setEndDate(DateTime.parse(dateVo.getEndDate(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toString("yyyy-MM-dd"));

        if (weekVo == null) {
            weekVo = new MyWeekVo();
        }

        BeanUtils.copyProperties(dateVo, weekVo);

        if (weekVo.getInCash() != null && weekVo.getOutCash() != null) {
            weekVo.setGrowthFund(weekVo.getInCash().subtract(weekVo.getOutCash()));
        }

        return weekVo;
    }

    private DateVo doWeekDate(String betweenDate) {
        DateVo dateVo = new DateVo();

        dateVo.setLastWeekStart(DateTime.now().plusWeeks(-1).withDayOfWeek(1).toString("yyyy-MM-dd"));
        dateVo.setLastWeekEnd(DateTime.now().plusWeeks(-1).withDayOfWeek(5).toString("yyyy-MM-dd"));
        dateVo.setThisWeekStart(DateTime.now().withDayOfWeek(1).toString("yyyy-MM-dd"));
        dateVo.setThisWeekEnd(DateTime.now().withDayOfWeek(5).toString("yyyy-MM-dd"));

        if (StringUtils.isBlank(betweenDate)) {//默认取上个星期
            dateVo.setStartDate(dateVo.getLastWeekStart());
            dateVo.setEndDate(DateTime.now().plusWeeks(-1).withDayOfWeek(5).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss"));
        } else {
            String[] split = betweenDate.split(" - ");
            dateVo.setStartDate(split[0]);
            dateVo.setEndDate(DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss"));
        }
        return dateVo;
    }

    //团队周报
    @Override
    public TeamWeekVo countTeamWeek(String deptId, String betweenDate) {
        TeamWeekVo teamWeekVo = new TeamWeekVo();

        DateVo dateVo = this.doWeekDate(betweenDate);

        List<ReportVo> reportVos = reportMapper.findTeamWeek(deptId, dateVo.getStartDate(), dateVo.getEndDate());

        dateVo.setEndDate(DateTime.parse(dateVo.getEndDate(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toString("yyyy-MM-dd"));
        BeanUtils.copyProperties(dateVo, teamWeekVo);

        if (reportVos.isEmpty()) return teamWeekVo;

        teamWeekVo.setReportVos(doMore(reportVos));

        return teamWeekVo;
    }

    private DateVo doMonthDate(String date) {
        DateVo dateVo = new DateVo();

        dateVo.setLastMonth(DateTime.now().plusMonths(-1).toString("yyyy-MM"));//上月
        dateVo.setThisMonth(DateTime.now().toString("yyyy-MM"));//本月

        if (StringUtils.isBlank(date)) {//默认取上个月
            dateVo.setMonth(dateVo.getLastMonth());
        } else {
            dateVo.setMonth(date);
        }
        return dateVo;
    }

    @Override
    public MyMonthVo getMonth(String followId, String month) {

        MyMonthVo monthVo;

        DateVo dateVo = this.doMonthDate(month);

        String startDate = DateTime.parse(dateVo.getMonth()).toString("yyyy-MM-dd HH:mm:ss");//本月开始
        String endDate = DateTime.parse(dateVo.getMonth()).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

        monthVo = reportMapper.getMonth(followId, startDate, endDate);

        if (monthVo == null) {
            monthVo = new MyMonthVo();
        }

        BeanUtils.copyProperties(dateVo, monthVo);

        if (monthVo.getInCash() != null && monthVo.getOutCash() != null) {
            monthVo.setGrowthFund(monthVo.getInCash().subtract(monthVo.getOutCash()));
        }
        return monthVo;

    }

    @Override
    public TeamMonthVo countTeamMonth(String deptId, String month) {
        TeamMonthVo teamMonthVo = new TeamMonthVo();

        DateVo dateVo = doMonthDate(month);

        String startDate = DateTime.parse(dateVo.getMonth()).toString("yyyy-MM-dd HH:mm:ss");//本月开始
        String endDate = DateTime.parse(dateVo.getMonth()).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

        List<ReportVo> reportVos = reportMapper.findTeamMonth(deptId, startDate, endDate);

        teamMonthVo.setReportVos(doMore(reportVos));

        BeanUtils.copyProperties(dateVo, teamMonthVo);

        return teamMonthVo;
    }

    @Override
    public Page<ReportVo> findAdminDaily(Page page, String deptName, String betweenDate) {
        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = betweenDate.split(" - ");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        page = reportMapper.findAdminDaily(page, deptName, startDate, endDate);

        return page;
    }

    @Override
    public Page findAdminDailyDetail(Page page, String deptId, String date) {

        if (StringUtils.isBlank(deptId) || StringUtils.isBlank(date)) return page;//必须同时满足

        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        return reportMapper.findAdminDailyDetail(page, deptId, startDate, endDate);
    }

    @Override
    public List<ReportDailyVo> findAdminDailyDetailList(String deptId, String date) {
        List<ReportDailyVo> list = new ArrayList();
        if (StringUtils.isBlank(deptId) || StringUtils.isBlank(date)) return list;//必须同时满足

        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        return reportMapper.findAdminDailyDetail(deptId, startDate, endDate);
    }

    @Override
    public AdminWeekVo countAdminWeek(Page page, String betweenDate) {
        AdminWeekVo adminWeekVo = new AdminWeekVo();
        String startDate;
        String endDate;

        DateVo dateVo = doWeekDate(betweenDate);

        if (StringUtils.isBlank(betweenDate)) {
            startDate = dateVo.getLastWeekStart();
            endDate = dateVo.getLastWeekEnd();
        } else {
            String[] split = betweenDate.split(" - ");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        dateVo.setEndDate(DateTime.parse(dateVo.getEndDate(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toString("yyyy-MM-dd"));
        BeanUtils.copyProperties(dateVo, adminWeekVo);

        page = reportMapper.findAdminWeekSum(page, startDate, endDate);

        Page per = new Page();
        per.setPageNo(page.getPageNo());
        per.setPageSize(page.getPageSize() * 5);

        per = reportMapper.findAdminWeekPer(per, startDate, endDate);

        adminWeekVo.setWeekVos(mergeData(page.getResults(), per.getResults()));

        return adminWeekVo;
    }

    @Override
    public List<WeekVo> findAdminWeekList(String betweenDate) {
        List<WeekVo> list = new ArrayList<>();

        if (StringUtils.isBlank(betweenDate)) return list;

        String[] split = betweenDate.split(" - ");
        String startDate = split[0];
        String endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        List<WeekVo> sum = reportMapper.findAdminWeekSum(startDate, endDate);

        Page page = new Page();
        page.setPageSize(sum.size() * 5);
        Page<WeekVo> per = reportMapper.findAdminWeekPer(page, startDate, endDate);

        return mergeData(sum, per.getResults());
    }

    @Override
    public AdminMonthVo countAdminMonth(Page page, String month) {
        AdminMonthVo adminMonthVo = new AdminMonthVo();

        DateVo dateVo = doMonthDate(month);

        String startDate = DateTime.parse(dateVo.getMonth()).toString("yyyy-MM-dd HH:mm:ss");//本月开始
        String endDate = DateTime.parse(dateVo.getMonth()).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

        BeanUtils.copyProperties(dateVo, adminMonthVo);

        page = reportMapper.findAdminMonth(page, startDate, endDate);//本月
        adminMonthVo.setMonthVos(doAdminMonth(page.getResults(), dateVo.getMonth()));
        return adminMonthVo;
    }

    @Override
    public List<MonthVo> findAdminMonthList(String deptId, String month) {
        List<MonthVo> list = new ArrayList<>();
        if (StringUtils.isBlank(month)) return list;

        String startDate = DateTime.parse(month).toString("yyyy-MM-dd HH:mm:ss");//本月开始
        String endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

        List<MonthVo> monthList = reportMapper.findAdminMonth(startDate, endDate);//本月
        return doAdminMonth(monthList, month);
    }

    private List<MonthVo> doAdminMonth(List<MonthVo> list, String month) {
        String date1 = "";
        String date2 = "";
        String date3 = "";
        String date4 = "";
        if (!StringUtils.isBlank(month)) {
            date1 = DateTime.parse(month).dayOfMonth().withMaximumValue().millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");//本月最后一天开始
            date2 = DateTime.parse(month).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

            date3 = DateTime.parse(month).plusMonths(-1).dayOfMonth().withMaximumValue().millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");//上月最后一天开始
            date4 = DateTime.parse(month).plusMonths(-1).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//上月结束
        } else {
            return list;
        }
        for (MonthVo vo : list) {
            if (vo == null) continue;
            //获取新签创客
            int orgNum = reportMapper.getNewSignOrgNum(vo.getFollowId(), month, date2);
            vo.setOrgNum(orgNum);

            BigDecimal manageFund1 = reportMapper.getLastManageFund(vo.getFollowId(), date1, date2);//当月最后一天管理资产
            vo.setManageFund(manageFund1.setScale(2));
            if (BigDecimal.ZERO.compareTo(manageFund1) == 0) {
                vo.setRingGrowthRatio("暂无数据");
                vo.setManageFund(null);
                continue;
            }
            BigDecimal manageFund2 = reportMapper.getLastManageFund(vo.getFollowId(), date3, date4);//上月最后一天管理资产
            if (BigDecimal.ZERO.compareTo(manageFund2) == 0) {
                vo.setRingGrowthRatio("上月无数据");
                continue;
            }
            BigDecimal subtract = manageFund1.subtract(manageFund2);//获取净增
            BigDecimal divide = subtract.divide(manageFund2, 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal value = divide.multiply(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            vo.setRingGrowthRatio(value + "%");
        }
        return list;
    }

    private List<WeekVo> mergeData(List<WeekVo> sum, List<WeekVo> per) {
        List<WeekVo> adminWeekVos = new ArrayList<>();

        for (int j = 0; j < sum.size(); j++) {
            WeekVo vo = new WeekVo();
            vo.setDeptName(sum.get(j).getDeptName());
            vo.setName(sum.get(j).getName());
            vo.setOpenAccountNum(sum.get(j).getOpenAccountNum());
            vo.setInCash(sum.get(j).getInCash());

            for (int i = 0 + 5 * j; i < 5 + 5 * j; i++) {
                switch ((i + 5) % 5) {
                    case 0:
                        vo.setMonOpen(per.get(i).getOpenAccountNum());
                        vo.setMonInCash(per.get(i).getInCash());
                        break;
                    case 1:
                        vo.setTueOpen(per.get(i).getOpenAccountNum());
                        vo.setTueInCash(per.get(i).getInCash());
                        break;
                    case 2:
                        vo.setWedOpen(per.get(i).getOpenAccountNum());
                        vo.setWedInCash(per.get(i).getInCash());
                        break;
                    case 3:
                        vo.setThuOpen(per.get(i).getOpenAccountNum());
                        vo.setThuInCash(per.get(i).getInCash());
                        break;
                    case 4:
                        vo.setFriOpen(per.get(i).getOpenAccountNum());
                        vo.setFriInCash(per.get(i).getInCash());
                        break;
                }

            }
            adminWeekVos.add(vo);
        }
        return adminWeekVos;

    }

    /**
     * 统计团队周报和团队月报的总计数据
     */
    private List<ReportVo> doMore(List<ReportVo> items) {
        ReportVo sum = new ReportVo();
        if (CollectionUtils.isEmpty(items)) {
            return items;
        }
        for (ReportVo item : items) {
            BigDecimal subtracts = BigDecimals.subtracts(item.getInCash().doubleValue(), item.getOutCash().doubleValue());
            item.setGrowthFund(subtracts); //计算本周资金净增

            if (sum.getOpenAccountNum() == null) {
                sum.setOpenAccountNum(0 + item.getOpenAccountNum());//开户数
            } else {
                sum.setOpenAccountNum(sum.getOpenAccountNum() + item.getOpenAccountNum());//开户数
            }

            if (sum.getInCash() == null) {
                sum.setInCash(BigDecimal.ZERO.add(item.getInCash()));//入金
            } else {
                sum.setInCash(sum.getInCash().add(item.getInCash()));//入金
            }

            if (sum.getOutCash() == null) {
                sum.setOutCash(BigDecimal.ZERO.add(item.getOutCash()));//出金
            } else {
                sum.setOutCash(sum.getOutCash().add(item.getOutCash()));//出金
            }

            if (sum.getManageGrowthFund() == null) {
                sum.setManageGrowthFund(BigDecimal.ZERO.add(item.getManageGrowthFund()));//管理资金净增
            } else {
                sum.setManageGrowthFund(sum.getManageGrowthFund().add(item.getManageGrowthFund()));//管理资金净增
            }

            if (sum.getGrowthFund() == null) {
                sum.setGrowthFund(BigDecimal.ZERO.add(item.getGrowthFund()));//资金净增
            } else {
                sum.setGrowthFund(sum.getGrowthFund().add(item.getGrowthFund()));//资金净增
            }

        }
        sum.setName("团队总计");
        items.add(sum);
        return items;
    }
}