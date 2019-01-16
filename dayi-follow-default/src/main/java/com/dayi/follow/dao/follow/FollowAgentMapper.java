package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.AgentContact;
import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.vo.agent.AgentContactVo;
import com.dayi.follow.vo.agent.AgentListVo;
import com.dayi.follow.vo.agent.AssignListVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.followup.FollowUpListVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowAgentMapper extends BaseMapper<FollowAgent> {
    //获取待联系代理商的id集合
    List<Integer> getWaitLinkAgentIds(@Param("followId") String followId);

    //查找代理商最近的一条联系记录
    AgentContact findLastContact(@Param("agentId") Integer agentId);

    //查找代理商列表
    Page<AgentListVo> findAgents(Page page, @Param("searchVo") SearchVo searchVo, @Param("ids") List<Integer> ids,
                                 @Param("startStr") String startStr, @Param("endStr") String endStr,
                                 @Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    Page<AgentListVo> findTeamAgents(Page page, @Param("searchVo") SearchVo searchVo, @Param("startStr") String startStr,
                                     @Param("endStr") String endStr, @Param("followIds") List<String> followIds,
                                     @Param("assistDataBase") String assistDataBase);

    //统计资产规模
    BigDecimal getTotalFund(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //获取代理商的跟进人id
    String getFollowIdByAgentId(@Param("agentId") Integer agentId);

    //获取联系记录
    Page<AgentContactVo> findContacts(Page page, @Param("agentId") Integer agentId);

    //获取分配
    Date getAssignDate(@Param("agentId") Integer agentId);

    //获取follow_agent
    FollowAgent getFollowAgentByAgentId(@Param("agentId") Integer agentId);

    //查找已分配跟进人的代理商
    Page<AssignListVo> findAgentsAssign(Page page, @Param("searchVo") SearchVo searchVo, @Param("assistDataBase") String assistDataBase);

    //查找已分配跟进人的代理商
    List<AssignListVo> findAgentsAssign(@Param("searchVo") SearchVo searchVo, @Param("assistDataBase") String assistDataBase);

    //查找跟进人的全部代理商
    List<Agent> findAgentsByFollowId(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //获取跟进代理商数量
    int getAgentNum(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //获取跟进创客数量
    int getOrgNum(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //获取入金
    BigDecimal getInCash(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("assistDataBase") String assistDataBase);

    //获取入金人数
    int getInCashNum(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("assistDataBase") String assistDataBase);

    //获取出金
    BigDecimal getOutCash(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("assistDataBase") String assistDataBase);

    //获取出金人数
    int getOutCashNum(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("assistDataBase") String assistDataBase);

    //获取开户数
    int getOpenAccountNum(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("assistDataBase") String assistDataBase);

    BigDecimal getAgentFund(@Param("followId") String followId, @Param("deadline") String deadline, @Param("assistDataBase") String assistDataBase);
}
