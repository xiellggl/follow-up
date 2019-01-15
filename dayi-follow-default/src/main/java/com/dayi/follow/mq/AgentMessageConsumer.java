package com.dayi.follow.mq;

import com.dayi.common.constant.DayiConstant;
import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.model.follow.*;
import com.dayi.follow.service.FollowAgentService;
import com.dayi.follow.service.OrgService;
import com.dayi.mq.Topic;
import com.dayi.mq.support.AbstractConsumer;
import com.dayi.mq.support.MessageExt;
import com.dayi.user.message.UserMessageConts;
import com.dayi.user.model.RegisterForm;
import com.dayi.user.model.User;
import com.dayi.user.model.UserRegistration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiell
 * @date 2018/12/11
 */
@Service
public class AgentMessageConsumer extends AbstractConsumer {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(AgentMessageConsumer.class);
    @Resource
    AgentMapper agentMapper;
    @Resource
    FollowUpMapper followUpMapper;
    @Resource
    FollowAgentMapper followAgentMapper;
    @Resource
    FollowAgentService followAgentService;
    @Resource
    OrgService orgService;

    public AgentMessageConsumer() {
        // 设置关注的topic
        Topic topic = new Topic(UserMessageConts.TOPIC_USER);
        topic.addTags(UserMessageConts.TAGS_USER_ADD);
        super.addAttentionTopic(topic);
    }

    @Override
    public boolean consumeMessage(MessageExt messageExt) {
        if (null == messageExt) return false;

        if (!UserMessageConts.TAGS_USER_ADD.equals(messageExt.getTags())) return true;

        // 监听添加用户消息
        log.info("接到添加用户消息，userid:" + messageExt.getKeys());
        UserRegistration registration = messageExt.getContent(UserRegistration.class);

        // 别的平台注册消息，不做处理。 当前平台注册来源finance(前端注册)、xinhuo(星火)和后台添加
        if (!DayiConstant.SystemId.SYSTEM_FINANCE.getName().equals(registration.getRegisterSource())
                && !DayiConstant.SystemId.SYSTEM_SYS.getName().equals(registration.getRegisterSource())
                && !DayiConstant.SystemId.SYSTEM_ANDROID.getName().equals(registration.getRegisterSource())
                && !DayiConstant.SystemId.SYSTEM_IOS.getName().equals(registration.getRegisterSource())
                && !"xinhuo".equals(registration.getRegisterSource())) {
            log.info("非塑如意用户，不作处理");
            return true;
        }

        Agent agent = agentMapper.getByUcId(registration.getUserId());
        if (agent == null) {
            log.info("agent不存在，不作处理！");
            return false;
        }

        //判断是否已经存在于跟进人系统中
        FollowAgent fAgent = followAgentMapper.getFollowAgentByAgentId(agent.getId());
        if (fAgent != null) {
            log.info("agent已经存在于跟进人系统中！");
            return true;
        }

        Map<String, String> extraData = registration.getExtraPara();
        if (null == extraData) {
            extraData = new HashMap<>();
        }

        String inviteCode = registration.getInviteCode();//这个有可能是跟进人的邀请码，也可能是城市服务商部门的邀请码,这个必有值

        String followCode = extraData.get("followCode");//当通过城市服务商注册时，这个就会有值，是跟进人的邀请码

        if (StringUtils.isBlank(inviteCode)) {//保持这边代理商数量与塑如意那边一致，利于某些统计
            try {
                doCreate(agent, registration);
                log.info("创建代理商完毕！");
                return true;
            } catch (Exception e) {
                log.error("创建代理商失败！", e);
                return false;
            }
        } else {
            //传的是跟进人的邀请码
            FollowUp followUp = new FollowUp();
            Organization org = orgService.getByInviteCode(inviteCode);
            if (org == null) {
                org = orgService.getByMarkerNum(inviteCode);
                if (org != null) {
                    if (StringUtils.isNotBlank(followCode)) {
                        followUp = followUpMapper.getByInviteCode(followCode);
                    }
                } else {
                    followUp = followUpMapper.getByInviteCode(inviteCode);
                }
                if (followUp != null) {
                    try {
                        doAssign(agent, followUp, registration);
                    } catch (Exception e) {
                        log.error("分配跟进人失败！", e);
                        return false;
                    }
                    log.info("代理商分配跟进人完毕！");
                    return true;
                }
            }

            //传的是代理商的邀请码
            Agent inviteAgent = agentMapper.getByInviteCode(inviteCode);

            if (inviteAgent != null && inviteAgent.getInviteLevel() < 2) {

                FollowAgent followAgent = followAgentMapper.getFollowAgentByAgentId(inviteAgent.getId());

                if (!StringUtils.isBlank(followAgent.getFollowId())) {
                    FollowUp followUp1 = followUpMapper.get(followAgent.getFollowId());
                    if (followUp1 != null && followUp1.getSwitchStatus() == 1) {

                        try {
                            doAssign(agent, followUp1, registration);
                        } catch (Exception e) {
                            log.error("分配跟进人失败！", e);
                            return false;
                        }
                        log.info("代理商分配跟进人完毕！");
                        return true;

                    }
                }

            }
        }
        log.info("非跟进人业务，不作处理！");
        return true;
    }

    private void doCreate(Agent agent, UserRegistration registration) {
        FollowAgent followAgent = new FollowAgent();
        followAgent.setId(followAgentMapper.getNewId());
        followAgent.setAgentId(agent.getId());
        followAgent.setCustomerType(AgentCusTypeEnum.NOT_LINK.getValue());
        followAgent.setAgentFundBefore(BigDecimal.ZERO);
        followAgent.setTotalFundBefore(BigDecimal.ZERO);
        followAgent.setCreateTime(registration.getRegisterTime());
        followAgent.setUpdateTime(registration.getRegisterTime());
        followAgentMapper.add(followAgent);
    }

    private void doAssign(Agent agent, FollowUp followUp, UserRegistration registration) {
        FollowAgent followAgent = new FollowAgent();
        followAgent.setId(followAgentMapper.getNewId());
        followAgent.setAgentId(agent.getId());
        followAgent.setFollowId(followUp.getId());
        followAgent.setCustomerType(AgentCusTypeEnum.NOT_LINK.getValue());
        followAgent.setAssignDate(registration.getRegisterTime());

        Account account = agentMapper.getAccount(agent.getId());
        if (account != null) {
            BigDecimal agentFund = account.getCargoInterest().multiply(BigDecimal.valueOf(0.8)).add(account.getCargoInterestPuchas());
            followAgent.setAgentFundBefore(agentFund);

            BigDecimal totalFund = account.getCargoInterest().multiply(BigDecimal.valueOf(0.8)).
                    add(account.getCargoInterestPuchas()).
                    add(account.getFrozen()).add(account.getUseable()).add(account.getOutFrozen());
            followAgent.setTotalFundBefore(totalFund);
        }

        followAgent.setCreateTime(registration.getRegisterTime());
        followAgent.setUpdateTime(registration.getRegisterTime());
        followAgentMapper.add(followAgent);
    }
}
