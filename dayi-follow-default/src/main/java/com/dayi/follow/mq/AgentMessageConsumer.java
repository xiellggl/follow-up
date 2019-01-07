package com.dayi.follow.mq;

import com.dayi.common.constant.DayiConstant;
import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.model.follow.Account;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.FollowAgentService;
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

    public AgentMessageConsumer() {
        // 设置关注的topic
        Topic topic = new Topic(UserMessageConts.TOPIC_USER);
        topic.addTags(UserMessageConts.TAGS_USER_ADD);
        super.addAttentionTopic(topic);

        log.info("构造   AgentMessageConsumer ");
    }

    @Override
    public boolean consumeMessage(MessageExt messageExt) {
        log.info("consumeMessage ： {}",messageExt);
        if (null == messageExt) return false;

        if (!UserMessageConts.TAGS_USER_ADD.equals(messageExt.getTags())) return true;

        // 监听添加用户消息
        log.info("接到添加用户消息，userid:" + messageExt.getKeys());
        UserRegistration registration = messageExt.getContent(UserRegistration.class);

        Agent agent = agentMapper.getByUcId(registration.getUserId());
        if (agent == null) {
            log.info("agent不存在，不作处理");
            return false;
        }

        Map<String, String> extraData = registration.getExtraPara();
        if (null == extraData) {
            extraData = new HashMap<>();
        }

        String inviteCode = registration.getInviteCode();//这个有可能是跟进人的邀请码，也可能是城市服务商部门的邀请码,这个必有值

        String followCode = extraData.get("followCode");//当通过城市服务商注册时，这个就会有值，是跟进人的邀请码

        FollowUp followUp;

        if (StringUtils.isBlank(inviteCode)) {
            log.info("无邀请码，不作处理");
            return true;
        } else {
            if (!StringUtils.isBlank(followCode)) {//不为空则表明是城市服务商过来的
                followUp = followUpMapper.getByInviteCode(followCode);
            } else {
                followUp = followUpMapper.getByInviteCode(inviteCode);
            }
        }

        if (followUp == null) {
            log.info("非跟进人邀请码，不作处理");
            return true;
        }

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

        try {
            followAgentMapper.add(followAgent);
        } catch (Exception e) {
            log.error("分配跟进人失败！", e);
            return false;
        }
        log.info("代理商分配跟进人完毕");
        return true;
    }
}
