package com.dayi.follow.service.impl;


import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.model.follow.AgentContact;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.IndexVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
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
    @Value("${spring.datasource.dayi.dataBase}")
    String dayiDataBaseStr;

    @Override
    public Page<AgentListVo> findAgentPage(Page<AgentListVo> page, SearchVo searchVo, String followId, Integer deptId, Integer deptFlowId,
                                           Integer subDeptId, String followUpd) {
        String whereSql = this.spellCustomerWhereSql(subDeptId, deptId, deptFlowId);  // 拼写跟进人特殊条件过滤语句
        List<Integer> ids = null;
        if (searchVo.getWaitToLinkToday() != null) {
            ids = followAgentMapper.getWaitLinkAgentIds(followId);
        }

        DateTime dateTime = new DateTime();
        String startStr = dateTime.millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String endStr = dateTime.millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        List<AgentListVo> agents = followAgentMapper.findAgents(searchVo,
                ids, startStr, endStr, followId, whereSql,dayiDataBaseStr,
                (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());

        Integer totalCount = followAgentMapper.findAgentsCount(searchVo, ids, startStr, endStr, followId, whereSql,dayiDataBaseStr);

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
        page.setResults(agents);
        page.setTotalRecord(totalCount);
        return page;
    }


    /* 私有方法：抽取通用拼写跟进人特殊条件过滤语句 */
    private String spellCustomerWhereSql(Integer subDeptId, Integer deptId, Integer deptFlowId) {
        StringBuffer whereSql = new StringBuffer();
        if (deptFlowId != null) { // 下级客户 -- 不包含自己的跟进人
            whereSql.append(" AND a.flow_id != ").append(deptFlowId).append(" ");
        }
        if (deptId != null) { // 下级客户 -- 跟进人列表过滤
            whereSql.append(deptService.spellMyDeptManagerFlowIdsNotInsql(deptId, "flow_id", "a"));  // 排除本部门所有负责人
            String deptSubSql = deptService.spellSubFlowIdsInsql(deptId, null, "flow_id", "a", true);
            if (StringUtils.isNotBlank(deptSubSql)) {  // 所有下级部门跟进人
                whereSql.append(deptSubSql);
            } else {
                whereSql.append(" AND 1 = 0 ");
            }
        }
        if (subDeptId != null && subDeptId != -1) { // 下级客户 -- 页面查询条件 -- 选择部门
            String subWhereSql = deptService.spellSubFlowIdsInsql(subDeptId, null, "flow_id", "a", true);
            if (StringUtils.isNotBlank(subWhereSql)) {
                whereSql.append(subWhereSql);
            } else { // 如果选择条件部门的所有下级都不存在跟进人，则查询无结果
                whereSql.append(" AND 1 = 0 ");
            }
        }
        return whereSql.toString();
    }

    /**
     * 代理商 -- 统计相应跟进人数目--
     */
    public IndexVo countCusStatus(String followId) {
        return agentMapper.countCusStatus(followId);
    }


}

