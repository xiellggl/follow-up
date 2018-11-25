package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.follow.DeptMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.*;
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
import org.springframework.beans.factory.annotation.Autowired;
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
    @Resource
    PermissionService permissionService;
    @Resource
    DeptMapper deptMapper;
    @Resource
    DeptService deptService;
    @Resource
    RoleService roleService;
    /**
     * 黑名单列表
     */
    private List<String> blackList;
    private List<Permission> permissions;

    @Autowired
    public FollowUpServiceImpl(PermissionService permissionService) {
        this.blackList = new ArrayList<>();
        this.permissions = permissionService.getPermissions();
        for (Permission permission : this.permissions) {
            if (permission != null) {
                String url = permission.getKey();
                this.blackList.add(url);
            }
        }
    }

    @Override
    public BizResult add(FollowUp followUp) {
        followUpMapper.add(followUp);
        Department department = deptService.get(followUp.getDeptId());
        if (null != department) {
            department.setPersonNum(department.getPersonNum() + 1);
        }
        return deptService.updateDept(department) ? BizResult.SUCCESS : BizResult.FAIL;
    }


    @Override
    public FollowUp get(String followUpId) {
        return followUpMapper.get(followUpId);
    }

    @Override
    public FollowUp getByUserName(String userName) {
        return followUpMapper.getByUserName(userName);
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
        if (null == authenticationInfo) {
            return null;
        }
        FollowUp followUp = (FollowUp) authenticationInfo.getPrincipal();
        SimpleAuthorizationInfo authorizationInfoImpl = new SimpleAuthorizationInfo(this.getTargetClass(),this.getRealmId(),authenticationInfo);
        List<RolePermissionResult> rolePermissionResults = new ArrayList<>();
        //根据id获取角色列表
        List<Role> roles = roleService.queryRolesByIds(followUp.getRoleids());
        for (RoleBase<String> role : roles) {
            List<Permission> permissions = permissionService.getPermissionsByRoleId(role.getId());
            RolePermissionResult ar = new RolePermissionResult(role, permissions);
            rolePermissionResults.add(ar);
        }
        //封装角色权限数据到AuthorizationInfoImpl
        authorizationInfoImpl.setRoles(roles);
        authorizationInfoImpl.setRolePermissionResults(rolePermissionResults);

        return authorizationInfoImpl;
    }

    @Override
    public List<String> getWhitelistUrls() {
        return null;
    }

    @Override
    public List<String> getBlacklistUrls() {
        return this.blackList;
    }

    @Override
    public String getUnAuthorizedUrl(String s) {
        return "/followup/login";
    }

    @Override
    public String getUnAuthenticatedUrl(String s) {
        return "/followup/login";
    }
}

