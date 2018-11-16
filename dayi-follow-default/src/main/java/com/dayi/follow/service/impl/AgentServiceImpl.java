package com.dayi.follow.service.impl;


import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.vo.AgentVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public Page<AgentVo> findAgentPage(Page<AgentVo> page, Integer linkStatus, Integer idCardValid, Integer sign,
                                       Integer inCash, Integer totalFund, String mobile, String invitCode,
                                       Integer subDeptId, String followUp, Integer todayInCash,
                                       Integer todayOutCash, Integer customerType, Integer totalBalance,
                                       String bankType, Integer waitToLinkToday, String flowId,
                                       Integer deptId, Integer deptFlowId) {
        String whereSql = this.spellCustomerWhereSql(subDeptId, deptId, deptFlowId);  // 拼写跟进人特殊条件过滤语句
        List<Integer> ids = null;
        if (waitToLinkToday != null && waitToLinkToday != -1) {
            ids = followAgentMapper.getWaitLinkAgentIds(flowId);
        }
        String idsStr = null;
        if (ids != null) {
            String str = ids.toString();
            String replace = str.replace("[", "");
            idsStr = replace.toString().replace("]", "");
        }
//        Page<AgentVo> agentPage = agentDao.getAgentCustomerPage(page, linkStatus, idCardValid, sign, inCash, totalFund,
//                mobile, invitCode, followUp, todayInCash, todayOutCash, customerType, totalBalance,
//                bankType, idsStr, flowId, whereSql);
//        for (AgentVo vo : agentPage.getItems()) {
//            Date lastLoginTime = financeLoginLogDao.findLastLoginTime(vo.getId());
//            vo.setLastLoginDate(lastLoginTime);//最后登录时间
//
//            AgentContact lastContact = agentContactDao.findLastContact(vo.getId());//最新一条联系记录
//            if (lastContact != null) {
//                vo.setContactContent(lastContact.getContent());
//                vo.setContactDate(lastContact.getCreateDate());
//            }
//
//            AgentVo lastVo = agentDao.calLastAgentVo(vo.getId());//获取最近代理金额，和日期
//            vo.setDateStr(lastVo.getDateStr());
//            vo.setAmount(lastVo.getAmount());
//        }
//        return agentPage;
        return null;
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
}

