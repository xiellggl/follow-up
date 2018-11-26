package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.AgentContact;
import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowAgentMapper extends BaseMapper<AgentListVo> {

    List<Integer> getWaitLinkAgentIds(@Param("followUpId") String followUpId);

    AgentContact findLastContact(@Param("agentId") Integer agentId);

    List<AgentListVo> findAgents(@Param("searchVo") SearchVo searchVo, @Param("ids") List<Integer> ids,
                                 @Param("startStr") String startStr, @Param("endStr") String endStr,
                                 @Param("followId") String followId, @Param("whereSql") String whereSql,
                                 @Param("assistDataBase") String assistDataBase, @Param("limitStart") Integer limitStart,
                                 @Param("limitEnd") Integer limitEnd);

    Integer findAgentsCount(@Param("searchVo") SearchVo searchVo, @Param("ids") List<Integer> ids,
                            @Param("startStr") String startStr, @Param("endStr") String endStr,
                            @Param("followId") String followId, @Param("whereSql") String whereSql,
                            @Param("assistDataBase") String assistDataBase);

}
