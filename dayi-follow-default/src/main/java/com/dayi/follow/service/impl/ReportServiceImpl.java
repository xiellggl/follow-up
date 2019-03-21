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

        page = reportMapper.findTeamDailyDetail(page, deptId, startDate, endDate);
        return page;

    }

    @Override
    public List<ReportVo> exportTeamDailyDetail(String deptId, String date) {
        List<ReportVo> list = new ArrayList<>();
        if (StringUtils.isBlank(deptId) || StringUtils.isBlank(date)) return list;

        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        list = reportMapper.findTeamDailyDetail(deptId, startDate, endDate);
        return list;

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

        weekVo.setItems(doMore(reportVos));

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

        monthVo.setItems(doMore(reportVos));

        return monthVo;
    }

    @Override
    public List findAdminDaily(String date) {
        if (StringUtils.isBlank(date)) return null;

        String startDate = DateTime.parse(date).millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.parse(date).millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        return sourceReportMapper.findByTime(null, startDate, endDate);
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

        List srs = new ArrayList();

        SourceReport zg = sourceReportMapper.sumByTime(SourceReport.TYPE_ZG.getId(), weekVo.getStartDateHMS(), weekVo.getEndDateHMS());
        SourceReport maker = sourceReportMapper.sumByTime(SourceReport.TYPE_MAKER.getId(), weekVo.getStartDateHMS(), weekVo.getEndDateHMS());
        SourceReport other = sourceReportMapper.sumByTime(SourceReport.TYPE_OTHER.getId(), weekVo.getStartDateHMS(), weekVo.getEndDateHMS());

        srs.add(zg);
        srs.add(maker);
        srs.add(other);

        weekVo.setItems(srs);

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

        List srs = new ArrayList();

        SourceReport zg = sourceReportMapper.sumByTime(SourceReport.TYPE_ZG.getId(), monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());
        SourceReport maker = sourceReportMapper.sumByTime(SourceReport.TYPE_MAKER.getId(), monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());
        SourceReport other = sourceReportMapper.sumByTime(SourceReport.TYPE_OTHER.getId(), monthVo.getMonthStartHMS(), monthVo.getMonthEndHMS());

        srs.add(zg);
        srs.add(maker);
        srs.add(other);

        monthVo.setItems(srs);
        return null;
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

//    private List<MonthVo> doAdminMonth(List<MonthVo> list, String month) {
//        String date1 = "";
//        String date2 = "";
//        String date3 = "";
//        String date4 = "";
//        if (!StringUtils.isBlank(month)) {
//            date1 = DateTime.parse(month).dayOfMonth().withMaximumValue().millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");//本月最后一天开始
//            date2 = DateTime.parse(month).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//本月结束
//
//            date3 = DateTime.parse(month).plusMonths(-1).dayOfMonth().withMaximumValue().millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");//上月最后一天开始
//            date4 = DateTime.parse(month).plusMonths(-1).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");//上月结束
//        } else {
//            return list;
//        }
//        for (MonthVo vo : list) {
//            if (vo == null) continue;
//            //获取新签创客
//            int orgNum = followOrgMapper.getNewSignOrgNum(vo.getFollowId(), month, date2, dayiDataBaseStr);
//            vo.setOrgNum(orgNum);
//
//            FollowUpLog log1 = followUpLogMapper.getLog(vo.getFollowId(), date1, date2);
//
//            if (log1 != null) {//这月最后一天日报已经生成
//                BigDecimal agentFund1 = followAgentMapper.getAgentFund(vo.getFollowId(), date2, dayiDataBaseStr);//加上代理商
//                BigDecimal fund1 = log1.getManageFund().add(agentFund1);
//                vo.setManageFund(fund1.setScale(2, BigDecimal.ROUND_HALF_UP));
//
//                FollowUpLog log2 = followUpLogMapper.getLog(vo.getFollowId(), date3, date4);
//                if (log2 == null) {
//                    vo.setRingGrowthRatio("上月无数据");
//                    continue;
//                }
//
//                BigDecimal agentFund2 = followAgentMapper.getAgentFund(vo.getFollowId(), date4, dayiDataBaseStr);
//                BigDecimal fund2 = log2.getManageFund().add(agentFund2);
//
//                if (BigDecimal.ZERO.compareTo(fund2) == 0) {
//                    vo.setRingGrowthRatio("上月无数据");
//                } else {
//                    BigDecimal subtract = fund1.subtract(fund2);//获取净增
//                    BigDecimal divide = subtract.divide(fund2, 2, BigDecimal.ROUND_HALF_UP);
//                    BigDecimal value = divide.multiply(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
//                    vo.setRingGrowthRatio(value + "%");
//                }
//
//            } else {//还没有生成-未知，所以跳过
//                vo.setManageFund(null);
//                vo.setRingGrowthRatio("暂无数据");
//                continue;
//            }
//        }
//        return list;
//    }
//
//    private List<WeekBaseVo> mergeData(List<WeekBaseVo> sum, List<WeekBaseVo> per) {
//        List<WeekBaseVo> adminWeekVos = new ArrayList<>();
//
//        for (int j = 0; j < sum.size(); j++) {
//            WeekBaseVo vo = new WeekBaseVo();
//            vo.setDeptName(sum.get(j).getDeptName());
//            vo.setName(sum.get(j).getName());
//            vo.setOpenAccountNum(sum.get(j).getOpenAccountNum());
//            vo.setInCash(sum.get(j).getInCash());
//
//            for (int i = 0 + 5 * j; i < 5 + 5 * j; i++) {
//                switch ((i + 5) % 5) {
//                    case 0:
//                        vo.setMonOpen(per.get(i).getOpenAccountNum());
//                        vo.setMonInCash(per.get(i).getInCash());
//                        break;
//                    case 1:
//                        vo.setTueOpen(per.get(i).getOpenAccountNum());
//                        vo.setTueInCash(per.get(i).getInCash());
//                        break;
//                    case 2:
//                        vo.setWedOpen(per.get(i).getOpenAccountNum());
//                        vo.setWedInCash(per.get(i).getInCash());
//                        break;
//                    case 3:
//                        vo.setThuOpen(per.get(i).getOpenAccountNum());
//                        vo.setThuInCash(per.get(i).getInCash());
//                        break;
//                    case 4:
//                        vo.setFriOpen(per.get(i).getOpenAccountNum());
//                        vo.setFriInCash(per.get(i).getInCash());
//                        break;
//                }
//
//            }
//            adminWeekVos.add(vo);
//        }
//        return adminWeekVos;
//
//    }

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

            if (sum.getManageFund() == null) {
                if (item.getManageFund() == null) sum.setManageFund(BigDecimal.ZERO);
                else
                    sum.setManageFund(BigDecimal.ZERO.add(item.getManageFund()));//管理资金规模
            } else {
                if (item.getManageFund() != null)
                    sum.setManageFund(sum.getManageFund().add(item.getManageFund()));//管理资金规模
            }

            if (sum.getGrowthFund() == null) {
                sum.setGrowthFund(BigDecimal.ZERO.add(item.getGrowthFund()));//管理资金规模净增
            } else {
                sum.setGrowthFund(sum.getGrowthFund().add(item.getGrowthFund()));//管理资金规模净增
            }

            if (sum.getMakerFund() == null) {
                sum.setMakerFund(BigDecimal.ZERO.add(item.getMakerFund()));//创客管理资金规模
            } else {
                sum.setMakerFund(sum.getMakerFund().add(item.getMakerFund()));//创客管理资金规模
            }


        }
        sum.setName("团队总计");
        items.add(sum);
        return items;
    }
}