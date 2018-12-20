package com.dayi.follow.dao.dayi;

import com.dayi.follow.model.follow.Account;
import com.dayi.follow.vo.agent.AgentListVo;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.vo.LoginLogVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface AgentMapper extends BaseMapper<Agent> {


    Date findLastLoginTime(@Param("agentId") Integer agentId);

    AgentListVo countRecentAgent(@Param("agentId") Integer agentId);

    List<Agent> findByOrgId(@Param("orgId") Integer orgId);

    List<Agent> findLevel1ByOrgId(@Param("orgId") Integer orgId);

    //获取代理中的资金
    BigDecimal getAgentFund(@Param("agentId") Integer agentId);

    //获取当日入金
    BigDecimal getDayInCash(@Param("agentId") Integer agentId, @Param("todayStart") String todayStart, @Param("todayEnd") String todayEnd);

    //获取当日最后一笔入金时间
    Date getDayLastInCashTime(@Param("agentId") Integer agentId, @Param("todayStart") String todayStart, @Param("todayEnd") String todayEnd);

    //获取今日出金
    BigDecimal getDayOutCash(@Param("agentId") Integer agentId, @Param("todayStart") String todayStart, @Param("todayEnd") String todayEnd);

    //获取今日转出到卡
    BigDecimal getDayToCard(@Param("agentId") Integer agentId, @Param("todayStart") String todayStart, @Param("todayEnd") String todayEnd);

    //获取今日转出申请
    BigDecimal getDayApplyOutCash(@Param("agentId") Integer agentId, @Param("todayStart") String todayStart, @Param("todayEnd") String todayEnd);

    //获取今日最后一笔转出申请时间
    Date getDayLastApplyOutCashTime(@Param("agentId") Integer agentId, @Param("todayStart") String todayStart, @Param("todayEnd") String todayEnd);

    //获取已开通银行通道类型（字符串形式）
    String getOpenBankIdsStr(@Param("agentId") Integer agentId);

    //获取代理商的账户
    Account getAccount(@Param("agentId") Integer agentId);

    //获取代理商的账户
    List<LoginLogVo> findLoginLog(@Param("agentId") Integer agentId, @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);

    //获取代理商的账户
    long getLoginLogNum(@Param("agentId") Integer agentId);

    Agent getByUcId(String ucId);
}
