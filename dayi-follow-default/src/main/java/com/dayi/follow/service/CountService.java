package com.dayi.follow.service;


import com.dayi.follow.vo.*;

import java.util.Date;
import java.util.List;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface CountService {

    IndexVo countPreDayDaily(String followId, String deptIdStr);

    CusStatusVo countCusStatus(String followId);


    /**
     * 统计待联系代理商 -- 跟进人Id
     */
    Integer getAgentNumWait2Link(String followId, String dateStr);

    /* 统计客户类型--跟进人ID */
    List<CusTypeRatioVo> countCusTypeRatio(String followId);

    /* 统计客户资产阶级--跟进人ID */
    List<FundRankVo> countCusFundRank(String followId);

    /* 统计近七日开户数--跟进人ID */
    List<SevenOpenVo> countSevenOpen(String followId);
    // 统计近七日入金
    List<SevenInCashVo> countSevenInCash(String followId);
}
