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

import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowAgentMapper extends BaseMapper<FollowAgent> {

    List<Integer> getWaitLinkAgentIds(@Param("followUpId") String followUpId);

    AgentContact findLastContact(@Param("agentId") Integer agentId);

    List<AgentListVo> findAgents(@Param("searchVo") SearchVo searchVo, @Param("ids") List<Integer> ids,
                                 @Param("startStr") String startStr, @Param("endStr") String endStr,
                                 @Param("followId") String followId, @Param("assistDataBase") String assistDataBase, @Param("limitStart") Integer limitStart,
                                 @Param("limitEnd") Integer limitEnd);

    Integer findAgentsCount(@Param("searchVo") SearchVo searchVo, @Param("ids") List<Integer> ids,
                            @Param("startStr") String startStr, @Param("endStr") String endStr,
                            @Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    List<AgentListVo> findTeamAgents(@Param("searchVo") SearchVo searchVo, @Param("ids") List<Integer> ids,
                                     @Param("startStr") String startStr, @Param("endStr") String endStr,
                                     @Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase,
                                     @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);

    Integer findTeamAgentsCount(@Param("searchVo") SearchVo searchVo, @Param("ids") List<Integer> ids,
                                @Param("startStr") String startStr, @Param("endStr") String endStr,
                                @Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);


    //统计资产规模
    double getTotalFund(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    String getFollowIdByAgentId(@Param("agentId") Integer agentId);

    //获取联系记录
    List<AgentContactVo> findContacts(@Param("agentId") Integer agentId, @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);

    //获取联系记录
    int getContactsNum(@Param("agentId") Integer agentId);

    //获取分配
    Date getAssignDate(@Param("agentId") Integer agentId);

    //获取follow_agent
    FollowAgent getFollowAgentByAgentId(@Param("agentId") Integer agentId);

    //查找已分配跟进人的代理商
    Page<AssignListVo> findAssignsFollow(Page page, @Param("searchVo") SearchVo searchVo, @Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //查找未分配跟进人的代理商
    Page<AssignListVo> findAssignsNoFollow(Page page, @Param("searchVo") SearchVo searchVo, @Param("assistDataBase") String assistDataBase);

    List<Agent> findAgentsByFollowId(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //获取跟进代理商数量
    int getAgentNum(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //获取跟进创客数量
    int getOrgNum(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);
}
