package com.dayi.follow.vo;

import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.AgentIntenTypeEnum;
import com.dayi.follow.util.CheckIdCardUtils;
import com.dayi.follow.util.Misc;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class AgentVo {
    private Integer inviteLevel;//邀请等级

    public Integer getInviteLevel() {
        return inviteLevel;
    }

    public void setInviteLevel(Integer inviteLevel) {
        this.inviteLevel = inviteLevel;
    }
}