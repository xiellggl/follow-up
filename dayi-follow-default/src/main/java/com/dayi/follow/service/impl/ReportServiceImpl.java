package com.dayi.follow.service.impl;

import com.dayi.common.util.BigDecimals;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.ReportService;
import com.dayi.follow.vo.report.AdminMonthVo;
import com.dayi.follow.vo.report.AdminWeekVo;
import com.dayi.follow.vo.report.ReportDailyVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
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
        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = StringUtils.split(betweenDate, ",");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }
        ReportDailyVo week = reportMapper.getWeek(followId, startDate, endDate);
        week.setGrowthFund(week.getInCash().subtract(week.getOutCash()));
        return week;
    }

    //团队周报
    @Override
    public List<ReportDailyVo> findTeamWeek(String deptId, String betweenDate) {
        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = StringUtils.split(betweenDate, ",");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }
        List<ReportDailyVo> teamWeek = reportMapper.findTeamWeek(deptId, startDate, endDate);
        return doMore(teamWeek);
    }

    @Override
    public ReportDailyVo getMonth(String followId, String month) {

        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(month)) {
            startDate = DateTime.parse(month).dayOfMonth().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
            endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        return reportMapper.getMonth(followId, startDate, endDate);

    }

    @Override
    public List<ReportDailyVo> findTeamMonth(String deptId, String month) {
        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(month)) {
            startDate = DateTime.parse(month).dayOfMonth().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
            endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }
        List<ReportDailyVo> teamMonth = reportMapper.findTeamMonth(deptId, startDate, endDate);
        return doMore(teamMonth);
    }

    @Override
    public Page<ReportDailyVo> findAdminDaily(Page page, String deptId, String deptName, String betweenDate) {
        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = StringUtils.split(betweenDate, ",");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        page = reportMapper.findAdminDaily(page, followIds, deptName, startDate, endDate);

        return page;
    }

    @Override
    public Page findAdminDailyDetail(Page page, String deptId, String date) {
        if (StringUtils.isBlank(date)) return page;
        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        return reportMapper.findAdminDailyDetail(page, deptId, startDate, endDate);
    }

    @Override
    public Page<AdminWeekVo> findAdminWeek(Page page, String deptId, String betweenDate) {

        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = StringUtils.split(betweenDate, ",");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        page = reportMapper.findAdminWeekSum(page, followIds, startDate, endDate);

        page.setPageSize(page.getPageSize() * 5);
        Page<ReportDailyVo> per = reportMapper.findAdminWeekPer(page, followIds, startDate, endDate);

        page.setResults(mergeData(page.getResults(), per.getResults()));

        return page;

    }

    @Override
    public List<AdminWeekVo> exportAdminWeek(String deptId, String betweenDate) {

        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = StringUtils.split(betweenDate, ",");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        Page<ReportDailyVo> sum = reportMapper.findAdminWeekSum(null, followIds, startDate, endDate);

        Page<ReportDailyVo> per = reportMapper.findAdminWeekPer(null, followIds, startDate, endDate);
        return mergeData(sum.getResults(), per.getResults());
    }

    @Override
    public Page<AdminMonthVo> findAdminMonth(Page page, String deptId, String month) {
        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(month)) {
            startDate = DateTime.parse(month).toString("yyyy-MM-dd HH:mm:ss");//本月开始
            endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束
        }

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        page = reportMapper.findAdminMonth(page, followIds, startDate, endDate);//本月
        page.setResults(countGrowthRatio(page.getResults(), month));
        return page;
    }

    @Override
    public List<AdminMonthVo> exportAdminMonth(String deptId, String month) {
        String startDate = "";
        String endDate = "";

        if (!StringUtils.isBlank(month)) {
            startDate = DateTime.parse(month).toString("yyyy-MM-dd HH:mm:ss");//本月开始
            endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束
        }

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        Page<AdminMonthVo> page = reportMapper.findAdminMonth(null, followIds, startDate, endDate);//本月
        return countGrowthRatio(page.getResults(), month);
    }

    private List<AdminMonthVo> countGrowthRatio(List<AdminMonthVo> list, String month) {
        String date1 = "";
        String date2 = "";
        String date3 = "";
        String date4 = "";
        if (!StringUtils.isBlank(month)) {
            date1 = DateTime.parse(month).dayOfMonth().withMaximumValue().plusDays(-1).plusSeconds(1).toString("yyyy-MM-dd HH:mm:ss");//本月最后一天开始
            date2 = DateTime.parse(month).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束

            date3 = DateTime.parse(month).plusMonths(-1).dayOfMonth().withMaximumValue().plusDays(-1).plusSeconds(1).toString("yyyy-MM-dd HH:mm:ss");//上月最后一天开始
            date4 = DateTime.parse(month).plusMonths(-1).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//上月结束
        }
        for (AdminMonthVo vo : list) {
            double manageFund1 = reportMapper.getLastManageFund(vo.getId(), date1, date2);//当月最后一天管理资产
            vo.setManageFund(BigDecimal.valueOf(manageFund1));
            if (manageFund1 == 0) {
                vo.setRingGrowthRatio("暂无数据");
                break;
            }
            double manageFund2 = reportMapper.getLastManageFund(vo.getId(), date3, date4);//上月最后一天管理资产
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