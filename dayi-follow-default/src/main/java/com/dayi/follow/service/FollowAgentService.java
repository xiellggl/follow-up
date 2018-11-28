package com.dayi.follow.service;


import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.AgentVo;
import com.dayi.follow.vo.DetailVo;
import com.dayi.follow.vo.SearchVo;
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

}
