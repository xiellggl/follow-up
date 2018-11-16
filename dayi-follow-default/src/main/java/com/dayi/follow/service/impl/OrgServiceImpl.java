package com.dayi.follow.service.impl;


import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.enums.DelStatusEnum;
import com.dayi.follow.enums.OrgTypeEnum;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.util.WordLetterUtil;
import com.dayi.follow.vo.InviteCodeVo;
import com.dayi.follow.vo.OrgVo;
import com.dayi.mybatis.support.Conditions;
import com.dayi.mybatis.support.ext.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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


    @Override
    public OrgVo getByMarkerNum(String makerNum) {
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.eq("maker_num", makerNum));
        conditions.add(Restrictions.eq("del_status", DelStatusEnum.Normal.getValue()));
        return orgMapper.getByConditions(conditions);
    }

    @Override
    public OrgVo getByInviteCode(String inviteCode) {
        OrgVo orgVo = null;
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
    public OrgVo getManagerOrgByInviteCode(Integer inviteCode) {
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.eq("invite_code", inviteCode));
        conditions.add(Restrictions.eq("org_type", OrgTypeEnum.Manager.getValue()));
        conditions.add(Restrictions.eq("del_status", DelStatusEnum.Normal.getValue()));
        return orgMapper.getByConditions(conditions);
    }

    @Override
    public OrgVo getColligateOrgByInviteCode(String inviteCode) {
        InviteCodeVo vo = WordLetterUtil.getInviteCode(inviteCode);
        if (vo == null) return null;
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.eq("word_letter", vo.getWord()));
        conditions.add(Restrictions.eq("invite_code", vo.getCode()));
        conditions.add(Restrictions.eq("org_type", OrgTypeEnum.Colligate.getValue()));
        conditions.add(Restrictions.ne("del_status", DelStatusEnum.Delete.getValue()));
        return orgMapper.getByConditions(conditions);
    }




}

