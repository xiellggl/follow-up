package com.dayi.follow.service.impl;

import com.dayi.common.util.BigDecimals;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.ReportService;
import com.dayi.follow.vo.export.AdminWeekExport;
import com.dayi.follow.vo.report.AdminMonthVo;
import com.dayi.follow.vo.report.AdminWeekVo;
import com.dayi.follow.vo.report.ReportDailyVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOError;
import java.io.IOException;
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
            String[] split = StringUtils.split(betweenDate, ",");
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
            String[] split = StringUtils.split(betweenDate, ",");
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

        list = reportMapper.findTeamDailyDetailList(deptId, startDate, endDate);
        return list;

    }

    //个人周报
    @Override
    public ReportDailyVo getWeek(String followId, String betweenDate) {
        ReportDailyVo vo = new ReportDailyVo();

        if (StringUtils.isBlank(betweenDate)) return vo;

        String[] split = betweenDate.split(" - ");
        String startDate = split[0];
        String endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        vo = reportMapper.getWeek(followId, startDate, endDate);
        if (vo == null) return vo;
        vo.setGrowthFund(vo.getInCash().subtract(vo.getOutCash()));
        return vo;
    }

    //团队周报
    @Override
    public List<ReportDailyVo> findTeamWeek(String deptId, String betweenDate) {
        List<ReportDailyVo> list = new ArrayList<>();

        if (StringUtils.isBlank(betweenDate)) return list;

        String[] split = betweenDate.split(" - ");
        String startDate = split[0];
        String endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        list = reportMapper.findTeamWeek(deptId, startDate, endDate);
        return doMore(list);
    }

    @Override
    public ReportDailyVo getMonth(String followId, String month) {

        ReportDailyVo monthVo = new ReportDailyVo();

        if (StringUtils.isBlank(month)) return monthVo;
        String startDate = DateTime.parse(month).toString("yyyy-MM-dd HH:mm:ss");//本月开始
        String endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

        monthVo = reportMapper.getMonth(followId, startDate, endDate);
        if (monthVo == null) return monthVo;
        monthVo.setGrowthFund(monthVo.getInCash().subtract(monthVo.getOutCash()));
        return monthVo;

    }

    @Override
    public List<ReportDailyVo> findTeamMonth(String deptId, String month) {
        List<ReportDailyVo> list = new ArrayList<>();

        if (StringUtils.isBlank(month)) return list;
        String startDate = DateTime.parse(month).toString("yyyy-MM-dd HH:mm:ss");//本月开始
        String endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

        list = reportMapper.findTeamMonth(deptId, startDate, endDate);
        return doMore(list);
    }

    @Override
    public Page<ReportDailyVo> findAdminDaily(Page page, String deptId, String deptName, String betweenDate) {
        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = betweenDate.split(" - ");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        page = reportMapper.findAdminDaily(page, followIds, deptName, startDate, endDate);

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
    public Page<AdminWeekVo> findAdminWeek(Page page, String deptId, String betweenDate) {
        if (StringUtils.isBlank(betweenDate)) return page;

        String[] split = betweenDate.split(" - ");
        String startDate = split[0];
        String endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        page = reportMapper.findAdminWeekSum(page, followIds, startDate, endDate);

        Page per = new Page();
        per.setPageNo(page.getPageNo());
        per.setPageSize(page.getPageSize() * 5);

        per = reportMapper.findAdminWeekPer(per, followIds, startDate, endDate);

        page.setResults(mergeData(page.getResults(), per.getResults()));

        return page;

    }

    @Override
    public List<AdminWeekVo> findAdminWeekList(String deptId, String betweenDate) {
        List<AdminWeekVo> list = new ArrayList<>();

        if (StringUtils.isBlank(betweenDate)) return list;

        String[] split = betweenDate.split(" - ");
        String startDate = split[0];
        String endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        List<ReportDailyVo> sum = reportMapper.findAdminWeekSumList(followIds, startDate, endDate);

        Page page = new Page();
        page.setPageSize(sum.size() * 5);
        Page<ReportDailyVo> per = reportMapper.findAdminWeekPer(page, followIds, startDate, endDate);
        return mergeData(sum, per.getResults());

    }

    @Override
    public Page<AdminMonthVo> findAdminMonth(Page page, String deptId, String month) {
        if (StringUtils.isBlank(month)) return page;
        String startDate = DateTime.parse(month).toString("yyyy-MM-dd HH:mm:ss");//本月开始
        String endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        page = reportMapper.findAdminMonth(page, followIds, startDate, endDate);//本月
        page.setResults(countGrowthRatio(page.getResults(), month));
        return page;
    }

    @Override
    public List<AdminMonthVo> findAdminMonthList(String deptId, String month) {
        List<AdminMonthVo> list = new ArrayList<>();
        if (StringUtils.isBlank(month)) return list;

        String startDate = DateTime.parse(month).toString("yyyy-MM-dd HH:mm:ss");//本月开始
        String endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        List<AdminMonthVo> monthList = reportMapper.findAdminMonthList(followIds, startDate, endDate);//本月
        return countGrowthRatio(monthList, month);
    }

    private List<AdminMonthVo> countGrowthRatio(List<AdminMonthVo> list, String month) {
        String date1 = "";
        String date2 = "";
        String date3 = "";
        String date4 = "";
        if (!StringUtils.isBlank(month)) {
            date1 = DateTime.parse(month).dayOfMonth().withMaximumValue().millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");//本月最后一天开始
            date2 = DateTime.parse(month).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

            date3 = DateTime.parse(month).plusMonths(-1).dayOfMonth().withMaximumValue().millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");//上月最后一天开始
            date4 = DateTime.parse(month).plusMonths(-1).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//上月结束
        }
        for (AdminMonthVo vo : list) {
            if (vo == null) continue;
            double manageFund1 = reportMapper.getLastManageFund(vo.getFollowId(), date1, date2);//当月最后一天管理资产
            vo.setManageFund(BigDecimal.valueOf(manageFund1).setScale(2));
            if (manageFund1 == 0) {
                vo.setRingGrowthRatio("暂无数据");
                break;
            }
            double manageFund2 = reportMapper.getLastManageFund(vo.getFollowId(), date3, date4);//上月最后一天管理资产
            if (manageFund2 == 0) {
                vo.setRingGrowthRatio("上月无数据");
                break;
            }
            BigDecimal subtract = BigDecimals.subtracts(manageFund1, manageFund2);//获取净增
            BigDecimal divide = subtract.divide(BigDecimal.valueOf(manageFund2), 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal value = divide.multiply(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            vo.setRingGrowthRatio(value + "%");
        }
        return list;
    }

    private List<AdminWeekVo> mergeData(List<ReportDailyVo> sum, List<ReportDailyVo> per) {

        List<AdminWeekVo> adminWeekVos = new ArrayList<AdminWeekVo>();
        for (int j = 0; j < sum.size(); j++) {
            AdminWeekVo vo = new AdminWeekVo();

            vo.setDeptName(sum.get(j).getDeptName());
            vo.setName(sum.get(j).getName());
            vo.setOpenNum(sum.get(j).getOpenAccountNum());
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
    private List<ReportDailyVo> doMore(List<ReportDailyVo> items) {
        ReportDailyVo sum = new ReportDailyVo();
        if (CollectionUtils.isEmpty(items)) {
            return items;
        }
        for (ReportDailyVo item : items) {
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