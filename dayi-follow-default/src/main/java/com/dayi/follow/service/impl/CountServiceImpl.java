package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.dao.follow.*;
import com.dayi.follow.enums.OrgTypeEnum;
import com.dayi.follow.enums.SwitchStatusEnum;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.FollowUpLog;
import com.dayi.follow.model.follow.Organization;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.vo.index.*;
import com.dayi.follow.vo.org.OrgDataVo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private OrgService orgService;
    @Value("${follow.dataBase}")
    String followDataBaseStr;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;


    @Override
    public long getAgentNumWait2Link(String followId, String dateStr) {
        return countMapper.getAgentNumWait2Link(followId, dateStr, followDataBaseStr);
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

        statusVo.setCusNum(countMapper.getCusNum(followIds, followDataBaseStr));// 跟进用户总数
        statusVo.setHadLinkNum(countMapper.getLinkCusNum(followIds, followDataBaseStr));   // 已联系--用户人数
        statusVo.setHadRealNameNum(countMapper.getNameCusNum(followIds, followDataBaseStr));// 已实名认证--用户人数
        statusVo.setHadSignNum(countMapper.getCardCusNum(followIds, followDataBaseStr));// 已绑卡--用户人数
        statusVo.setHadInCashNum(countMapper.getInCashCusNum(followIds, followDataBaseStr));// 已入金--用户人数
        statusVo.setHadAgentNum(countMapper.getAgentCusNum(followIds, followDataBaseStr));// 已代理--用户人数
        statusVo.setNoFundNum(countMapper.getNoFundCusNum(followIds, followDataBaseStr));// 总资产为零--用户人数
        statusVo.setHadLostNum(countMapper.getLostCusNum(followIds, followDataBaseStr)); //流失客户

        return statusVo;
    }

    public AdminCusStatusVo countSerCusStatus(List<String> deptIds) {
        AdminCusStatusVo serCusStatusVo = new AdminCusStatusVo();

        if (deptIds.isEmpty()) return serCusStatusVo;

        //跟进人数量
        List<FollowUp> followUps = followUpMapper.findByDeptIds(deptIds);
        serCusStatusVo.setFollowUpNum(followUps.size());

        //待分配客户数量
        long waitAgentNum = countMapper.getWaitAssignAgentNum(followDataBaseStr);//待分配代理商数量
        long waitOrgNum = countMapper.getWaitAssignOrgNum(OrgTypeEnum.Maker.getValue(), followDataBaseStr);//待分配创客数量
        serCusStatusVo.setWaitAssignNum(waitAgentNum + waitOrgNum);

        //跟进用户总数
        List<String> followIds = followUpMapper.findIdsByDeptIds(deptIds);
        long followCusNum = countMapper.getFollowCusNum(followIds, followDataBaseStr);
        serCusStatusVo.setFollowCusNum(followCusNum);

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
        double agentTotalFund = followAgentMapper.getTotalFund(followIds, dayiDataBaseStr);
        double orgTotalFund = followOrgMapper.getTotalFund(followIds, dayiDataBaseStr);
        serCusStatusVo.setTotalFund(BigDecimals.add(agentTotalFund, orgTotalFund));

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

        double manageFund = this.getOrgManageFund(yesOrgVos);

        orgDataVo.setManageFund(BigDecimal.valueOf(manageFund).setScale(2, BigDecimal.ROUND_HALF_UP));
        return orgDataVo;
    }

    @Override
    public OrgDataVo countTeamOrgData(String deptId) {
        OrgDataVo orgDataVo = new OrgDataVo();
        if (StringUtils.isBlank(deptId)) return orgDataVo;

        Department department = deptService.get(deptId);
        if (department == null) return orgDataVo;

        List<String> deptIds = deptService.getSubDeptIds(deptId);//下级部门id
        deptIds.add(deptId);//加上自己

        List<String> followIds = followUpMapper.findIdsByDeptIds(deptIds);

        List<Organization> orgVos = followOrgMapper.findOrgsByfollowIds(followIds, null, dayiDataBaseStr);
        orgDataVo.setOrgNum(orgVos.size());

        int agentNum = this.getValidAgentNum(orgVos);
        orgDataVo.setAgentNum(agentNum);

        DateTime dateTime = DateTime.now();
        String deadline = dateTime.millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        List<Organization> yesOrgVos = followOrgMapper.findOrgsByfollowIds(followIds, deadline, dayiDataBaseStr);//截至昨天的创客

        double manageFund = this.getOrgManageFund(yesOrgVos);

        orgDataVo.setManageFund(BigDecimal.valueOf(manageFund).setScale(2, BigDecimal.ROUND_HALF_UP));
        return orgDataVo;
    }

    @Override
    public int getValidAgentNum(List<Organization> orgs) {
        int num = 0;
        int agentNum = 0;
        for (Organization orgVo : orgs) {
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
    public double getOrgManageFund(List<Organization> orgs) {
        double manageFund = 0;//全部机构商资产
        double orgManageFund = 0;//单个机构商的管理资产
        for (Organization orgVo : orgs) {
            double oneLevel = orgMapper.getManageFundLevel1(orgVo.getId());//一级代理商资产

            double twoLevel = 0;//二级代理商资产
            Integer switchStatus = orgVo.getSwitchStatus();
            if (switchStatus != null && switchStatus.equals(SwitchStatusEnum.OPEN.getKey().intValue())) {//开了二级收益开关
                twoLevel = orgMapper.getManageFundLevel2(orgVo.getId());
            }

            orgManageFund = BigDecimals.add(oneLevel, twoLevel);
            manageFund = BigDecimals.add(manageFund, orgManageFund);
        }
        return manageFund;
    }

    @Override
    public DailyVo countTeamDaily(String deptId) {

        List<String> deptIds = deptService.getSubDeptIds(deptId);//下级部门id
        deptIds.add(deptId);//加上自己
        //获取管辖部门（团队）的所有日报，包括KA
        return countMapper.countTeamDaily(deptIds, followDataBaseStr);
    }

    @Override
    public boolean countFollowUpLog() {
        List<FollowUp> followUps = followUpMapper.findAll();
        for (FollowUp followUp : followUps) {
            //统计昨天23:30:01到今天23:30:00的数据
            String stratTime = DateTime.now().plusDays(-1).millisOfDay().withMaximumValue()
                    .plusMinutes(-30).plusSeconds(2).toString("yyyy-MM-dd HH:mm:ss");

            String endTime = DateTime.now().plusDays(1).millisOfDay().withMinimumValue()
                    .plusMinutes(-30).toString("yyyy-MM-dd HH:mm:ss");

            DailyVo dailyVo = countMapper.countFollowUpLog(followUp.getId(), stratTime, endTime);

            FollowUpLog followUpLog = new FollowUpLog();
            followUpLog.setFollowId(followUp.getId());
            followUpLog.setDeptId(followUp.getDeptId());
            followUpLog.setCreateTime(new Date());
            followUpLog.setUpdateTime(new Date());
            followUpLog.setOpenAccountNum(dailyVo.getOpenNum());
            followUpLog.setInCash(dailyVo.getInCash());
            followUpLog.setInCashNum(dailyVo.getInCashNum());
            followUpLog.setOutCash(dailyVo.getOutCash());
            followUpLog.setOutCashNum(dailyVo.getOutCashNum());

            endTime = DateTime.now().plusDays(1).millisOfDay().withMinimumValue()
                    .plusMinutes(-30).toString("yyyy-MM-dd HH:mm:ss");

            List<Organization> orgs = followOrgMapper.findOrgsByfollowId(followUp.getId(), endTime, dayiDataBaseStr);

            double manageFund = this.getOrgManageFund(orgs);
            followUpLog.setManageFund(BigDecimal.valueOf(manageFund));

            stratTime = DateTime.now().plusDays(-2).millisOfDay().withMaximumValue()
                    .plusMinutes(-30).plusSeconds(2).toString("yyyy-MM-dd HH:mm:ss");

            endTime = DateTime.now().millisOfDay().withMinimumValue().plusMinutes(-30)
                    .toString("yyyy-MM-dd HH:mm:ss");

            FollowUpLog log = followUpLogMapper.getLog(followUp.getId(), stratTime, endTime);

            if (log == null) {
                followUpLog.setManageGrowthFund(BigDecimal.valueOf(manageFund));
            } else {
                followUpLog.setManageGrowthFund(BigDecimal.valueOf(manageFund).subtract(log.getManageFund()));
            }
            followUpLogMapper.add(followUpLog);
        }
        return true;
    }

    @Override
    public DailyVo countDaily(String followId) {
        return countMapper.countDaily(followId, followDataBaseStr);
    }


}

