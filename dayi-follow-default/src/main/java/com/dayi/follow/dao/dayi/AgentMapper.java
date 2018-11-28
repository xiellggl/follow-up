package com.dayi.follow.dao.dayi;

import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.AgentVo;
import com.dayi.follow.vo.IndexVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface AgentMapper extends BaseMapper<AgentVo> {


    Date findLastLoginTime(@Param("agentId") Integer agentId);

    AgentListVo countRecentAgent(@Param("agentId") Integer agentId);

    List<AgentVo> findByOrgId (@Param("orgId") Integer orgId);
    //获取代理中的资金
    double getAgentFund(Integer agentId);
    //获取可用余额
    double getUseableFund(Integer agentId);
    //获取代理冻结货款
    double getFrozenFund(Integer agentId);
    //获取出金冻结
    double getOutFrozenFund(Integer agentId);
    //获取历史入金总数
    double getTotalInCash(Integer agentId);
}
