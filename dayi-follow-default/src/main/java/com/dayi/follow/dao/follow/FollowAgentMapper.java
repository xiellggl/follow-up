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
    double getTotalFund(@Param("followIds") List<String> followIds);

    String getFollowIdByAgentId(@Param("agentId") Integer agentId);

    //获取联系记录
    Page<AgentContactVo> findContacts(@Param("agentId") Integer agentId, @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);

    //获取分配
    Date getAssignDate(@Param("agentId") Integer agentId);

    //获取follow_agent
    FollowAgent getFollowAgentByAgentId(@Param("agentId") Integer agentId);

    //查找已分配跟进人的代理商
    List<AssignListVo> findAssignsFollow(Page page, SearchVo searchVo, List<String> followIds, String assistDataBase);

    //统计已分配跟进人的代理商数量
    long getAssignsFollowNum(SearchVo searchVo, List<String> followIds, String assistDataBase);

    //查找未分配跟进人的代理商
    List<AssignListVo> findAssignsNoFollow(Page page, SearchVo searchVo, String assistDataBase);

    //统计未分配跟进人的代理商数量
    long getAssignsNoFollowNum(@Param("searchVo") SearchVo searchVo, @Param("assistDataBase") String assistDataBase);

    List<Agent> findAgentsByFollowId(@Param("followId") String followId, @Param("assistDataBase") String dayiDataBaseStr);




}
