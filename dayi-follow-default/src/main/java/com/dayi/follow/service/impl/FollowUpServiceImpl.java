package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.common.util.BizResult;
import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.dao.follow.*;
import com.dayi.follow.enums.SwitchStatusEnum;
import com.dayi.follow.model.follow.*;
import com.dayi.follow.service.*;
import com.dayi.follow.util.CollectionUtil;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.followup.FMDetailListVo;
import com.dayi.follow.vo.followup.FollowUpListVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 跟进人 业务实现类
 */
@Service
public class FollowUpServiceImpl implements FollowUpService {
    @Resource
    private FollowUpMapper followUpMapper;
    @Resource
    private FollowAgentMapper followAgentMapper;
    @Resource
    private CountMapper countMapper;
    @Resource
    private OrgMapper orgMapper;
    @Resource
    private FollowOrgMapper followOrgMapper;
    @Resource
    private AgentMapper agentMapper;
    @Resource
    private CountService countService;
    @Resource
    private DeptService deptService;
    @Resource
    private OrgService orgService;
    @Resource
    private FollowUpLogMapper followUpLogMapper;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;

    @Override
    public FollowUp get(String followUpId) {
        return followUpMapper.get(followUpId);
    }


    @Override
    public List<String> findIdsByDeptId(String deptId) {
        return followUpMapper.findIdsByDeptId(deptId);
    }

    @Override
    public Page<FollowUpListVo> findPage(Page<FollowUpListVo> page, String name, String queryDeptId, String inviteCode) {
        List<String> subDeptIds;

        if (!StringUtils.isBlank(queryDeptId)) {
            subDeptIds = deptService.getSubDeptIds(queryDeptId);
            subDeptIds.add(queryDeptId);
            page = followUpMapper.findFollowUps(page, name, subDeptIds, inviteCode, dayiDataBaseStr);
        } else {
            page = followUpMapper.findFollowUps(page, name, null, inviteCode, dayiDataBaseStr);
        }

        for (FollowUpListVo vo : page.getResults()) {
            List<Organization> orgs = followOrgMapper.findOrgsByfollowId(vo.getId(), null, dayiDataBaseStr);
            vo.setOrgNum(orgs.size());

            BigDecimal orgFund = countService.getOrgManageFund(orgs);
            vo.setOrgFund(orgFund);

            //判断最后一天的日报生成没有，生成了就进行比较

            String dateStart = DateTime.now().dayOfMonth().withMaximumValue().millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
            String dateEnd = DateTime.now().dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

            FollowUpLog log = followUpLogMapper.getLog(vo.getId(), dateStart, dateEnd);
            if (log != null && log.getManageFund() != null) {
                FollowUp followUp = followUpMapper.get(vo.getId());
                if (followUp != null && followUp.getHisMaxFund() != null && log.getManageFund().compareTo(followUp.getHisMaxFund()) == 1) {
                    vo.setHisMaxFund(log.getManageFund());
                    followUp.setHisMaxFund(log.getManageFund());
                    followUpMapper.updateAll(followUp);
                }
            }
        }
        return page;
    }

    @Override
    public Page<FollowUpListVo> findAssignSelect(Page<FollowUpListVo> page, String followUp) {

        page = followUpMapper.findAllAssignSelect(page, followUp);

        for (FollowUpListVo vo : page.getResults()) {
            int agentNum = followAgentMapper.getAgentNum(vo.getId(), dayiDataBaseStr);
            int orgNum = followAgentMapper.getOrgNum(vo.getId(), dayiDataBaseStr);
            vo.setCusNum(agentNum + orgNum);
        }
        return page;
    }

    @Override
    public Page<FollowUpListVo> findTeamAssignSelect(Page<FollowUpListVo> page, String followUp, String deptId, String excludeId) {
        List<String> followIds = new ArrayList<>();

        List<String> subDeptIds = deptService.getSubDeptIds(deptId);
        subDeptIds.add(deptId);
        for (String subDeptId : subDeptIds) {
            followIds.addAll(followUpMapper.findIdsByDeptId(subDeptId));
        }

        if (!StringUtils.isBlank(excludeId)) {
            followIds.remove(excludeId);
        }

        if (followIds.isEmpty()) return page;

        page = followUpMapper.findAssignSelect(page, followUp, followIds);

        for (FollowUpListVo vo : page.getResults()) {
            int agentNum = followAgentMapper.getAgentNum(vo.getId(), dayiDataBaseStr);
            int orgNum = followAgentMapper.getOrgNum(vo.getId(), dayiDataBaseStr);
            vo.setCusNum(agentNum + orgNum);
        }
        return page;
    }

    @Override
    public Page<FMDetailListVo> findAgentPage(Page page, SearchVo searchVo, String followId) {
        if (StringUtils.isBlank(followId)) return page;

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        return followUpMapper.findAgents(page, searchVo, followIds, dayiDataBaseStr);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "导出代理商明细列表")
    public List<FMDetailListVo> findAgentList(SearchVo searchVo, String followId) {
        List<FMDetailListVo> agentList = new ArrayList<>();
        if (StringUtils.isBlank(followId)) return agentList;

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        return followUpMapper.findAgents(searchVo, followIds, dayiDataBaseStr);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "导出创客明细列表")
    public List<FMDetailListVo> findOrgList(SearchVo searchVo, String followId) {
        List<FMDetailListVo> orgList = new ArrayList<>();
        if (StringUtils.isBlank(followId)) return orgList;

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        orgList = followUpMapper.findOrgs(searchVo, followIds, dayiDataBaseStr);

        return doOrgMore(orgList);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "查询全部代理商明细列表")
    public Page<FMDetailListVo> findAllAgentPage(Page page, SearchVo searchVo) {

        return followUpMapper.findAgents(page, searchVo, null, dayiDataBaseStr);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "导出全部代理商明细列表")
    public List<FMDetailListVo> findAllAgentList(SearchVo searchVo) {
        return followUpMapper.findAgents(searchVo, null, dayiDataBaseStr);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "查询创客明细列表")
    public Page<FMDetailListVo> findOrgPage(Page page, SearchVo searchVo, String followId) {
        if (StringUtils.isBlank(followId)) return page;

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        page = followUpMapper.findOrgs(page, searchVo, followIds, dayiDataBaseStr);

        page.setResults(doOrgMore(page.getResults()));

        return page;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "查询全部创客明细列表")
    public Page<FMDetailListVo> findAllOrgPage(Page page, SearchVo searchVo) {

        page = followUpMapper.findOrgs(page, searchVo, null, dayiDataBaseStr);

        page.setResults(doOrgMore(page.getResults()));

        return page;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "导出全部创客明细列表")
    public List<FMDetailListVo> findAllOrgList(SearchVo searchVo) {
        List<FMDetailListVo> orgList = followUpMapper.findOrgs(searchVo, null, dayiDataBaseStr);

        return doOrgMore(orgList);
    }

    @Override
    public List<FollowUp> findAll() {
        return followUpMapper.findAll();
    }

    private List<FMDetailListVo> doAgentMore(List<FMDetailListVo> agents) {
        for (FMDetailListVo agent : agents) {
            //agent.setAgentCargo(agentMapper.getAgentFund(agent.getId()));//代理资金

            if (agent == null) continue;

            Account account = agentMapper.getAccount(agent.getId());
            if (account != null) {
                double totalFund = account.getCargoInterest().multiply(BigDecimal.valueOf(0.8))//总资金
                        .add(account.getCargoInterestPuchas())
                        .add(account.getFrozen())
                        .add(account.getOutFrozen())
                        .add(account.getUseable()).doubleValue();
                agent.setTotalFund(BigDecimal.valueOf(totalFund));
                agent.setInterest(account.getInterest());//利息-服务费
            }

            //  agent.setGrowthCargo(agent.getAgentCargo().subtract(agent.getAgentCargoBefore()));//净增货值
            //  agent.setGrowthFund(agent.getTotalFund().subtract(agent.getTotalFundBefore()));//净增资金
        }
        return agents;
    }

    private List<FMDetailListVo> doOrgMore(List<FMDetailListVo> orgs) {
        for (FMDetailListVo org : orgs) {
            Organization organization = orgMapper.get(org.getId());

            if (organization == null) continue;

            BigDecimal oneLevel = orgMapper.getManageFundLevel1(org.getId());//一级代理商资产

            BigDecimal twoLevel = BigDecimal.ZERO;//二级代理商资产
            Integer switchStatus = organization.getSecondIncomeSwitch();
            if (switchStatus != null && switchStatus.equals(SwitchStatusEnum.OPEN.getKey().intValue())) {//开了二级收益开关
                twoLevel = orgMapper.getManageFundLevel2(org.getId());
            }
            org.setAgentCargo(oneLevel.add(twoLevel));//代理资金

            org.setGrowthCargo(org.getAgentCargo().subtract(org.getAgentCargoBefore()));//净增货值
        }
        return orgs;
    }

    @Override
    public BizResult updateTotalFundBefore(Integer agentId, BigDecimal newValue) {
        FollowAgent followAgent = followAgentMapper.getFollowAgentByAgentId(agentId);
        if (followAgent == null) return BizResult.FAIL;

        if (newValue.compareTo(BigDecimal.ZERO) == 1) {
            followAgent.setTotalFundBefore(newValue);
            followAgentMapper.updateAll(followAgent);
            return BizResult.SUCCESS;
        }
        return BizResult.fail("输入数值必须大于或等于0！");
    }

    @Override
    public BizResult updateHisMaxFund(String followId, BigDecimal newValue) {
        FollowUp followUp = followUpMapper.get(followId);

        if (followUp == null) return BizResult.FAIL;

        if ((followUp.getHisMaxFund() == null && newValue.doubleValue() > 0) || (newValue.compareTo(followUp.getHisMaxFund()) == 1)) {
            followUp.setHisMaxFund(newValue);
            followUpMapper.updateAll(followUp);
            return BizResult.SUCCESS;
        }
        return BizResult.fail("输入数值必须大于当前值！");
    }

    @Override
    public BizResult getManageFund(SearchVo searchVo, String followId) {
        BigDecimal manageFund = followUpMapper.getManageFund(searchVo, followId, dayiDataBaseStr);
        return BizResult.succ(manageFund);
    }


}

