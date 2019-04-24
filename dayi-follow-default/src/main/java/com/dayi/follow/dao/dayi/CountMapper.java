package com.dayi.follow.dao.dayi;

import com.dayi.follow.vo.index.*;
import com.dayi.follow.vo.org.OrgDataVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface CountMapper {

    //获取跟进用户数量
    long getCusNum(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //获取已联系用户数量
    long getLinkCusNum(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //获取已实名认证用户数量
    long getNameCusNum(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //获取已绑卡用户数量
    long getCardCusNum(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //获取已入金用户数量
    long getInCashCusNum(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //获取已代理用户数量
    long getAgentCusNum(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //获取资产为0用户数量
    long getNoFundCusNum(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //获取已流失用户数量
    long getLostCusNum(@Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    /**
     * 统计待联系代理商
     */
    Long getAgentNumWait2Link(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("assistDataBase") String assistDataBase);

    //统计客户类型占比
    List<CusTypeRatioVo> countCusTypeRatio(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //客户资产阶级统计
    List<FundRankVo> countCusFundRank(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //统计近七日开户数
    List<SevenOpenVo> countSevenOpen(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //统计近七日入金
    List<SevenInCashVo> countSevenInCash(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //获取未分配代理商数量
    long getNoAssignedAgentNum(@Param("assistDataBase") String assistDataBase);

    //获取已分配创客数量
    int getNoAssignedOrgNum(@Param("assistDataBase") String assistDataBase);

    //获取跟进代理商数量
    long getFollowAgentNum(@Param("assistDataBase") String assistDataBase);

    //获取跟进创客数量
    int getFollowOrgNum(@Param("assistDataBase") String assistDataBase);

    //统计团队日报
    DailyVo countTeamDaily(@Param("deptIds") List<String> deptIds, @Param("assistDataBase") String assistDataBase);


    //统计日报
    DailyVo countDaily(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    //获取某一创客有效代理商数量
    int getOrgValidAgentNum(@Param("orgId") Integer orgId, @Param("inviteLevel") Integer inviteLevel);

    //获取多个创客有效代理商数量
    int getValidAgentNumAll(@Param("orgIds") List<Integer> orgIds);

    //获取多个创客一级代理商数量
    int getValidAgentNumLevel1(@Param("orgIds") List<Integer> orgIds);

}
