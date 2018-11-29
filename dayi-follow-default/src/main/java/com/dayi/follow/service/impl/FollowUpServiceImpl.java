package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.follow.DeptMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.*;
import com.dayi.follow.util.Md5Util;
import com.dayi.follow.model.follow.Organization;
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
    @Resource
    UserComponent userComponent;
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
    public BizResult addFollowUp(FollowUp followUp) {
        if (followUpMapper.add(followUp) != 1) {
            return BizResult.FAIL;
        }
        //处理部门关系
        Department department = deptService.get(followUp.getDeptId());
        if (null != department) {
            department.setPersonNum(department.getPersonNum() + 1);
            return deptService.updateDept(department);
        }
        return BizResult.FAIL;
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
        if (StringUtils.isBlank(inviteCode)) return false;
        Organization orgVo = null;
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

    @Override
    public BizResult update(FollowUp followUpVo) {
        FollowUp followUp = followUpMapper.get(followUpVo.getId());

        Department oldDept = deptMapper.get(followUp.getDeptId());
        oldDept.setPersonNum(oldDept.getPersonNum() - 1);

        Department newDept = deptMapper.get(followUpVo.getDeptId());
        newDept.setPersonNum(newDept.getPersonNum() + 1);

        deptMapper.update(oldDept);
        deptMapper.update(newDept);

        followUp.setName(followUpVo.getName());
        followUp.setMobile(followUpVo.getMobile());
        followUp.setDeptId(followUpVo.getDeptId());
        followUp.setRoleids(followUpVo.getRoleids());

        followUpMapper.update(followUp);

        return BizResult.SUCCESS;
    }

    /**
     * 修改密码 -- 跟进人信息
     */
    public BizResult editPwd(String id, String oldPwd, String newPwd, String confirmPwd) {
        FollowUp followUp = followUpMapper.get(id);
        if (followUp == null) {
            return BizResult.fail("用户信息不存在！");
        }

        if (!newPwd.equals(confirmPwd)) {
            return BizResult.fail("两次输入的密码不相同!");
        }

        if (!Md5Util.md5(followUp.getUserName(), oldPwd).equals(followUp.getPassword())) {
            return BizResult.fail("密码不正确!");
        }
        if (!StringUtils.isBlank(newPwd)) {
            followUp.setPassword(Md5Util.md5(followUp.getUserName(), newPwd));
        }

        return 1 == followUpMapper.update(followUp) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public List<String> findIdsByDeptId(String deptId) {
        return followUpMapper.findIdsByDeptId(deptId);
    }

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
        SimpleAuthorizationInfo authorizationInfoImpl = new SimpleAuthorizationInfo(this.getTargetClass(), this.getRealmId(), authenticationInfo);
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

