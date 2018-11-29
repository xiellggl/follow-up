package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.AgentContact;
import com.dayi.follow.vo.*;
import com.dayi.mybatis.support.Page;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface AgentService {


    /**
     * 获取代理商列表
     */
    Page<AgentListVo> findAgentPage(Page<AgentListVo> page, SearchVo searchVo, String followId);

    /**
     * 获取代理商列表
     */
    Page<AgentListVo> findTeamAgentPage(Page<AgentListVo> page, SearchVo searchVo,String followId, String deptId);
    Agent get(Integer agentId);

    Page<LoginLogVo> findLoginLog(Page page,Integer agentId);

    BizResult addContact(AgentContact agentContact);

}
