package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.vo.agent.AssignListVo;
import com.dayi.follow.vo.agent.DetailVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;

import java.util.List;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface FollowAgentService {


    String getFollowIdByAgentId(Integer agentId);

    DetailVo getDetail(Integer agentId);

    Page findContacts(Page page, Integer agentId);

    FollowAgent getFollowAgentByAgentId(Integer agentId);

    Page findAssignPage(Page<AssignListVo> page, SearchVo searchVo);

    List findAssignList(SearchVo searchVo, String deptId);

    BizResult add(FollowAgent followAgent);

    BizResult addBatch(List<FollowAgent> followAgents);

    BizResult clear(FollowAgent followAgent);

    BizResult clearBatch(List<FollowAgent> followAgents);


}
