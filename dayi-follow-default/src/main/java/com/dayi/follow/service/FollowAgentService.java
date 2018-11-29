package com.dayi.follow.service;


import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.vo.DetailVo;
import com.dayi.mybatis.support.Page;
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

}
