package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.common.util.NameItem;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.dao.follow.*;
import com.dayi.follow.enums.OrgTypeEnum;
import com.dayi.follow.enums.SwitchStatusEnum;
import com.dayi.follow.model.follow.*;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.vo.index.*;
import com.dayi.follow.vo.org.OrgDataVo;
import com.dayi.follow.vo.report.ReportVo;
import com.dayi.mybatis.support.Page;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import java.lang.annotation.Retention;
import java.math.BigDecimal;
import java.util.*;

/**
 * 跟进人 业务实现类
 */
@Service
public class CountServiceImpl implements CountService {
    @Resource
    private FollowUpMapper followUpMapper;
    @Resource
    private ReportMapper reportMapper;
    @Resource
    private CountMapper countMapper;
    @Resource
    private AgentMapper agentMapper;
    @Resource
    private FollowAgentMapper followAgentMapper;
    @Resource
    private FollowOrgMapper followOrgMapper;
    @Resource
    private DeptService deptService;
    @Resource
    private OrgMapper orgMapper;
    @Resource
    private FollowUpLogMapper followUpLogMapper;
    @Resource
    private SourceReportMapper sourceReportMapper;
    @Resource
    private OrgService orgService;
    @Value("${follow.dataBase}")
    String followDataBaseStr;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;


    @Override
    public long getAgentNumWait2Link(String followId) {
        String startDate = DateTime.now().millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.now().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        return countMapper.getAgentNumWait2Link(followId, startDate, endDate, followDataBaseStr);
    }

    /* 统计客户类型--跟进人ID */
    @Override
    public List<CusTypeRatioVo> countCusTypeRatio(String followId) {
        return countMapper.countCusTypeRatio(followId, followDataBaseStr);
    }

    @Override
    public List<FundRankVo> countCusFundRank(String followId) {
        return countMapper.countCusFundRank(followId, followDataBaseStr);
    }

    @Override
    public List<SevenOpenVo> countSevenOpen(String followId) {
        return countMapper.countSevenOpen(followId, followDataBaseStr);
    }

    @Override
    public List<SevenInCashVo> countSevenInCash(String followId) {
        return countMapper.countSevenInCash(followId, followDataBaseStr);
    }

    /**
     * 代理商 -- 统计相应跟进人数目--
     */
    public CusStatusVo countCusStatus(String followId) {
        CusStatusVo statusVo = new CusStatusVo();

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        statusVo.setCusNum(countMapper.getCusNum(followIds, followDataBaseStr));// 跟进代理商总数
        statusVo.setHadLinkNum(countMapper.getLinkCusNum(followIds, followDataBaseStr));   // 已联系--用户人数
        statusVo.setHadRealNameNum(countMapper.getNameCusNum(followIds, followDataBaseStr));// 已实名认证--用户人数
        statusVo.setHadSignNum(countMapper.getCardCusNum(followIds, followDataBaseStr));// 已绑卡--用户人数
        statusVo.setHadInCashNum(countMapper.getInCashCusNum(followIds, followDataBaseStr));// 已入金--用户人数
        statusVo.setHadAgentNum(countMapper.getAgentCusNum(followIds, followDataBaseStr));// 已代理--用户人数
        statusVo.setNoFundNum(countMapper.getNoFundCusNum(followIds, followDataBaseStr));// 总资产为零--用户人数
        statusVo.setHadLostNum(countMapper.getLostCusNum(followIds, followDataBaseStr)); //流失客户

        return statusVo;
    }

    public AdminCusStatusVo countSerCusStatus() {
        AdminCusStatusVo serCusStatusVo = new AdminCusStatusVo();

        //跟进人数量
        List<FollowUp> followUps = followUpMapper.findAll();
        serCusStatusVo.setFollowUpNum(followUps.size());

        //待分配客户数量
        long agentNum = countMapper.getNoAssignedAgentNum(followDataBaseStr);

        int orgNum = countMapper.getNoAssignedOrgNum(followDataBaseStr);

        serCusStatusVo.setWaitAssignNum(agentNum + orgNum);

        //跟进用户总数
        List<String> followIds = new ArrayList<>();
        for (FollowUp followUp : followUps) {
            followIds.add(followUp.getId());
        }

        agentNum = countMapper.getFollowAgentNum(followDataBaseStr);
        orgNum = countMapper.getFollowOrgNum(followDataBaseStr);
        serCusStatusVo.setFollowCusNum(agentNum + orgNum);

        //已认证客户数量
        long nameCusNum = countMapper.getNameCusNum(followIds, followDataBaseStr);
        //已绑卡客户数量
        long cardCusNum = countMapper.getCardCusNum(followIds, followDataBaseStr);
        //已代理客户数量
        long agentCusNum = countMapper.getAgentCusNum(followIds, followDataBaseStr);
        serCusStatusVo.setNameNum(nameCusNum);
        serCusStatusVo.setCardNum(cardCusNum);
        serCusStatusVo.setAgentNum(agentCusNum);
        //资产总规模
        BigDecimal agentTotalFund = followAgentMapper.getTotalFund(followIds, dayiDataBaseStr);

        List<Organization> orgs = followOrgMapper.findOrgsByfollowIds(followIds, null, dayiDataBaseStr);

        List<Integer> incomeOpen = new ArrayList<>();

        List<Integer> incomeClose = new ArrayList<>();

        for (Organization orgVo : orgs) {

            if (!orgVo.getOrgType().equals(OrgTypeEnum.Maker.getValue())) continue;//排除非创客

            if (orgVo.getSecondIncomeSwitch() != null && orgVo.getSecondIncomeSwitch().equals(SwitchStatusEnum.OPEN.getKey())) {
                incomeOpen.add(orgVo.getId());
            } else {
                incomeClose.add(orgVo.getId());
            }
        }

        BigDecimal manageFund;
        BigDecimal openFund = BigDecimal.ZERO;
        BigDecimal closeFund = BigDecimal.ZERO;

        if (!incomeOpen.isEmpty()) {
            openFund = orgMapper.getOrgManageFundAll(incomeOpen);
        }

        if (!incomeClose.isEmpty()) {
            closeFund = orgMapper.getOrgManageFundLevel1(incomeClose);
        }

        manageFund = openFund.add(closeFund);

        serCusStatusVo.setTotalFund(agentTotalFund.add(manageFund));

        return serCusStatusVo;
    }

    @Override
    public OrgDataVo countOrgData(String followId) {
        OrgDataVo orgDataVo = new OrgDataVo();
        if (StringUtils.isBlank(followId)) return orgDataVo;

        List<Organization> orgVos = followOrgMapper.findOrgsByfollowId(followId, null, dayiDataBaseStr);
        orgDataVo.setOrgNum(orgVos.size());

        int agentNum = this.getValidAgentNum(orgVos);
        orgDataVo.setAgentNum(agentNum);

        DateTime dateTime = DateTime.now();
        String deadline = dateTime.millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        List<Organization> yesOrgVos = followOrgMapper.findOrgsByfollowId(followId, deadline, dayiDataBaseStr);//截至昨天的创客

        BigDecimal manageFund = this.getOrgManageFund(yesOrgVos);

        orgDataVo.setManageFund(manageFund.setScale(2, BigDecimal.ROUND_HALF_UP));
        return orgDataVo;
    }

    @Override
    public OrgDataVo countTeamOrgData(String deptId) {
        OrgDataVo orgDataVo = new OrgDataVo();
        if (StringUtils.isBlank(deptId)) return orgDataVo;

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        //String deadline = DateTime.now().millisOfDay().withMinimumValue().plusMinutes(-30).toString("yyyy-MM-dd HH:mm:ss");

        List<Organization> orgVos = followOrgMapper.findOrgsByfollowIds(followIds, null, dayiDataBaseStr);
        orgDataVo.setOrgNum(orgVos.size());

        List<Integer> inviteOpen = new ArrayList<>();

        List<Integer> inviteClose = new ArrayList<>();

        List<Integer> incomeOpen = new ArrayList<>();

        List<Integer> incomeClose = new ArrayList<>();

        for (Organization orgVo : orgVos) {
            if (!orgVo.getOrgType().equals(OrgTypeEnum.Maker.getValue())) continue;//排除非创客

            if (orgVo.getSwitchStatus() != null && orgVo.getSwitchStatus().equals(SwitchStatusEnum.OPEN.getKey())) {
                inviteOpen.add(orgVo.getId());
            } else {
                inviteClose.add(orgVo.getId());
            }
            if (orgVo.getSecondIncomeSwitch() != null && orgVo.getSecondIncomeSwitch().equals(SwitchStatusEnum.OPEN.getKey())) {
                incomeOpen.add(orgVo.getId());
            } else {
                incomeClose.add(orgVo.getId());
            }
        }

        int openNum = 0;
        int closeNum = 0;
        if (!inviteOpen.isEmpty()) openNum = countMapper.getValidAgentNumAll(inviteOpen);

        if (!inviteClose.isEmpty()) closeNum = countMapper.getValidAgentNumLevel1(inviteClose);

        orgDataVo.setAgentNum(openNum + closeNum);

        BigDecimal manageFund;
        BigDecimal openFund = BigDecimal.ZERO;
        BigDecimal closeFund = BigDecimal.ZERO;

        if (!incomeOpen.isEmpty()) {
            openFund = orgMapper.getOrgManageFundAll(incomeOpen);
        }

        if (!incomeClose.isEmpty()) {
            closeFund = orgMapper.getOrgManageFundLevel1(incomeClose);
        }

        manageFund = openFund.add(closeFund);

        orgDataVo.setManageFund(manageFund.setScale(2, BigDecimal.ROUND_HALF_UP));
        return orgDataVo;
    }

    @Override
    public int getValidAgentNum(List<Organization> orgs) {
        int num;
        int agentNum = 0;
        for (Organization orgVo : orgs) {

            if (!orgVo.getOrgType().equals(OrgTypeEnum.Maker.getValue())) continue;//目前只算创客

            Integer switchStatus = orgVo.getSwitchStatus();
            if (switchStatus != null && switchStatus.equals(SwitchStatusEnum.OPEN.getKey())) {//二级资管开启
                num = countMapper.getOrgValidAgentNum(orgVo.getId(), null);//包括2级
                agentNum = agentNum + num;
            }
            if (switchStatus != null && switchStatus.equals(SwitchStatusEnum.CLOSE.getKey())) {//二级资管关闭
                num = countMapper.getOrgValidAgentNum(orgVo.getId(), 1);//不包括2级
                agentNum = agentNum + num;
            }
        }
        return agentNum;
    }

    @Override
    public BigDecimal getOrgManageFund(List<Organization> orgs) {
        BigDecimal manageFund = BigDecimal.ZERO;//全部机构商资产
        BigDecimal orgManageFund;//单个机构商的管理资产
        for (Organization orgVo : orgs) {

            if (!orgVo.getOrgType().equals(OrgTypeEnum.Maker.getValue())) continue;//目前只算创客

            BigDecimal oneLevel = orgMapper.getManageFundLevel1(orgVo.getId());//一级代理商资产

            BigDecimal twoLevel = BigDecimal.ZERO;//二级代理商资产
            Integer switchStatus = orgVo.getSecondIncomeSwitch();
            if (switchStatus != null && switchStatus.equals(SwitchStatusEnum.OPEN.getKey().intValue())) {//开了二级收益开关
                twoLevel = orgMapper.getManageFundLevel2(orgVo.getId());
            }

            orgManageFund = oneLevel.add(twoLevel);
            manageFund = manageFund.add(orgManageFund);
        }
        return manageFund;
    }

    @Override
    public DailyVo countTeamDaily(String deptId) {
        List<String> deptIds = new ArrayList<>();
        deptIds.add(deptId);//加上自己
        //获取管辖部门（团队）的所有日报，包括KA
        return countMapper.countTeamDaily(deptIds, followDataBaseStr);
    }

    @Override
    public boolean countFollowUpLog() {
        List<FollowUp> followUps = followUpMapper.findAll();

        //查找昨天23:00:00到今天00:00:00是否存在数据
        String stratTime = DateTime.now().plusDays(-1).withHourOfDay(23).withMinuteOfHour(0)
                .withSecondOfMinute(0).toString("yyyy-MM-dd HH:mm:ss");

        String endTime = DateTime.now().secondOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        Page<FollowUpLog> page = new Page<>();
        page = followUpLogMapper.findLogsByTime(page, stratTime, endTime);

        if (page.getTotalRecord() <= 0) {//昨天不是第一天改时间
            //统计昨天17:30:01到今天17:30:00的数据
            stratTime = DateTime.now().plusDays(-1).withHourOfDay(17)
                    .withMinuteOfHour(30).withSecondOfMinute(1).toString("yyyy-MM-dd HH:mm:ss");

            endTime = DateTime.now().withHourOfDay(17).withMinuteOfHour(30)
                    .withSecondOfMinute(0).toString("yyyy-MM-dd HH:mm:ss");
        } else {
            //统计昨天23:30:01到今天17:30:00的数据
            stratTime = DateTime.now().plusDays(-1).withHourOfDay(23).withMinuteOfHour(30)
                    .withSecondOfMinute(1).toString("yyyy-MM-dd HH:mm:ss");

            endTime = DateTime.now().withHourOfDay(17).withMinuteOfHour(30)
                    .withSecondOfMinute(0).toString("yyyy-MM-dd HH:mm:ss");
        }

        String yesStratTime = DateTime.now().plusDays(-1).millisOfDay().withMinimumValue()
                .toString("yyyy-MM-dd HH:mm:ss");
        String yesEndTime = DateTime.now().plusDays(-1).millisOfDay().withMaximumValue()
                .toString("yyyy-MM-dd HH:mm:ss");


        for (FollowUp followUp : followUps) {
            FollowUpLog followUpLog = new FollowUpLog();

            int openNum = followAgentMapper.getOpenAccountNum(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setOpenAccountNum(openNum);

            BigDecimal inCash = followAgentMapper.getInCash(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setInCash(inCash);

            int inCashNum = followAgentMapper.getInCashNum(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setInCashNum(inCashNum);

            BigDecimal outCash = followAgentMapper.getOutCash(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setOutCash(outCash);

            int outCashNum = followAgentMapper.getOutCashNum(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setOutCashNum(outCashNum);

            //统计新签创客人数
            int orgNum = followOrgMapper.getNewSignOrgNum(followUp.getId(), stratTime, endTime, dayiDataBaseStr);
            followUpLog.setSignOrgNum(orgNum);

            followUpLog.setId(followUpLogMapper.getNewId());
            followUpLog.setFollowId(followUp.getId());
            followUpLog.setDeptId(followUp.getDeptId());
            followUpLog.setCreateTime(new Date());
            followUpLog.setUpdateTime(new Date());

            List<Organization> orgs = followOrgMapper.findOrgsByfollowId(followUp.getId(), endTime, dayiDataBaseStr);

            BigDecimal makerFund = this.getOrgManageFund(orgs);
            followUpLog.setMakerFund(makerFund);

            BigDecimal fund = followUpMapper.getManageFund(null, followUp.getId(), dayiDataBaseStr);
            followUpLog.setManageFund(fund);

            FollowUpLog log = followUpLogMapper.getLog(followUp.getId(), yesStratTime, yesEndTime);

            if (log == null) {
                followUpLog.setMakerGrowthFund(makerFund);
                followUpLog.setManageGrowthFund(fund);
            } else {
                followUpLog.setMakerGrowthFund(makerFund.subtract(log.getMakerFund()));
                if (followUp.getHisMaxFund() != null) {
                    followUpLog.setManageGrowthFund(fund.subtract(followUp.getHisMaxFund()));
                } else {
                    followUpLog.setManageGrowthFund(fund);
                }
            }
            followUpLogMapper.add(followUpLog);
        }

        countSourceReport();

        return true;
    }

    @Override
    public void countSourceReport() {
        String startDate = DateTime.now().plusDays(-1).withHourOfDay(17).withMinuteOfHour(30).withSecondOfMinute(1).toString("yyyy-MM-dd HH:mm:ss");
        String endDate = DateTime.now().withHourOfDay(17).withMinuteOfHour(30).withSecondOfMinute(0).toString("yyyy-MM-dd HH:mm:ss");

        String yesStartDate = DateTime.now().plusMonths(-1).dayOfMonth().withMaximumValue().withHourOfDay(17)
                .withMinuteOfHour(0).withSecondOfMinute(0).toString("yyyy-MM-dd HH:mm:ss");
        String yesEndDate = DateTime.now().plusMonths(-1).dayOfMonth().withMaximumValue().withHourOfDay(18)
                .withMinuteOfHour(0).withSecondOfMinute(0).toString("yyyy-MM-dd HH:mm:ss");

        Iterator<NameItem> iterator = SourceReport.TYPE_ALL.iterator();

        BigDecimal inCash = null;
        BigDecimal outCash = null;
        BigDecimal manageFund = null;
        BigDecimal lastManageFund = null;

        while (iterator.hasNext()) {
            SourceReport sr = new SourceReport();
            NameItem next = iterator.next();
            if (SourceReport.TYPE_ZG.getId() == next.getId()) {//资管中心
                inCash = sourceReportMapper.getFollowUpInCash(startDate, endDate);
                outCash = sourceReportMapper.getFollowUpOutCash(startDate, endDate);
                manageFund = sourceReportMapper.getFollowUpManageFund(startDate, endDate);

                SourceReport preSR = sourceReportMapper.getByTime(SourceReport.TYPE_ZG.getId(), yesStartDate, yesEndDate);
                if (preSR != null) {
                    lastManageFund = preSR.getManageFund();
                }
                sr.setType(SourceReport.TYPE_ZG.getId());
            }
            if (SourceReport.TYPE_MAKER.getId() == next.getId()) {//创客
                inCash = sourceReportMapper.getMakerInCash(startDate, endDate, dayiDataBaseStr);
                outCash = sourceReportMapper.getMakerOutCash(startDate, endDate, dayiDataBaseStr);
                manageFund = sourceReportMapper.getMakerManageFund(startDate, endDate, dayiDataBaseStr);

                SourceReport preSR = sourceReportMapper.getByTime(SourceReport.TYPE_MAKER.getId(), yesStartDate, yesEndDate);
                if (preSR != null) {
                    lastManageFund = preSR.getManageFund();
                }
                sr.setType(SourceReport.TYPE_MAKER.getId());


            }
            if (SourceReport.TYPE_OTHER.getId() == next.getId()) {//其它
                inCash = sourceReportMapper.getOtherInCash(startDate, endDate, dayiDataBaseStr);
                outCash = sourceReportMapper.getOtherOutCash(startDate, endDate, dayiDataBaseStr);
                manageFund = sourceReportMapper.getOtherManageFund(startDate, endDate, dayiDataBaseStr);

                SourceReport preSR = sourceReportMapper.getByTime(SourceReport.TYPE_OTHER.getId(), yesStartDate, yesEndDate);
                if (preSR != null) {
                    lastManageFund = preSR.getManageFund();
                }
                sr.setType(SourceReport.TYPE_OTHER.getId());
            }
            sr.setId(sourceReportMapper.getNewId());
            sr.setInCash(inCash);
            sr.setOutCash(outCash);
            sr.setGrowthFund(inCash.subtract(outCash));
            sr.setManageFund(manageFund);
            if (lastManageFund != null) {
                sr.setManageGrowthFund(manageFund.subtract(lastManageFund));
            } else {
                sr.setManageGrowthFund(manageFund);
            }
            sourceReportMapper.add(sr);
        }
    }

    @Override
    public DailyVo countDaily(String followId) {
        return countMapper.countDaily(followId, followDataBaseStr);
    }


}

