package com.dayi.follow.dao.follow;

import com.dayi.follow.vo.AgentVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowAgentMapper extends BaseMapper<AgentVo> {

    List<Integer> getWaitLinkAgentIds(@Param("followUpId") String followUpId);
}
