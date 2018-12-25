package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
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
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "查询跟进人列表")
    public Page<FollowUpListVo> findPage(Page<FollowUpListVo> page, String deptId, String mobile, String queryDeptId, String inviteCode) {
        List<String> followIds = new ArrayList<String>();
        List<String> subDeptIds;
        if (StringUtils.isBlank(queryDeptId)) subDeptIds = deptService.getSubDeptIds(deptId);
        else subDeptIds = deptService.getSubDeptIds(queryDeptId);

        for (String subDeptId : subDeptIds) {
            followIds.addAll(followUpMapper.findIdsByDeptId(subDeptId));
        }

        if (followIds.isEmpty()) return page;

        page = followUpMapper.findFollowUps(page, mobile, followIds, inviteCode, dayiDataBaseStr);

        for (FollowUpListVo vo : page.getResults()) {
            List<Organization> orgs = followOrgMapper.findOrgsByfollowId(vo.getId(), null, dayiDataBaseStr);
            vo.setOrgNum(orgs.size());

            BigDecimal orgFund = countService.getOrgManageFund(orgs);
            vo.setOrgFund(orgFund);
        }

        return page;
    }

    @Override
    public Page<FollowUpListVo> findAssignSelect(Page<FollowUpListVo> page, String followUp, String deptId) {
        List<String> followIds = new ArrayList<>();

        List<String> subDeptIds = deptService.getSubDeptIds(deptId);
        subDeptIds.add(deptId);

        for (String subDeptId : subDeptIds) {
            followIds.addAll(this.findIdsByDeptId(subDeptId));
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
    public Page<FollowUpListVo> findTeamAssignSelect(Page<FollowUpListVo> page, String followUp, String deptId) {
        List<String> followIds = new ArrayList<>();

        followIds = followUpMapper.findIdsByDeptId(deptId);

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
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "查询代理商明细列表")
    public Page<FMDetailListVo> findAgentPage(Page page, SearchVo searchVo, String followId) {
        if (StringUtils.isBlank(followId)) return page;

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        page = followUpMapper.findAgents(page, searchVo, followIds, dayiDataBaseStr);

        page.setResults(doAgentMore(page.getResults()));

        return page;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "导出代理商明细列表")
    public List<FMDetailListVo> findAgentList(SearchVo searchVo, String followId) {
        List<FMDetailListVo> agentList = new ArrayList<>();
        if (StringUtils.isBlank(followId)) return agentList;

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        agentList = followUpMapper.findAgentList(searchVo, followIds, dayiDataBaseStr);

        return doAgentMore(agentList);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "导出创客明细列表")
    public List<FMDetailListVo> findOrgList(SearchVo searchVo, String followId) {
        List<FMDetailListVo> orgList = new ArrayList<>();
        if (StringUtils.isBlank(followId)) return orgList;

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        orgList = followUpMapper.findOrgList(searchVo, followIds, dayiDataBaseStr);

        return doOrgMore(orgList);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "查询全部代理商明细列表")
    public Page<FMDetailListVo> findAllAgentPage(Page page, SearchVo searchVo, String deptId) {
        List<String> followIds = new ArrayList<>();

        List<String> subDeptIds = deptService.getSubDeptIds(deptId);
        for (String subDeptId : subDeptIds) {
            followIds.addAll(followUpMapper.findIdsByDeptId(subDeptId));
        }

        if (followIds.isEmpty()) return page;

        page = followUpMapper.findAgents(page, searchVo, followIds, dayiDataBaseStr);

        page.setResults(doAgentMore(page.getResults()));

        return page;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "导出全部代理商明细列表")
    public List<FMDetailListVo> findAllAgentList(SearchVo searchVo, String deptId) {
        List<FMDetailListVo> agentList = new ArrayList<>();

        List<String> subDeptIds = deptService.getSubDeptIds(deptId);
        List<String> followIds = new ArrayList<>();
        for (String subDeptId : subDeptIds) {
            followIds.addAll(followUpMapper.findIdsByDeptId(subDeptId));
        }

        if (followIds.isEmpty()) return agentList;

        agentList = followUpMapper.findAgentList(searchVo, followIds, dayiDataBaseStr);

        return doAgentMore(agentList);
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
    public Page<FMDetailListVo> findAllOrgPage(Page page, SearchVo searchVo, String deptId) {
        List<String> subDeptIds;
        List<String> followIds = new ArrayList<>();

        if (StringUtils.isBlank(deptId)) return page;
        else subDeptIds = deptService.getSubDeptIds(deptId);

        for (String subDeptId : subDeptIds) {
            followIds.addAll(followUpMapper.findIdsByDeptId(subDeptId));
        }

        if (followIds.isEmpty()) return page;

        page = followUpMapper.findOrgs(page, searchVo, followIds, dayiDataBaseStr);

        page.setResults(doOrgMore(page.getResults()));

        return page;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "跟进人管理", note = "导出全部创客明细列表")
    public List<FMDetailListVo> findAllOrgList(SearchVo searchVo, String deptId) {
        List<FMDetailListVo> orgList = new ArrayList<>();

        List<String> subDeptIds = deptService.getSubDeptIds(deptId);
        List<String> followIds = new ArrayList<>();
        for (String subDeptId : subDeptIds) {
            followIds.addAll(followUpMapper.findIdsByDeptId(subDeptId));
        }

        if (followIds.isEmpty()) return orgList;

        orgList = followUpMapper.findOrgList(searchVo, followIds, dayiDataBaseStr);

        return doOrgMore(orgList);
    }

    @Override
    public List<FollowUp> findAll() {
        return followUpMapper.findAll();
    }

    private List<FMDetailListVo> doAgentMore(List<FMDetailListVo> agents) {
        for (FMDetailListVo agent : agents) {
            agent.setAgentCargo(agentMapper.getAgentFund(agent.getId()));//代理资金

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

            agent.setGrowthCargo(agent.getAgentCargo().subtract(agent.getAgentCargoBefore()));//净增货值
            agent.setGrowthFund(agent.getTotalFund().subtract(agent.getTotalFundBefore()));//净增资金
        }
        return agents;
    }

    private List<FMDetailListVo> doOrgMore(List<FMDetailListVo> orgs) {
        for (FMDetailListVo org : orgs) {
            Organization organization = orgMapper.get(org.getId());

            BigDecimal oneLevel = orgMapper.getManageFundLevel1(org.getId());//一级代理商资产

            BigDecimal twoLevel = BigDecimal.ZERO;//二级代理商资产
            Integer switchStatus = organization.getSwitchStatus();
            if (switchStatus != null && switchStatus.equals(SwitchStatusEnum.OPEN.getKey().intValue())) {//开了二级收益开关
                twoLevel = orgMapper.getManageFundLevel2(org.getId());
            }
            org.setAgentCargo(oneLevel.add(twoLevel));//代理资金

            org.setGrowthCargo(org.getAgentCargo().subtract(org.getAgentCargoBefore()));//净增货值
        }
        return orgs;
    }

}

