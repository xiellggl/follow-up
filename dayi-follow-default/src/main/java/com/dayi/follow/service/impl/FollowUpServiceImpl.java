package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.service.FollowUpService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.util.Md5Util;
import com.dayi.follow.vo.OrgVo;
import com.dayi.user.authorization.authc.AccountInfo;
import com.dayi.user.authorization.authc.AuthenticationInfo;
import com.dayi.user.authorization.authc.AuthenticationToken;
import com.dayi.user.authorization.authc.support.SimpleAuthenticationInfo;
import com.dayi.user.authorization.authc.support.UsernamePasswordToken;
import com.dayi.user.authorization.authz.AuthorizationInfo;
import com.dayi.user.authorization.realm.Realm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 跟进人 业务实现类
 */
@Service
public class FollowUpServiceImpl implements FollowUpService,Realm {
    @Resource
    private FollowUpMapper followUpMapper;
    @Resource
    private OrgService orgService;

    @Override
    public BizResult add(FollowUp followUp) {
        if (!this.checkUserName(followUp.getUserName(), followUp.getId())) {
            return BizResult.fail("用户名已存在！");
        }
        if (!this.checkCodeRepeat(followUp.getInviteCode())) {
            return BizResult.fail("邀请码已存在！");
        }
        followUp.setPassword(Md5Util.md5(followUp.getUserName(), followUp.getPassword()));
        followUpMapper.add(followUp);
       // updateDept(flowUp.getDeptId(), null, 1);//更新部门信息
        return BizResult.SUCCESS;
    }

    @Override
    public FollowUp get(String followUpId) {
        return followUpMapper.get(followUpId);
    }

    /**
     * 新增/修改 -- 跟进人 -- 校验账号是否重复
     */
    public boolean checkUserName(String userName, String id) {
        FollowUp flowUp = followUpMapper.getByUserName(userName);
        if (flowUp != null) {
            if (!StringUtils.isBlank(id)) {
                if (!id.equals(flowUp.getId())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 新增/修改 -- 跟进人 -- 校验邀请码是否重复
     */
    public boolean checkCodeRepeat(String inviteCode) {
        OrgVo orgVo = null;
        orgVo = orgService.getByMarkerNum(inviteCode);
        if (orgVo != null) {
            return false;
        }
        orgVo = orgService.getByInviteCode(inviteCode);
        if (orgVo != null) {
            return false;
        }
        FollowUp followUp = followUpMapper.getByInviteCode(inviteCode);
        if (followUp != null) {
            return false;
        }
        return true;
    }


    /**
     * 新增/修改 -- 跟进人 -- 校验邀请码是否重复
     */
//    public boolean checkCode(String code, String id) {
//        FollowUp flowUp = null;
//        if (!StringUtils.isBlank(id)) {
//            flowUp = flowUpMapper.get(id);
//            if (flowUp != null && flowUp.getInviteCode().equals(code)) {
//                return true;
//            }
//        }
//        BizRetVo biz = organizationService.findFollowUpCodeValid(code);
//        if (biz.isSucc()) {
//            return true;
//        }
//        return false;
//    }

//    public BizResult findFollowUpCodeValid(String inviteCode) {
//        Organization organization = findOrgByMarkerNum(inviteCode);
//        if (organization != null) {
//            biz.setError("该邀请码已经被创客使用!");
//            return biz;
//        }
//        Organization org = inviteOrganizatonService.findOrgnByInviteCode(inviteCode);
//        if (org != null) {
//            biz.setError("该邀请码已经被机构会员使用!");
//            return biz;
//        }
//        FollowUp flowUp = flowUpService.getFlowUpByCode(inviteCode);
//        if (flowUp != null) {
//            biz.setError("该邀请码已经被使用!");
//            return biz;
//        }
//        return biz;
//    }


    @Override
    public String getRealmId() {
        return String.valueOf(hashCode());
    }

    @Override
    public Class<? extends Realm> getTargetClass() {
        return getClass();
    }

    @Override
    public boolean isSupport(AuthenticationToken authToken) {
        return null != authToken && authToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) {
        String username = authenticationToken.getPrincipal();
        String password = authenticationToken.getCredentials();
        String loginIp = authenticationToken.getLoginIp();

        FollowUp flowUp = followUpMapper.getByUserName(StringUtils.trim(username));
        if (flowUp != null && flowUp.getPassword().equals(Md5Util.md5(username,password))) {

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(flowUp, String.valueOf(flowUp.getId()),
                    flowUp.getUserName(), loginIp);

            simpleAuthenticationInfo.addOptions(-AccountInfo.OPTIONS_SECOND_VALID);
            return simpleAuthenticationInfo;
        }
        return null;
    }

    @Override
    public AuthorizationInfo getAuthorizationInfo(AuthenticationInfo authenticationInfo) {
        return null;
    }

    @Override
    public List<String> getWhitelistUrls() {
        return null;
    }

    @Override
    public List<String> getBlacklistUrls() {
        return null;
    }

    @Override
    public String getUnAuthorizedUrl(String s) {
        return null;
    }

    @Override
    public String getUnAuthenticatedUrl(String s) {
        return null;
    }
}

