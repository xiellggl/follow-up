package com.dayi.follow.dao.dayi;

import com.dayi.follow.vo.AccountVo;
import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.vo.LoginLogVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

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

    List<Agent> findLevel2ByOrgId(@Param("orgId") Integer orgId);

    //获取代理中的资金
    double getAgentFund(Integer agentId);

    //获取当日入金
    double getDayInCash(Integer agentId, String todayStart, String todayEnd);

    //获取当日最后一笔入金时间
    Date getDayLastInCashTime(Integer agentId, String todayStart, String todayEnd);

    //获取今日出金
    double getDayOutCash(Integer agentId, String todayStart, String todayEnd);

    //获取今日转出到卡
    double getDayToCard(Integer agentId, String todayStart, String todayEnd);

    //获取今日转出申请
    double getDayApplyOutCash(Integer agentId, String todayStart, String todayEnd);

    //获取今日最后一笔转出申请时间
    Date getDayLastApplyOutCashTime(Integer agentId, String todayStart, String todayEnd);

    //获取已开通银行通道类型（字符串形式）
    String getOpenBankIdsStr(Integer agentId);
    //获取代理商的账户
    AccountVo getAccount(Integer agentId);

    //获取代理商的账户
    Page<LoginLogVo> findLoginLog(Integer agentId, Integer limitStart, Integer limitEnd);
}
