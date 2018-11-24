package com.dayi.follow.service.impl;


import com.dayi.common.util.Misc;
import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.dao.follow.RoleMapper;
import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 跟进人 业务实现类
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;
    @Resource
    PermissionService permissionService;

    @Override
    public Role getRole(String id) {
        return roleMapper.get(id);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.ADD, what = "角色管理", note = "添加角色")
    public boolean addRole(Role role, String[] permissionIds) {
        if (Misc.isEmpty(role.getId())) {
            role.setId(roleMapper.getNewId());
        }
        if (role.getStatus() == null) {
            role.setStatus(Role.STATUS_NORMAL.id);
        }
        role.setUpdateTime(new Date());
        if (1 == roleMapper.add(role)) {
            permissionService.addBatch(role.getId(), permissionIds);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "角色管理", note = "修改角色资料")
    public boolean updateRole(Role role, String[] permissionIds) {
        role.setUpdateTime(new Date());
        if (1 == roleMapper.update(role)) {
            //保存前，先删除已有角色权限关系数据
            permissionService.deleteRolePermission(role.getId());
            permissionService.addBatch(role.getId(), permissionIds);
            return true;
        } else {
            return false;
        }

    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.DELETE, what = "角色管理", note = "删除角色")
    public boolean deleteRole(String id) {
        Role role = new Role();
        role.setId(id);
        role.setStatus(Role.STATUS_DEL.id);
        role.setUpdateTime(new Date());
        return 1 == roleMapper.update(role);
    }

    @Override
    public List<Role> queryRolesByIds(String roleIds) {
        if (roleIds == null) {
            return new ArrayList<>();
        }
        String[] roleArr = roleIds.split(",");
        return roleMapper.getByIds(Arrays.asList(roleArr));
    }

}

