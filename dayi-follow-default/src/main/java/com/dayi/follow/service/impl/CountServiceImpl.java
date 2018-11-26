package com.dayi.follow.service.impl;


import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.service.CountService;
import com.dayi.follow.vo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 跟进人 业务实现类
 */
@Service
public class CountServiceImpl implements CountService {
    @Resource
    private FollowUpMapper followUpMapper;
    @Resource
    private ReportMapper reportMapper;
    @Resource
    private CountMapper countMapper;
    @Value("${follow.dataBase}")
    String followDataBaseStr;

    @Override
    public IndexVo countPreDayDaily(String followId, String deptIdStr) {
        return reportMapper.countPreDayDaily(followId, deptIdStr);
    }

    /**
     * 代理商 -- 统计相应跟进人数目--
     */
    public CusStatusVo countCusStatus(String followId) {
        CusStatusVo statusVo = new CusStatusVo();

        List<String> followIds = new ArrayList<String>();
        followIds.add(followId);

        statusVo.setCusNum(countMapper.getCusNum(followIds));// 跟进用户总数
        statusVo.setHadLinkNum(countMapper.getLinkCusNum(followIds));   // 已联系--用户人数
        statusVo.setHadRealNameNum(countMapper.getNameCusNum(followIds));// 已实名认证--用户人数
        statusVo.setHadSignNum(countMapper.getCardCusNum(followIds));// 已绑卡--用户人数
        statusVo.setHadInCashNum(countMapper.getInCashCusNum(followIds));// 已入金--用户人数
        statusVo.setHadAgentNum(countMapper.getAgentCusNum(followIds));// 已代理--用户人数
        statusVo.setNoFundNum(countMapper.getNoFundCusNum(followIds));// 总资产为零--用户人数
        statusVo.setHadLostNum(countMapper.getLostCusNum(followIds)); //流失客户

        return statusVo;
    }

    @Override
    public Integer getAgentNumWait2Link(String followId, String dateStr) {
        return countMapper.getAgentNumWait2Link(followId, dateStr,followDataBaseStr);
    }

    /* 统计客户类型--跟进人ID */
    @Override
    public List<CusTypeRatioVo> countCusTypeRatio(String followId) {
        return countMapper.countCusTypeRatio(followId,followDataBaseStr);
    }

    @Override
    public List<FundRankVo> countCusFundRank(String followId) {
        return countMapper.countCusFundRank(followId,followDataBaseStr);
    }

    @Override
    public List<SevenOpenVo> countSevenOpen(String followId) {
        return countMapper.countSevenOpen(followId,followDataBaseStr);
    }

    @Override
    public List<SevenInCashVo> countSevenInCash(String followId) {
        return countMapper.countSevenInCash(followId,followDataBaseStr);
    }
}

