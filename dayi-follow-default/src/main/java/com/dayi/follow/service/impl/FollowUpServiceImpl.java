package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.model.Permission;
import com.dayi.follow.model.Role;
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
import com.dayi.user.authorization.authz.RoleBase;
import com.dayi.user.authorization.authz.RolePermissionResult;
import com.dayi.user.authorization.authz.support.SimpleAuthorizationInfo;
import com.dayi.user.authorization.realm.Realm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 跟进人 业务实现类
 */
@Service
public class FollowUpServiceImpl implements FollowUpService, Realm {
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
        if (flowUp != null && flowUp.getPassword().equals(Md5Util.md5(username, password))) {

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(flowUp, String.valueOf(flowUp.getId()),
                    flowUp.getUserName(), loginIp);

            simpleAuthenticationInfo.addOptions(-AccountInfo.OPTIONS_SECOND_VALID);
            return simpleAuthenticationInfo;
        }
        return null;
    }

    @Override
    public AuthorizationInfo getAuthorizationInfo(AuthenticationInfo authenticationInfo) {
//        if (null == authenticationInfo) {
//            return null;
//        }
//        FollowUp followUp = (FollowUp) authenticationInfo.getPrincipal();
//        SimpleAuthorizationInfo authorizationInfoImpl = new SimpleAuthorizationInfo(this.getTargetClass(),this.getRealmId(),authenticationInfo);
        List<RolePermissionResult> rolePermissionResults = new ArrayList<>();
//        //根据id获取角色列表
//        List<Role> roles = roleService.queryRolesByIds(followUp.getRoleids());
//        for (RoleBase<String> role : roles) {
//            List<Permission> permissions = permissionService.getPermissionsByRoleId(role.getId());
//            RolePermissionResult ar = new RolePermissionResult(role, permissions);
//            rolePermissionResults.add(ar);
//        }
        SimpleAuthorizationInfo authorizationInfoImpl = new SimpleAuthorizationInfo(this.getTargetClass(), this.getRealmId(), authenticationInfo);
        //封装角色权限数据到AuthorizationInfoImpl

        Role role = new Role();
        role.setStatus(1);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());

        List<Role> roles = new ArrayList<Role>();
        roles.add(role);

        authorizationInfoImpl.setRoles(roles);

        Permission permission = new Permission();
        permission.setDisplayStatus(1);
        permission.setDelStatus(0);
        permission.setStatus(1);
        permission.setCreateTime(new Date());
        permission.setUpdateTime(new Date());
        permission.setUrl("/followup/index");

        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission);

        RolePermissionResult rolePermissionResult = new RolePermissionResult(role, permissions);
        rolePermissionResults.add(rolePermissionResult);

        authorizationInfoImpl.setRolePermissionResults(rolePermissionResults);

        return authorizationInfoImpl;//暂时模拟有权限
    }

    @Override
    public List<String> getWhitelistUrls() {
        return null;
    }

    @Override
    public List<String> getBlacklistUrls() {
        List<String> blackList = new ArrayList<String>();
        blackList.add("/followup/index");
        return blackList;
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

