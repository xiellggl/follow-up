package com.dayi.follow.dao.dayi;

import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.IndexVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface AgentMapper extends BaseMapper<AgentListVo> {


    Date findLastLoginTime(@Param("agentId") Integer agentId);

    AgentListVo countRecentAgent(@Param("agentId") Integer agentId);

    //获取跟进用户数量
    Integer getCusNum(@Param("followIds") List<String> followIds);

    //获取已联系用户数量
    Integer getLinkCusNum(@Param("followIds") List<String> followIds);

    //获取已实名认证用户数量
    Integer getNameCusNum(@Param("followIds") List<String> followIds);

    //获取已绑卡用户数量
    Integer getCardCusNum(@Param("followIds") List<String> followIds);

    //获取已入金用户数量
    Integer getInCashCusNum(@Param("followIds") List<String> followIds);

    //获取已代理用户数量
    Integer getAgentCusNum(@Param("followIds") List<String> followIds);

    //获取资产为0用户数量
    Integer getNoFundCusNum(@Param("followIds") List<String> followIds);

    //获取已流失用户数量
    Integer getLostCusNum(@Param("followIds") List<String> followIds);
}
