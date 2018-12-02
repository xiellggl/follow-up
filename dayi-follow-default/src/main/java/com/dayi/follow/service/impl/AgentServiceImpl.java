package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.follow.AgentContactMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.AgentContact;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.vo.*;
import com.dayi.follow.vo.agent.AgentListVo;
import com.dayi.mybatis.support.Page;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 跟进人 业务实现类
 */
@Service
public class AgentServiceImpl implements AgentService {
    @Resource
    private FollowUpMapper followUpMapper;
    @Resource
    private OrgService orgService;
    @Resource
    private DeptService deptService;
    @Resource
    private FollowAgentMapper followAgentMapper;
    @Resource
    private AgentMapper agentMapper;
    @Resource
    private AgentContactMapper agentContactMapper;
    @Resource
    private UserComponent userComponent;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;

    @Override
    public Page<AgentListVo> findAgentPage(Page<AgentListVo> page, SearchVo searchVo, String followId) {

        List<Integer> ids = null;
        if (searchVo.getWaitToLinkToday() != null) {
            ids = followAgentMapper.getWaitLinkAgentIds(followId);
        }

        DateTime dateTime = new DateTime();
        String startStr = dateTime.millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endStr = dateTime.millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        List<AgentListVo> agents = followAgentMapper.findAgents(searchVo,
                ids, startStr, endStr, followId, dayiDataBaseStr,
                page.getStartRow(), page.getPageSize());

        Integer totalCount = followAgentMapper.findAgentsCount(searchVo, ids, startStr, endStr, followId, dayiDataBaseStr);

        page.setResults(this.queryByList(agents));
        page.setTotalRecord(totalCount);

        return page;
    }

    @Override
    public Page<AgentListVo> findTeamAgentPage(Page<AgentListVo> page, SearchVo searchVo, String followId, String deptId) {

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);
        followIds.remove(followId);//过滤自己


        List<Integer> ids = null;
        if (searchVo.getWaitToLinkToday() != null) {
            ids = followAgentMapper.getWaitLinkAgentIds(followId);
        }

        DateTime dateTime = new DateTime();
        String startStr = dateTime.millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endStr = dateTime.millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        List<AgentListVo> agents = followAgentMapper.findTeamAgents(searchVo,
                ids, startStr, endStr, followIds, dayiDataBaseStr,
                page.getStartRow(), page.getPageSize());

        Integer totalCount = followAgentMapper.findTeamAgentsCount(searchVo, ids, startStr, endStr, followIds, dayiDataBaseStr);

        page.setResults(this.queryByList(agents));
        page.setTotalRecord(totalCount);

        return page;
    }

    @Override
    public Agent get(Integer agentId) {
        return agentMapper.get(agentId);
    }

    @Override
    public Page<LoginLogVo> findLoginLog(Page page, Integer agentId) {
        return agentMapper.findLoginLog(agentId, page.getStartRow(), page.getEndRow());
    }

    @Override
    public BizResult addContact(AgentContact agentContact) {
        return 1 == agentContactMapper.add(agentContact) ? BizResult.succ(agentContact) : BizResult.FAIL;
    }

    private List<AgentListVo> queryByList(List<AgentListVo> agents) {
        for (AgentListVo vo : agents) {
            Date lastLoginTime = agentMapper.findLastLoginTime(vo.getId());
            vo.setLastLoginDate(lastLoginTime);//最后登录时间

            AgentContact lastContact = followAgentMapper.findLastContact(vo.getId());//最新一条联系记录
            if (lastContact != null) {
                vo.setContactContent(lastContact.getContent());
                vo.setContactDate(lastContact.getCreateTime());
            }

            AgentListVo lastVo = agentMapper.countRecentAgent(vo.getId());//获取最近代理金额，和日期
            if (lastVo != null) {
                vo.setRecentAgentDate(lastVo.getRecentAgentDate());
                vo.setRecentAgentFund(lastVo.getRecentAgentFund());
            }
        }
        return agents;
    }


}

