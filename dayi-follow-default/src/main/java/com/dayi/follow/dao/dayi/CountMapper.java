package com.dayi.follow.dao.dayi;

import com.dayi.follow.vo.index.*;
import com.dayi.follow.vo.org.OrgDataVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface CountMapper {

    //获取跟进用户数量
    long getCusNum(@Param("followIds") List<String> followIds);

    //获取已联系用户数量
    long getLinkCusNum(@Param("followIds") List<String> followIds);

    //获取已实名认证用户数量
    long getNameCusNum(@Param("followIds") List<String> followIds);

    //获取已绑卡用户数量
    long getCardCusNum(@Param("followIds") List<String> followIds);

    //获取已入金用户数量
    long getInCashCusNum(@Param("followIds") List<String> followIds);

    //获取已代理用户数量
    long getAgentCusNum(@Param("followIds") List<String> followIds);

    //获取资产为0用户数量
    long getNoFundCusNum(@Param("followIds") List<String> followIds);

    //获取已流失用户数量
    long getLostCusNum(@Param("followIds") List<String> followIds);

    /**
     * 统计待联系代理商
     */
    Long getAgentNumWait2Link(@Param("followId") String followId, @Param("dateStr") String dateStr, @Param("assistDataBase") String assistDataBase);

    List<CusTypeRatioVo> countCusTypeRatio(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    List<FundRankVo> countCusFundRank(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    List<SevenOpenVo> countSevenOpen(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    List<SevenInCashVo> countSevenInCash(@Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    long getWaitAssignAgentNum(@Param("assistDataBase") String assistDataBase);

    long getWaitAssignOrgNum(@Param("orgType") Integer orgType, @Param("assistDataBase") String assistDataBase);

    long getFollowCusNum(List<String> followUpIds, @Param("assistDataBase") String assistDataBase);

    //统计KA的创客数据
    OrgDataVo countOrgData(List<String> followIds);

    int getOrgValidAgentNum(@Param("orgId") Integer orgId, @Param("inviteLevel") Integer inviteLevel);

    //统计团队日报
    DailyVo countTeamDaily(String deptId);

    /**
     * 统计日报
     */

    DailyVo countDaily(String followId);


}
