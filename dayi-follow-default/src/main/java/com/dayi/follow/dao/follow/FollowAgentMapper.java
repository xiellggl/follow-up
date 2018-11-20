package com.dayi.follow.dao.follow;

import com.dayi.follow.model.AgentContact;
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

    List<Integer> findAgentIdFollow(@Param("searchVo") SearchVo searchVo, @Param("followId") String followId,
                                    @Param("whereSql") String whereSql);

    AgentContact findLastContact(@Param("agentId") Integer agentId) ;
}
