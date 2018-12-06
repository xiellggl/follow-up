package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.BankTypeEnum;
import com.dayi.follow.model.follow.Account;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowAgentService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.util.CheckIdCardUtils;
import com.dayi.follow.vo.*;
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
        // 可用余额
        Account account = agentMapper.getAccount(agentId);
        detailVo.setUseableFund(account.getUseable().doubleValue());

        // 实名认证
        detailVo.setIdCard(idCard);
        detailVo.setCardValidDate(agent.getCardValidDate());
        // 总资产
        double agentFund = agentMapper.getAgentFund(agentId);  // 代理资金

        double partFund = BigDecimal.valueOf(agentFund).add(account.getFrozen())
                .add(account.getOutFrozen()).doubleValue();
        double totalFund = BigDecimals.add(account.getUseable().doubleValue(), partFund, 2);

        detailVo.setTotalFund(totalFund);

        // 是否绑卡
        detailVo.setBankSign(agent.isBankSign());
        detailVo.setBankSignDate(agent.getBankSignDate());
        // 最近代理
        AgentListVo agentListVo = agentMapper.countRecentAgent(agentId);
        if (agentListVo != null) {
            detailVo.setRecentAgentFund(agentListVo.getRecentAgentFund());
            detailVo.setRecentAgentDate(agentListVo.getRecentAgentDate());
        }

        // 是否入金
        detailVo.setInCash(account.getTotalInCash().doubleValue());


        DateTime time = new DateTime();
        String todayStrat = time.millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        String todayEnd = time.millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");

        double dayInCash = agentMapper.getDayInCash(agentId, todayStrat, todayEnd);//当日累计入金
        Date dayLastInCashTime = agentMapper.getDayLastInCashTime(agentId, todayStrat, todayEnd);//当日最后一笔入金时间
        detailVo.setDayInCash(dayInCash);
        detailVo.setDayLastInCashTime(dayLastInCashTime);

        // 申请出金
        double dayApplyOutCash = agentMapper.getDayApplyOutCash(agentId, todayStrat, todayEnd);
        detailVo.setDayApplyOutCash(dayApplyOutCash);

        // 申请出金日期
        Date dayLastApplyOutCashTime = agentMapper.getDayLastApplyOutCashTime(agentId, todayStrat, todayEnd);
        if (dayLastApplyOutCashTime != null) {
            String s = dayLastApplyOutCashTime.toString();
            String dayLastApplyOutCashTimeStr = s.substring(0, s.length() - 2);
            detailVo.setDayLastApplyOutCashTimeFm(dayLastApplyOutCashTimeStr);
        }

        // 当日实际出金
        double dayOutCash = agentMapper.getDayOutCash(agentId, todayStrat, todayEnd);
        double dayToCard = agentMapper.getDayToCard(agentId, todayStrat, todayEnd);
        detailVo.setDayOutCash(BigDecimals.add(dayOutCash, dayToCard));

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

        //协议资金（代理中的资金）
        detailVo.setAgentFund(agentFund);

        //冻结货款
        double frozenFund = account.getFrozen().add(account.getOutFrozen()).doubleValue();
        detailVo.setFrozenFund(frozenFund);

        return detailVo;
    }

    @Override
    public Page findContacts(Page page, Integer agentId) {
        return followAgentMapper.findContacts(agentId, page.getStartRow(), page.getPageSize());
    }

    @Override
    public FollowAgent getFollowAgentByAgentId(Integer agentId) {
        return followAgentMapper.getFollowAgentByAgentId(agentId);
    }

    @Override
    public Page findAssignPage(Page page, SearchVo searchVo, String deptId) {

        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);
        List<AssignListVo> assignListVos = new ArrayList<>();

        long num;
        if (searchVo.getAssignStatus() == 1) {//查已分配
            assignListVos = followAgentMapper.findAssignsFollow(page, searchVo, followIds, dayiDataBaseStr);
            num = followAgentMapper.getAssignsFollowNum(searchVo, followIds, dayiDataBaseStr);
        } else {//查未分配
            assignListVos = followAgentMapper.findAssignsNoFollow(page, searchVo, dayiDataBaseStr);
            num = followAgentMapper.getAssignsNoFollowNum(searchVo, dayiDataBaseStr);
        }

        for (AssignListVo vo : assignListVos) {
            //遍历取实际开户银行
            String bankRealName = agentMapper.getAccount(vo.getId()).getBankRealName();
            vo.setRealBank(bankRealName);
        }

        page.setResults(assignListVos);
        page.setTotalRecord(num);
        return page;
    }

    @Override
    public BizResult add(FollowAgent followAgent) {

        FollowAgent followAgentOld = this.getFollowAgentByAgentId(followAgent.getAgentId());

        followAgent.setCreateTime(new Date());
        followAgent.setUpdateTime(new Date());

        if (followAgentOld == null) {//第一次分配
            followAgent.setCustomerType(AgentCusTypeEnum.NOT_LINK.getValue());
        } else {//删除原来的关系
            followAgent.setFollowDateBefore(followAgentOld.getCreateTime());//之前分配时间

            FollowUp followUp = followUpMapper.get(followAgentOld.getFollowId());
            followAgent.setFollowUpBefore(followUp.getName());//之前跟进人

            followAgent.setCustomerType(followAgentOld.getCustomerType());
            followAgent.setCusIntentionType(followAgentOld.getCusIntentionType());

            int delete = followAgentMapper.delete(followAgentOld);
            if (1 != delete) return BizResult.FAIL;

        }
        return 1 == followAgentMapper.add(followAgent) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public BizResult addBatch(List<FollowAgent> followAgents) {
        for (FollowAgent followAgent : followAgents) {
            BizResult add = this.add(followAgent);
            if (!add.isSucc()) return BizResult.FAIL;
        }
        return BizResult.SUCCESS;
    }

    @Override
    public BizResult clear(FollowAgent followAgent) {
        followAgent.setFollowId(null);
        followAgent.setFollowDate(null);
        return 1 == followAgentMapper.update(followAgent) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public BizResult clearBatch(List<FollowAgent> followAgents) {
        for (FollowAgent followAgent : followAgents) {
            BizResult clear = this.clear(followAgent);
            if (!clear.isSucc()) return BizResult.FAIL;
        }
        return BizResult.SUCCESS;

    }

}

