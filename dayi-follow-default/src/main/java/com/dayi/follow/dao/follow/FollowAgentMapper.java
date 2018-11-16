package com.dayi.follow.dao.dayi_follow;

import com.dayi.follow.vo.AgentVo;
import com.dayi.mybatis.support.BaseMapper;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowAgentMapper extends BaseMapper<AgentVo> {

    List<Integer> getWaitLinkAgentIds(String followUpId);
}
