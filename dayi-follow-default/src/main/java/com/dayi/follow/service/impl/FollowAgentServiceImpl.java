package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.common.util.BizResult;
import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.BankTypeEnum;
import com.dayi.follow.model.follow.*;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowAgentService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.util.CheckIdCardUtils;
import com.dayi.follow.vo.*;
import com.dayi.follow.vo.agent.AgentContactVo;
import com.dayi.follow.vo.agent.AgentListVo;
import com.dayi.follow.vo.agent.AssignListVo;
import com.dayi.follow.vo.agent.DetailVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 跟进人 业务实现类
 */
@Service
public class FollowAgentServiceImpl implements FollowAgentService {
    @Resource
    private FollowUpMapper followUpMapper;
    @Resource
    private OrgService orgService;
    @Resource
    private DeptService deptService;
    @Resource
    private FollowAgentMapper followAgentMapper;
    @Resource
    private AgentMapper agentMapper;
    @Resource
    private AgentService agentService;
    @Resource
    private UserComponent userComponent;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;


    @Override
    public String getFollowIdByAgentId(Integer agentId) {
        return followAgentMapper.getFollowIdByAgentId(agentId);
    }

    @Override
    public DetailVo getDetail(Integer agentId) {
        DetailVo detailVo = new DetailVo();
        if (agentId == null) {
            return detailVo;
        }
        Agent agent = agentMapper.get(agentId);
        if (agent == null) return detailVo;

        // 名称
        String idCard = agent.getIdCard();
        if (!StringUtils.isBlank(idCard)) {
            String linkPerson = agent.getLinkPerson();
            String substring = linkPerson.substring(0, 1);//截取姓氏
            String nameFormat = substring + CheckIdCardUtils.getCnGenderByIdCard(idCard);
            detailVo.setLinkPersonFm(nameFormat);
        }
        // 手机号
        detailVo.setMobile(agent.getMobile());
        // 邀请码
        detailVo.setInviteCode(agent.getRecordInviteCode());
        // 注册时间
        detailVo.setCreateDate(agent.getCreateDate());
        // 身份证
        detailVo.setIdCard(agent.getIdCard());
        // 所在地
        detailVo.setIdCardAddr(agent.getIdCardAddr());

        // 实名认证
        detailVo.setIdCard(idCard);
        detailVo.setCardValidDate(agent.getCardValidDate());


        // 是否绑卡
        detailVo.setBankSign(agent.isBankSign());
        detailVo.setBankSignDate(agent.getBankSignDate());
        // 最近代理
        AgentListVo agentListVo = agentMapper.countRecentAgent(agentId);
        if (agentListVo != null) {
            detailVo.setRecentAgentFund(agentListVo.getRecentAgentFund());
            detailVo.setRecentAgentDate(agentListVo.getRecentAgentDate());
        }

        DateTime time = new DateTime();
        String todayStrat = time.millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String todayEnd = time.millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        BigDecimal dayInCash = agentMapper.getDayInCash(agentId, todayStrat, todayEnd);//当日累计入金
        Date dayLastInCashTime = agentMapper.getDayLastInCashTime(agentId, todayStrat, todayEnd);//当日最后一笔入金时间
        detailVo.setDayInCash(dayInCash);
        detailVo.setDayLastInCashTime(dayLastInCashTime);

        // 申请出金
        BigDecimal dayApplyOutCash = agentMapper.getDayApplyOutCash(agentId, todayStrat, todayEnd);
        detailVo.setDayApplyOutCash(dayApplyOutCash);

        // 申请出金日期
        Date dayLastApplyOutCashTime = agentMapper.getDayLastApplyOutCashTime(agentId, todayStrat, todayEnd);
        if (dayLastApplyOutCashTime != null) {
            String s = dayLastApplyOutCashTime.toString();
            String dayLastApplyOutCashTimeStr = s.substring(0, s.length() - 2);
            detailVo.setDayLastApplyOutCashTimeFm(dayLastApplyOutCashTimeStr);
        }

        // 当日实际出金
        BigDecimal dayOutCash = agentMapper.getDayOutCash(agentId, todayStrat, todayEnd);
        BigDecimal dayToCard = agentMapper.getDayToCard(agentId, todayStrat, todayEnd);
        detailVo.setDayOutCash(dayOutCash.add(dayToCard));

        //已开通结算银行
        String openBankIdsStr = agentMapper.getOpenBankIdsStr(agentId);
        if (!StringUtils.isBlank(openBankIdsStr)) {
            BankTypeEnum[] values = BankTypeEnum.values();
            for (BankTypeEnum value : values) {
                if (openBankIdsStr.contains(value.getKey().toString())) {
                    openBankIdsStr = openBankIdsStr.replace(value.getKey().toString(), value.getCname());
                }
            }
            detailVo.setBankOpen(openBankIdsStr);
        }

        //分配时间
        detailVo.setAssignDate(followAgentMapper.getAssignDate(agentId));

        //年龄
        if (!StringUtils.isBlank(idCard)) {
            int age = CheckIdCardUtils.getAgeByIdCard(idCard);
            detailVo.setAge(age);
        }
        //出生月日
        if (!StringUtils.isBlank(idCard)) {
            String month = idCard.substring(10, 12);//月份
            String day = idCard.substring(12, 14);//日
            detailVo.setDateStr(month + "月" + day + "日");
        }
        //状态
        Integer status = agent.getStatus();
        detailVo.setStatus(status);//取statusStr

        // 可用余额
        Account account = agentMapper.getAccount(agentId);
        if (account == null) return detailVo;

        detailVo.setUseableFund(account.getUseable());

        //BigDecimal agentFund = agentMapper.getAgentFund(agentId);  // 代理资金
        BigDecimal agentFund = account.getCargoInterest().multiply(BigDecimal.valueOf(0.8)).add(account.getCargoInterestPuchas());  // 代理资金

        //协议资金（代理中的资金）
        detailVo.setAgentFund(agentFund);

        // 总资产
        BigDecimal partFund = agentFund.add(account.getFrozen())
                .add(account.getOutFrozen());

        BigDecimal totalFund = account.getUseable().add(partFund).setScale(2, BigDecimal.ROUND_HALF_UP);
        detailVo.setTotalFund(totalFund);

        // 是否入金
        detailVo.setInCash(account.getTotalInCash());

        //冻结货款
        BigDecimal frozenFund = account.getFrozen().add(account.getOutFrozen());
        detailVo.setFrozenFund(frozenFund);

        return detailVo;
    }

    @Override
    public Page findContacts(Page page, Integer agentId) {
        if (agentId == null) return page;
        return followAgentMapper.findContacts(page, agentId);
    }

    @Override
    public FollowAgent getFollowAgentByAgentId(Integer agentId) {
        return followAgentMapper.getFollowAgentByAgentId(agentId);
    }

    @Override
    public Page findAssignPage(Page<AssignListVo> page, SearchVo searchVo) {

        if (searchVo.getAssignStatus() == null || searchVo.getAssignStatus() != 1) {//查未分配
            page = followAgentMapper.findAssignsNoFollow(page, searchVo, dayiDataBaseStr);
        } else {//查已分配
            page = followAgentMapper.findAssignsFollow(page, searchVo, dayiDataBaseStr);
        }

        return page;
    }

    @Override
    public List findAssignList(SearchVo searchVo) {
        List<AssignListVo> list;

        if (searchVo.getAssignStatus() == null || searchVo.getAssignStatus() != 1) {//查未分配
            list = followAgentMapper.findAssignsNoFollowLimit(searchVo, dayiDataBaseStr);
        } else {//查已分配
            list = followAgentMapper.findAssignsFollowLimit(searchVo, dayiDataBaseStr);
        }

        return list;
    }


    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.ADD, what = "代理商分配管理", note = "分配跟进人")
    public BizResult add(FollowAgent followAgentVo) {
        FollowUp followUp = followUpMapper.get(followAgentVo.getFollowId());
        if (followUp == null) return BizResult.FAIL;

        Agent agent = agentService.get(followAgentVo.getAgentId());
        if (agent == null) return BizResult.FAIL;

        Account account = agentMapper.getAccount(followAgentVo.getAgentId());
        BigDecimal agentFund = BigDecimal.ZERO;
        BigDecimal totalFund = BigDecimal.ZERO;
        if (account != null) {
            agentFund = account.getCargoInterest().multiply(BigDecimal.valueOf(0.8)).add(account.getCargoInterestPuchas());

            totalFund = account.getCargoInterest().multiply(BigDecimal.valueOf(0.8)).
                    add(account.getCargoInterestPuchas()).
                    add(account.getFrozen()).add(account.getUseable()).add(account.getOutFrozen());
        }

        FollowAgent followAgent = followAgentMapper.getFollowAgentByAgentId(followAgentVo.getAgentId());
        if (followAgent == null) {//创建
            followAgent = new FollowAgent();
            followAgent.setId(followAgentMapper.getNewId());
            followAgent.setAgentId(followAgentVo.getAgentId());
            followAgent.setFollowId(followAgentVo.getFollowId());
            followAgent.setAssignDate(new Date());
            followAgent.setCreateTime(new Date());
            followAgent.setUpdateTime(new Date());

            followAgent.setAgentFundBefore(agentFund);
            followAgent.setTotalFundBefore(totalFund);

            followAgent.setCustomerType(AgentCusTypeEnum.NOT_LINK.getValue());

        }else {
            //当前有跟进人
            FollowUp oldFollowUp = followUpMapper.get(followAgent.getFollowId());
            if (oldFollowUp != null) {//变更跟进人
                followAgent.setFollowUpBefore(oldFollowUp.getName());
                followAgent.setAssignDateBefore(followAgent.getAssignDate());
            }

            followAgent.setAgentFundBefore(agentFund);
            followAgent.setTotalFundBefore(totalFund);

            followAgent.setFollowId(followAgentVo.getFollowId());
            followAgent.setAssignDate(new Date());
            followAgent.setUpdateTime(new Date());
            return 1 == followAgentMapper.updateAll(followAgent) ? BizResult.SUCCESS : BizResult.FAIL;
        }
        return 1 == followAgentMapper.add(followAgent) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.ADD, what = "代理商分配管理", note = "批量分配跟进人")
    public BizResult addBatch(List<FollowAgent> followAgents) {
        for (FollowAgent followAgent : followAgents) {
            BizResult add = this.add(followAgent);
            if (!add.isSucc()) return BizResult.FAIL;
        }
        return BizResult.SUCCESS;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "代理商分配管理", note = "清除跟进人")
    public BizResult clear(FollowAgent followAgent) {
        Agent agent = agentMapper.get(followAgent.getAgentId());
        if (agent == null) return BizResult.FAIL;

        FollowUp followUp = followUpMapper.get(followAgent.getFollowId());
        if (followUp == null) return BizResult.fail("清除失败，无此跟进人！");

        followAgent.setFollowId(null);
        followAgent.setAssignDate(null);
        followAgent.setUpdateTime(new Date());

        return 1 == followAgentMapper.updateAll(followAgent) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "代理商分配管理", note = "批量清除跟进人")
    public BizResult clearBatch(List<FollowAgent> followAgents) {
        for (FollowAgent followAgent : followAgents) {
            BizResult clear = this.clear(followAgent);
            if (!clear.isSucc()) return BizResult.FAIL;
        }
        return BizResult.SUCCESS;

    }

}

