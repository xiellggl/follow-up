package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.common.util.BizResult;
import com.dayi.common.util.DateUtil;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.dao.follow.FollowOrgMapper;
import com.dayi.follow.dao.follow.OrgContactMapper;
import com.dayi.follow.enums.DelStatusEnum;
import com.dayi.follow.enums.OrgTypeEnum;
import com.dayi.follow.enums.SwitchStatusEnum;
import com.dayi.follow.model.follow.OrgContact;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.util.CheckIdCardUtils;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.util.WordLetterUtil;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.vo.InviteCodeVo;
import com.dayi.follow.vo.OrgListVo;
import com.dayi.follow.model.follow.Organization;
import com.dayi.mybatis.support.Conditions;
import com.dayi.mybatis.support.Page;
import com.dayi.mybatis.support.ext.Restrictions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 机构商 业务实现类
 */
@Service
public class OrgServiceImpl implements OrgService {
    @Resource
    private OrgMapper orgMapper;
    @Resource
    private AgentMapper agentMapper;
    @Resource
    private OrgContactMapper orgContactMapper;
    @Resource
    private FollowOrgMapper followOrgMapper;
    @Resource
    private OrgService orgService;
    @Resource
    private CountService countService;
    @Resource
    private CountMapper countMapper;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;

    @Override
    public Organization getByMarkerNum(String makerNum) {
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.eq("maker_num", makerNum));
        conditions.add(Restrictions.eq("del_status", DelStatusEnum.Normal.getValue()));
        return orgMapper.getByConditions(conditions);
    }

    @Override
    public Organization getByInviteCode(String inviteCode) {
        Organization orgVo = null;
        if (StringUtil.isInteger(inviteCode)) {//判断邀请码是否为数字
            Integer inter = 0;
            try {
                inter = Integer.parseInt(inviteCode);
            } catch (Exception e) {
                return null;
            }
            return this.getManagerOrgByInviteCode(inter);
        } else {
            return this.getColligateOrgByInviteCode(inviteCode);
        }

    }

    @Override
    public Organization getManagerOrgByInviteCode(Integer inviteCode) {
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.eq("invite_code", inviteCode));
        conditions.add(Restrictions.eq("org_type", OrgTypeEnum.Manager.getValue()));
        conditions.add(Restrictions.eq("del_status", DelStatusEnum.Normal.getValue()));
        return orgMapper.getByConditions(conditions);
    }

    @Override
    public Organization getColligateOrgByInviteCode(String inviteCode) {
        InviteCodeVo vo = WordLetterUtil.getInviteCode(inviteCode);
        if (vo == null) return null;
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.eq("word_letter", vo.getWord()));
        conditions.add(Restrictions.eq("invite_code", vo.getCode()));
        conditions.add(Restrictions.eq("org_type", OrgTypeEnum.Colligate.getValue()));
        conditions.add(Restrictions.ne("del_status", DelStatusEnum.Delete.getValue()));
        return orgMapper.getByConditions(conditions);
    }

    @Override
    public double getManageFund(Integer orgId, Integer level) {
        return orgMapper.getManageFund(orgId, level);
    }

    @Override
    public BizResult addContact(OrgContact orgContact) {
        return 1 == orgContactMapper.add(orgContact) ? BizResult.succ(orgContact) : BizResult.FAIL;
    }

    @Override
    public Page<OrgListVo> findOrgPage(Page<OrgListVo> page, String mobile, String inviteCode, String followId) {
        List<OrgListVo> orgs = followOrgMapper.findOrgs(mobile, inviteCode, followId, dayiDataBaseStr, page.getStartRow(), page.getPageSize());

        for (OrgListVo item : orgs) {
            Date expirationDate = item.getExpirationDate();
            if (expirationDate == null) {//如果为空加1年
                DateTime dateTime = new DateTime(item.getCreateDate());
                expirationDate = dateTime.plusYears(1).toDate();
            }
            Integer daysBetween = DateUtil.between(new Date(), expirationDate);
            item.setDeadLineStr(String.valueOf(daysBetween) + "天");//会员期限

            item.setAge(CheckIdCardUtils.getAgeByIdCard(item.getIdCard()));//年龄

            Organization org = orgMapper.get(item.getId());
            Integer switchStatus = org.getSwitchStatus();

            if (switchStatus != null && org.getSwitchStatus().equals(SwitchStatusEnum.OPEN.getKey().intValue())) {//资管开启
                item.setAgentNum(agentMapper.findByOrgId(org.getId()).size());//管理代理商人數
                item.setValidAgentNum(countMapper.getOrgValidAgentNum(item.getId(), null));//有效代理商-包括2级
            }

            if (switchStatus != null && switchStatus.equals(SwitchStatusEnum.CLOSE.getKey().intValue())) {//二级资管关闭
                item.setAgentNum(agentMapper.findLevel2ByOrgId(item.getId()).size());//管理代理商人數
                item.setValidAgentNum(countMapper.getOrgValidAgentNum(item.getId(), 1));//有效代理商-只算1级
            }

            double oneLevel = orgService.getManageFund(org.getId(), 1);//一级代理商资产
            double twoLevel = 0;//二级代理商资产

            Integer secondIncomeSwitch = org.getSecondIncomeSwitch();
            if (secondIncomeSwitch != null && secondIncomeSwitch.equals(SwitchStatusEnum.OPEN.getKey().intValue())) {//如果开了二级收益开关
                twoLevel = orgService.getManageFund(org.getId(), 2);
            }

            double organizationAssets = BigDecimals.add(oneLevel, twoLevel);
            BigDecimal manageFund = BigDecimal.valueOf(organizationAssets);

            item.setManageFund(manageFund);//管理资产规模
        }
        return page.setResults(orgs);
    }

    @Override
    public Page<OrgListVo> findTeamOrgPage(Page<OrgListVo> page, String inviteCode, String followUp, String followId, String deptId) {
        return null;
    }
}

