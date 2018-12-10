package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.dao.follow.*;
import com.dayi.follow.enums.SwitchStatusEnum;
import com.dayi.follow.model.follow.Account;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.Organization;
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
    public Page<FollowUpListVo> findPage(Page page, String deptId, String mobile, String queryDeptId, String inviteCode) {
        List<String> followIds = new ArrayList<String>();

        if (StringUtils.isBlank(queryDeptId)) followIds = followUpMapper.findIdsByDeptId(deptId);
        else followIds = followUpMapper.findIdsByDeptId(queryDeptId);

        List<FollowUpListVo> followUps = followUpMapper.findFollowUps(mobile, followIds, inviteCode, page.getStartRow(), page.getPageSize(), dayiDataBaseStr);

        for (FollowUpListVo vo : followUps) {
            List<Organization> orgs = followOrgMapper.findOrgsByfollowId(vo.getId(), null, dayiDataBaseStr);
            vo.setOrgNum(orgs.size());

            double orgFund = countService.getOrgManageFund(orgs);
            vo.setOrgFund(orgFund);
        }

        int num = followUpMapper.getFollowUpsNum(mobile, followIds, inviteCode);

        page.setResults(followUps);
        page.setTotalRecord(num);
        return page;
    }

    @Override
    public Page<FollowUpListVo> findAssignSelect(Page<FollowUpListVo> page, String followUp, String deptId) {
        List<String> followIds = this.findIdsByDeptId(deptId);
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

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        page = followUpMapper.findAgents(page, searchVo, followIds, dayiDataBaseStr);

        page.setResults(doAgentMore(page.getResults()));

        return page;
    }

    @Override
    public Page<FMDetailListVo> findAllAgentPage(Page page, SearchVo searchVo, String deptId) {

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        page = followUpMapper.findAgents(page, searchVo, followIds, dayiDataBaseStr);

        page.setResults(doAgentMore(page.getResults()));

        return page;
    }

    @Override
    public Page<FMDetailListVo> findOrgPage(Page page, SearchVo searchVo, String followId) {
        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        page = followUpMapper.findOrgs(page, searchVo, followIds, dayiDataBaseStr);

        page.setResults(doOrgMore(page.getResults()));

        return page;
    }

    @Override
    public Page<FMDetailListVo> findAllOrgPage(Page page, SearchVo searchVo, String deptId) {

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);

        page = followUpMapper.findOrgs(page, searchVo, followIds, dayiDataBaseStr);

        page.setResults(doOrgMore(page.getResults()));

        return page;
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
                agent.setTotalFund(totalFund);
            }

            agent.setGrowthCargo(BigDecimals.subtract(agent.getAgentCargo(), agent.getAgentCargoBefore()));//净增货值
            agent.setGrowthFund(BigDecimals.subtract(agent.getTotalFund(), agent.getTotalFundBefore()));//净增资金
        }
        return agents;
    }

    private List<FMDetailListVo> doOrgMore(List<FMDetailListVo> orgs) {
        for (FMDetailListVo org : orgs) {
            Organization organization = orgMapper.get(org.getId());

            double oneLevel = orgMapper.getManageFundLevel1(org.getId());//一级代理商资产

            double twoLevel = 0;//二级代理商资产
            Integer switchStatus = organization.getSwitchStatus();
            if (switchStatus != null && switchStatus.equals(SwitchStatusEnum.OPEN.getKey().intValue())) {//开了二级收益开关
                twoLevel = orgMapper.getManageFundLevel2(org.getId());
            }
            org.setAgentCargo(BigDecimals.add(oneLevel, twoLevel));//代理资金

            org.setGrowthCargo(BigDecimals.subtract(org.getAgentCargo(), org.getAgentCargoBefore()));//净增货值
        }
        return orgs;
    }

}

