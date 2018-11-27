package com.dayi.follow.dao.dayi;

import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.IndexVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface AgentMapper extends BaseMapper<AgentListVo> {


    Date findLastLoginTime(@Param("agentId") Integer agentId);

    AgentListVo countRecentAgent(@Param("agentId") Integer agentId);




}
