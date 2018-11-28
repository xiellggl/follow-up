package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.enums.FundTypeEnum;
import com.dayi.follow.model.follow.AgentContact;
import com.dayi.follow.service.AgentService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowAgentService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.util.CheckIdCardUtils;
import com.dayi.follow.util.CollectionUtil;
import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.AgentVo;
import com.dayi.follow.vo.DetailVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        AgentVo agent = agentMapper.get(agentId);
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
        detailVo.setIdCardAddr(this.getIdCardAddr(agent));
        // 可用余额
        double useableFund = agentMapper.getUseableFund(agentId);

        detailVo.setUseableFund(useableFund);

        // 实名认证
        detailVo.setIdCard(idCard);
        detailVo.setCardValidDate(agent.getCardValidDate());
        // 总资产
        double agentFund = agentMapper.getAgentFund(agentId);  // 代理资金
        double frozenFund = agentMapper.getFrozenFund(agentId);//代理冻结资金
        double outFrozenFund = agentMapper.getOutFrozenFund(agentId);//出金冻结资金
        double partFund = BigDecimal.valueOf(agentFund).add(BigDecimal.valueOf(frozenFund))
                .add(BigDecimal.valueOf(outFrozenFund)).doubleValue();
        double totalFund = BigDecimals.add(useableFund, partFund, 2);
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
        detailVo.setInCash(agentMapper.getTotalInCash(agentId));

        /* 统计入金/出金/预约出金情况 */
        Integer intoKey = FundTypeEnum.INTO.getKey();  // 入金
        Integer outCashKey = FundTypeEnum.OUTCASH.getKey();  // 出金
        Integer outCashFrozenKey = FundTypeEnum.OUTCASH_FROZEN.getKey();  // 出金预约

        Map<Integer, BigDecimal> accMap = agentDao.calTodaySumAccountLogAccrual(agentId);

        // 累计入金
        Date lastInCashDate = agentDao.calTodayLastAccountLogDate(intoKey, agentId);
        if (accMap != null && !accMap.isEmpty()) {
            detailVo.setInCash(accMap.get(intoKey));
        }
        detailVo.setLastInCashDate(lastInCashDate);
        // 实际出金
        if (accMap != null && !accMap.isEmpty()) {
            detailVo.setOutCash(accMap.get(outCashKey));
        }
        // 申请出金日期
        Date date = agentDao.calTodayLastAccountLogDate(outCashFrozenKey, agentId);
        if (date != null) {
            String s = date.toString();
            String lastOutCashFrozenDateStr = s.substring(0, s.length() - 2);
            detailVo.setLastOutCashFrozenDateFm(lastOutCashFrozenDateStr);
        }
        // 申请出金
        if (accMap != null && !accMap.isEmpty()) {
            detailVo.setOutCashFrozen(accMap.get(outCashFrozenKey));
        }
        //已开通结算银行
        AgentVo bankHadOpen = getBankHadOpen(agentId);
        if (bankHadOpen != null) {
            String bankHadOpenStr = bankHadOpen.getBankIds();
            if (!StringUtil.isBlank(bankHadOpenStr)) {
                FinanceAccount.BankType[] values = FinanceAccount.BankType.values();
                for (FinanceAccount.BankType value : values) {
                    if (bankHadOpenStr.contains(value.getKey().toString())) {
                        bankHadOpenStr = bankHadOpenStr.replace(value.getKey().toString(), value.getCname());
                    }
                }
                detailVo.setBankHadOpen(bankHadOpenStr);
            }
        }
        //分配时间
        detailVo.setFlowDate(agent.getFlowDate());
        //年龄
        if (!StringUtil.isBlank(idCard)) {
            int age = CheckIdCardUtils.getAgeByIdCard(idCard);
            detailVo.setAge(age);
        }
        //出生月日
        if (!StringUtil.isBlank(idCard)) {
            String month = idCard.substring(10, 12);//月份
            String day = idCard.substring(12, 14);//日
            detailVo.setDateStr(month + "月" + day + "日");
        }
        //状态
        String statusStr = agent.getStatusStr();
        detailVo.setStatusStr(statusStr);
        //代理商客户类型
        detailVo.setCustomerType(agent.getCustomerType());
        //代理商意向度
        detailVo.setCustomerIntentionType(agent.getCustomerIntentionType());
        //协议资金（代理中的资金）
        detailVo.setAgentFound(BigDecimal.valueOf(amount));
        //冻结货款
        BigDecimal freezeFound = account.getFrozen().add(account.getOutFrozen());
        detailVo.setFreezeFound(freezeFound);
        return detailVo;
    }

    /* 私有方法：获取身份证地址所在地 */
    private String getIdCardAddr(AgentVo agent) {
        if (agent == null) {
            return null;
        }
        String linkPerson = agent.getLinkPerson();
        String idCard = agent.getIdCard();
        // 身份证所在地 -- 特殊处理：由于接口调用要收费，首先判断历史身份证地址如果为空，再调用，并更新数据库字段
        String idCardAddr = agent.getIdCardAddr();
        if (StringUtils.isBlank(idCardAddr)) {
            if (StringUtils.isNotBlank(linkPerson) && StringUtils.isNotBlank(idCard)) { // 调用接口获取
                idCardAddr = DataCheckAdapter.getIdCardAddr(idCard);
            }
            if (StringUtils.isNotBlank(idCardAddr)) {
                agent.setIdCardAddr(idCardAddr);
                agentDao.update(agent);
            }
        }
        if (StringUtils.isNotBlank(idCardAddr)) {
            int cityIndex = idCardAddr.indexOf("市");
            if (cityIndex != -1) {
                idCardAddr = idCardAddr.substring(0, cityIndex + 1);  // 只截取到市
            }
        }
        return idCardAddr;
    }
}

