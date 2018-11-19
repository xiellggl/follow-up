package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.vo.AgentVo;
import com.dayi.follow.vo.SearchVo;
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
    Page<AgentVo> findAgentPage(Page<AgentVo> page, SearchVo searchVo, String followId, Integer deptId, Integer deptFlowId,
                                Integer subDeptId, String followUp);


}
