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
    public Page<AdminWeekVo> findAdminDaily(Page page, String deptId,String deptName, String betweenDate) {
        String startDate = "";
        String endDate = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = StringUtils.split(betweenDate, ",");
            startDate = split[0];
            endDate = DateTime.parse(split[1]).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        List<ReportDailyVo> adminDaily = reportMapper.findAdminDaily(followIds, deptName, startDate, endDate, page.getStartRow(), page.getPageSize());
        int num = reportMapper.getAdminDailyNum(followIds, deptName, startDate, endDate);

        page.setResults(adminDaily);
        page.setTotalRecord(num);

        return page;
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

        List<ReportDailyVo> sum = reportMapper.findAdminWeekSum(followIds, startDate, endDate, page.getStartRow(), page.getPageSize());

        int count = reportMapper.findAdminWeekCount(followIds, startDate, endDate);

        page.setPageSize(page.getPageSize() * 5);
        List<ReportDailyVo> per = reportMapper.findAdminWeekPer(followIds, startDate, endDate, page.getStartRow(), page.getPageSize());
        page.setResults(mergeData(sum, per));
        page.setTotalRecord(count);
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

        List<ReportDailyVo> sum = reportMapper.findAdminWeekSum(followIds, startDate, endDate, null, null);

        List<ReportDailyVo> per = reportMapper.findAdminWeekPer(followIds, startDate, endDate, null, null);
        return mergeData(sum, per);
    }

    @Override
    public Page<ReportDailyVo> findAdminMonth(Page page, String deptId, String month) {
        String startDate = "";
        String endDate = "";

        String lastStartDate = "";
        if (!StringUtils.isBlank(month)) {
            startDate = DateTime.parse(month).dayOfMonth().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
            lastStartDate = DateTime.parse(month).dayOfMonth().withMaximumValue().plusDays(-1).toString("yyyy-MM-dd HH:mm:ss");
            endDate = DateTime.parse(month).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        }

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        List<AdminMonthVo> list = reportMapper.findAdminMonth(followIds, startDate, endDate, page.getStartRow(), page.getPageSize());//本月

        for (AdminMonthVo vo : list) {
            //获取管理资产
            double manageFund = reportMapper.getLastManageFund(vo.getId(), lastStartDate, endDate);
            vo.setManageFund(BigDecimal.valueOf(manageFund));
            //获取净增
        }


        return page;
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

            sum.setOpenAccountNum(sum.getOpenAccountNum() + item.getOpenAccountNum());//开户数

            double inCash = BigDecimals.add(sum.getInCash().doubleValue(), item.getInCash().doubleValue());//入金
            sum.setInCash(BigDecimal.valueOf(inCash));

            double outCash = BigDecimals.add(sum.getOutCash().doubleValue(), item.getOutCash().doubleValue());//出金
            sum.setOutCash(BigDecimal.valueOf(outCash));

            double manageGrowthFund = BigDecimals.add(sum.getOutCash().doubleValue(), item.getOutCash().doubleValue());//管理资金净增
            sum.setManageGrowthFund(BigDecimal.valueOf(manageGrowthFund));
        }
        sum.setName("团队总计");
        items.add(sum);
        return items;
    }
}