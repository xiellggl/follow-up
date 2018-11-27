package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowOrgMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.enums.OrgTypeEnum;
import com.dayi.follow.enums.SwitchStatusEnum;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.CountService;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

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
    @Resource
    private AgentMapper agentMapper;
    @Resource
    private FollowAgentMapper followAgentMapper;
    @Resource
    private FollowOrgMapper followOrgMapper;
    @Value("${follow.dataBase}")
    String followDataBaseStr;


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
    public long getAgentNumWait2Link(String followId, String dateStr) {
        return countMapper.getAgentNumWait2Link(followId, dateStr, followDataBaseStr);
    }

    /* 统计客户类型--跟进人ID */
    @Override
    public List<CusTypeRatioVo> countCusTypeRatio(String followId) {
        return countMapper.countCusTypeRatio(followId, followDataBaseStr);
    }

    @Override
    public List<FundRankVo> countCusFundRank(String followId) {
        return countMapper.countCusFundRank(followId, followDataBaseStr);
    }

    @Override
    public List<SevenOpenVo> countSevenOpen(String followId) {
        return countMapper.countSevenOpen(followId, followDataBaseStr);
    }

    @Override
    public List<SevenInCashVo> countSevenInCash(String followId) {
        return countMapper.countSevenInCash(followId, followDataBaseStr);
    }


    public SerCusStatusVo countSerCusStatus(List<String> chargeDeptIds) {
        SerCusStatusVo serCusStatusVo = new SerCusStatusVo();

        //跟进人数量
        List<FollowUp> followUps = followUpMapper.findByDeptId(chargeDeptIds);
        serCusStatusVo.setFollowUpNum(followUps.size());

        //待分配客户数量
        long waitAgentNum = countMapper.getWaitAssignAgentNum(followDataBaseStr);//待分配代理商数量
        long waitOrgNum = countMapper.getWaitAssignOrgNum(OrgTypeEnum.Maker.getValue(), followDataBaseStr);//待分配创客数量
        serCusStatusVo.setWaitAssignNum(waitAgentNum + waitOrgNum);

        //跟进用户总数
        List<String> followIds = followUpMapper.findIdsByDeptId(chargeDeptIds);
        long followCusNum = countMapper.getFollowCusNum(followIds, followDataBaseStr);
        serCusStatusVo.setFollowCusNum(followCusNum);

        //已认证客户数量
        long nameCusNum = countMapper.getNameCusNum(followIds);
        //已绑卡客户数量
        long cardCusNum = countMapper.getCardCusNum(followIds);
        //已代理客户数量
        long agentCusNum = countMapper.getAgentCusNum(followIds);
        serCusStatusVo.setNameNum(nameCusNum);
        serCusStatusVo.setCardNum(cardCusNum);
        serCusStatusVo.setAgentNum(agentCusNum);
        //资产总规模
        double agentTotalFund = followAgentMapper.getTotalFund(followIds);
        double orgTotalFund = followOrgMapper.getTotalFund(followIds);
        serCusStatusVo.setTotalFund(BigDecimals.add(agentTotalFund, orgTotalFund));

        return serCusStatusVo;
    }

    @Override
    public OrgDataVo countOrgData(String followId) {
        IndexVo indexVo = new IndexVo();
        OrgDataVo orgDataVo = new OrgDataVo();
        Integer num = 0;
        Integer orgValidAgentNum = 0;


        if (StringUtils.isBlank(followId)) return orgDataVo;
        FollowUp followUp = followUpMapper.get(followId);

        List<OrgVo> subOrgList = followOrgMapper.findOrgsByfollowId(followId);


        for (OrgVo orgVo : subOrgList) {
            Integer switchStatus = orgVo.getSwitchStatus();
            if (switchStatus != null && switchStatus.equals(SwitchStatusEnum.OPEN.getKey())) {//二级资管开启
                num = countMapper.getOrgValidAgentNum(orgVo.getId(), null);//有效代理商-包括2级
                orgValidAgentNum = orgValidAgentNum + num;
            }
            if (switchStatus != null && switchStatus.equals(ChannelCodeRecord.EnumSwitchStatus.CLOSE.getKey())) {//二级资管关闭
                num = flowUpDao.countOrgValidAgentNum(organization.getId(), 1).getOrgValidAgentNum();//有效代理商-包括2级
                orgValidAgentNum = orgValidAgentNum + num;
            }
        }
        indexVo.setKaOrgNum(subOrgList.size());
        indexVo.setKaOrgValidAgentNum(orgValidAgentNum);

        List<Organization> organizations = flowUpDao.getFlowUpOrg(true, flowId, whereSql);
        double assets = 0.0;
        for (Organization organization : organizations) {
            double firstAgentAssets = protocolService.getAgentAssets(organization, 1);//我的代理商资产（第一级代理商资产）
            double secondAgentAssets = 0.0;//二级代理商资产
            Integer secondIncomeSwitch = organization.getSecondIncomeSwitch();
            if (secondIncomeSwitch != null && secondIncomeSwitch.equals(ChannelCodeRecord.EnumSwitchStatus.OPEN.getKey().intValue())) {//如果开了二级收益开关
                secondAgentAssets = protocolService.getAgentAssets(organization, 2);
            }
            double organizationAssets = BigDecimals.add(firstAgentAssets, secondAgentAssets);
            assets = BigDecimals.add(assets, organizationAssets);
        }
        indexVo.setKaOrgManageMoney(BigDecimal.valueOf(assets).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public OrgDataVo countTeamOrgData(String deptId) {
        return null;
    }

    @Override
    public OrgDataVo countTeamsOrgData(List<String> deptIds) {
        return null;
    }


}

