package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.dao.follow.*;
import com.dayi.follow.enums.MemberStatusEnum;
import com.dayi.follow.model.follow.*;
import com.dayi.follow.service.*;
import com.dayi.follow.util.Md5Util;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.user.UserEditDto;
import com.dayi.follow.vo.user.UserVo;
import com.dayi.mybatis.support.Page;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 跟进人 业务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrgService orgService;
    @Resource
    PermissionService permissionService;
    @Resource
    DeptMapper deptMapper;
    @Resource
    DeptService deptService;
    @Resource
    FollowAgentMapper followAgentMapper;
    @Resource
    FollowOrgMapper followOrgMapper;
    @Resource
    RoleService roleService;
    @Resource
    RoleMapper roleMapper;
    @Resource
    PermissionMapper permissionMapper;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;
    /**
     * 黑名单列表
     */
    private List<String> blackList = new ArrayList<>();

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.ADD, what = "用户管理", note = "添加用户")
    public BizResult add(FollowUp followUp) {
        followUp.setId(userMapper.getNewId());
        followUp.setDisable(MemberStatusEnum.ENABLE.getValue());
        followUp.setCreateTime(new Date());
        followUp.setUpdateTime(new Date());
        if (userMapper.add(followUp) != 1) {
            return BizResult.FAIL;
        }
        //处理部门关系
        Department department = deptService.getDept(followUp.getDeptId());

        doAddPerson(department);
        return BizResult.SUCCESS;
    }


    @Override
    public FollowUp get(String followUpId) {
        return userMapper.get(followUpId);
    }

    @Override
    public FollowUp getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }


    /**
     * 新增/修改 -- 跟进人 -- 校验邀请码是否重复
     */
    public boolean checkCodeRepeat(String inviteCode) {
        if (StringUtils.isBlank(inviteCode)) return false;
        Organization orgVo;
        orgVo = orgService.getByMarkerNum(inviteCode);
        if (orgVo != null) {
            return false;
        }
        orgVo = orgService.getByInviteCode(inviteCode);
        if (orgVo != null) {
            return false;
        }
        FollowUp followUp = userMapper.getByInviteCode(inviteCode);
        if (followUp != null) {
            return false;
        }
        return true;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "用户管理", note = "修改用户")
    public BizResult update(UserEditDto userEditDto) {
        FollowUp followUp = userMapper.get(userEditDto.getId());
        if (null == followUp) {
            return BizResult.fail("所选用户不存在.");
        }
        Department oldDept = deptMapper.getDept(followUp.getDeptId());
        if (null == oldDept) {
            return BizResult.fail("原部门不存在.");
        }

        Department newDept = deptMapper.getDept(userEditDto.getDeptId());
        if (null == newDept) {
            return BizResult.fail("所选部门不存在.");
        }

        if (!userEditDto.getDeptId().equals(followUp.getDeptId())) {//如果部门有更改
            doUpdatePerson(oldDept, userEditDto.getDeptId());
        }

        UserEditDto.dtoToEntity(userEditDto, followUp);
        userMapper.updateAll(followUp);

        return BizResult.SUCCESS;
    }

    /**
     * 修改密码 -- 跟进人信息
     */
    public BizResult editPwd(String id, String oldPwd, String newPwd, String confirmPwd) {
        FollowUp followUp = userMapper.get(id);
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

        return 1 == userMapper.updateAll(followUp) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public Page<UserVo> findPage(Page<UserVo> page, String name, String queryDeptId, String inviteCode) {

        page = userMapper.findPage(page, name, queryDeptId, inviteCode);

        for (UserVo vo : page.getResults()) {
            //处理角色名称
            if (StringUtils.isBlank(vo.getRoleids())) continue;
            String[] split = StringUtils.split(vo.getRoleids(), ",");
            for (String roleId : split) {
                Role role = roleMapper.get(roleId);
                if (role == null) break;
                vo.setRolesName(role.getName() + ",");
            }
            //处理最后一个,
            if (!StringUtils.isBlank(vo.getRolesName())) {
                vo.setRolesName(vo.getRolesName().substring(0, vo.getRolesName().length() - 1));
            }
        }

        return page;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "用户管理", note = "重置密码")
    public BizResult resetPwd(FollowUp followUp, String newPwd) {
        followUp.setPassword(Md5Util.md5(followUp.getUserName(), newPwd));
        followUp.setUpdateTime(new Date());
        return 1 == userMapper.updateAll(followUp) ? BizResult.SUCCESS : BizResult.FAIL;

    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "用户管理", note = "启用用户")
    public BizResult enable(FollowUp followUp) {
        followUp.setDisable(MemberStatusEnum.ENABLE.getValue());
        followUp.setUpdateTime(new Date());
        userMapper.updateAll(followUp);

        Department department = deptMapper.getDept(followUp.getDeptId());

        this.doAddPerson(department);
        return BizResult.SUCCESS;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "用户管理", note = "禁用用户")
    public BizResult disable(FollowUp followUp) {
        followUp.setDisable(MemberStatusEnum.DISABLE.getValue());
        followUp.setUpdateTime(new Date());
        userMapper.updateAll(followUp);

        Department department = deptService.getDept(followUp.getDeptId());

        this.doReducePerson(department);

        return BizResult.SUCCESS;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.DELETE, what = "用户管理", note = "删除用户")
    public BizResult delete(FollowUp followUp) {
        List<Agent> agents = followAgentMapper.findAgentsByFollowId(followUp.getId(), dayiDataBaseStr);

        List<Organization> orgs = followOrgMapper.findOrgsByfollowId(followUp.getId(), null, dayiDataBaseStr);

        if (!agents.isEmpty() || !orgs.isEmpty()) return BizResult.fail("该账号存在跟进客户,无法删除！");
        userMapper.delete(followUp);

        Department department = deptService.getDept(followUp.getDeptId());

        this.doReducePerson(department);
        return BizResult.SUCCESS;
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

        FollowUp flowUp = userMapper.getByUserName(StringUtils.trim(username));
        if (flowUp != null && flowUp.getPassword().equals(Md5Util.md5(username, password))) {

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(flowUp, String.valueOf(flowUp.getId()),
                    flowUp.getUserName(), loginIp);

            //simpleAuthenticationInfo.addOptions(-AccountInfo.OPTIONS_SECOND_VALID);
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
        List<Permission> list = permissionMapper.getList();
        for (Permission permission : list) {
            if (permission.getDelStatus() != 1) {
                this.blackList.add(permission.getUrl());
            }
        }
        return this.blackList;
    }

    @Override
    public String getUnAuthorizedUrl(String s) {
        return "/user/login";
    }

    @Override
    public String getUnAuthenticatedUrl(String s) {
        return "/user/login";
    }

    @Override
    public void doAddPerson(Department department) {
        if (null != department) {
            department.setPersonNum(department.getPersonNum() + 1);
            deptMapper.updateAll(department);
            Department parentDept = department.getParentDept();
            //处理上级部门
            while (parentDept != null) {
                parentDept.setPersonNum(parentDept.getPersonNum() + 1);
                deptMapper.updateAll(parentDept);
                parentDept = parentDept.getParentDept();
            }
        }
    }

    @Override
    public void doReducePerson(Department department) {
        if (null != department) {
            department.setPersonNum(department.getPersonNum() - 1);
            deptMapper.updateAll(department);
            Department parentDept = department.getParentDept();
            //处理上级部门
            while (parentDept != null) {
                parentDept.setPersonNum(parentDept.getPersonNum() - 1);
                deptMapper.updateAll(parentDept);
                parentDept = parentDept.getParentDept();
            }
        }
    }

    /**
     * @Description: 传新部门对象，数据可能是旧的，所以传id
     * @Params: [department, newId]
     * @return: void
     * @Author: xiell
     * @Date: 2018/12/10
     */
    @Override
    public void doUpdatePerson(Department oldDept, String newId) {
        while (oldDept != null) {
            oldDept.setPersonNum(oldDept.getPersonNum() - 1);
            deptMapper.updateAll(oldDept);
            oldDept = oldDept.getParentDept();
        }

        Department newDept = deptMapper.getDept(newId);
        while (newDept != null) {
            newDept.setPersonNum(newDept.getPersonNum() + 1);
            deptMapper.updateAll(newDept);
            newDept = newDept.getParentDept();
        }
    }

    @Override
    public BizResult login(LoginVo loginVo) {
        FollowUp user = userMapper.getByUserName(loginVo.getUsername());
        if (user == null) return BizResult.fail("用户不存在！");
        if (user.getDisable() != MemberStatusEnum.ENABLE.getValue()) return BizResult.fail("账号已被禁用！");
        return BizResult.SUCCESS;
    }

}

