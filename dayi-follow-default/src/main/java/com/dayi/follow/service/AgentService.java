package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.vo.AgentVo;
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
    Page<AgentVo> findAgentPage(Page<AgentVo> page, Integer linkStatus, Integer idCardValid, Integer sign,
                                Integer inCash, Integer totalFund, String mobile, String invitCode,
                                Integer subDeptId, String followUp, Integer todayInCash, Integer todayOutCash,
                                Integer customerType, Integer totalBalance, String bankType,
                                Integer waitToLinkToday, String flowId, Integer deptId, Integer deptFlowId);


}
