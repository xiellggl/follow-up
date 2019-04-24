package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.vo.agent.AssignListVo;
import com.dayi.follow.vo.agent.DetailVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;

import java.math.BigDecimal;
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

    List findAssignList(SearchVo searchVo);

    BizResult add(FollowAgent followAgent);

    BizResult addBatch(String agentIds,String followId);

    BizResult clear(FollowAgent followAgent);

    BizResult clearBatch(List<FollowAgent> followAgents);

    BizResult updateHisFund(Integer agentId, BigDecimal hisFund);


}
