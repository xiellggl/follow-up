package com.dayi.follow.service;


import com.dayi.follow.model.follow.Organization;
import com.dayi.follow.vo.index.*;
import com.dayi.follow.vo.org.OrgDataVo;

import java.math.BigDecimal;
import java.util.List;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface CountService {


    //    统计客户状态
    CusStatusVo countCusStatus(String followId);


    /**
     * 统计待联系代理商 -- 跟进人Id
     */
    long getAgentNumWait2Link(String followId);

    /* 统计客户类型--跟进人ID */
    List<CusTypeRatioVo> countCusTypeRatio(String followId);

    /* 统计客户资产阶级--跟进人ID */
    List<FundRankVo> countCusFundRank(String followId);

    /* 统计近七日开户数--跟进人ID */
    List<SevenOpenVo> countSevenOpen(String followId);

    // 统计近七日入金
    List<SevenInCashVo> countSevenInCash(String followId);

    //    统计客服的客户状态
    AdminCusStatusVo countSerCusStatus();

    //统计创客数据
    OrgDataVo countOrgData(String followId);

    //统计团队创客数据
    OrgDataVo countTeamOrgData(String deptId);

    //获得创客的有效代理商数量
    int getValidAgentNum(List<Organization> orgs);

    //获的创客管理资金
    BigDecimal getOrgManageFund(List<Organization> orgs);

    DailyVo countDaily(String followId);

    DailyVo countTeamDaily(String deptId);

    boolean countFollowUpLog();

    void countSourceReport();

}
