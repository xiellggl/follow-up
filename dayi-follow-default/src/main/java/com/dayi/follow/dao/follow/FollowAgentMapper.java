package com.dayi.follow.dao.follow;

import com.dayi.follow.vo.AgentVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowAgentMapper extends BaseMapper<AgentVo> {

    List<Integer> getWaitLinkAgentIds(@Param("followUpId") String followUpId);

    List<AgentVo> findAgents(@Param("searchVo") SearchVo searchVo, @Param("ids") List<Integer> ids,
                             @Param("followId") String followId, @Param("whereSql") String whereSql,
                             @Param("startStr") String startStr, @Param("endStr") String endStr,
                             @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);

    Integer findAgentsCount(@Param("searchVo") SearchVo searchVo, @Param("ids") List<Integer> ids,
                             @Param("followId") String followId, @Param("whereSql") String whereSql,
                             @Param("startStr") String startStr, @Param("endStr") String endStr);

}
