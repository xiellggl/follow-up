package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.common.util.Misc;
import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.dao.follow.RoleMapper;
import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.service.RoleService;
import com.dayi.mybatis.support.Conditions;
import com.dayi.mybatis.support.Page;
import com.dayi.mybatis.support.ext.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Role getById(String id) {
        Role role = roleMapper.get(id);
        // 加载角色关联的权限
        if (null != role) {
            role.setPermissionList(permissionService.getPermissionsByRoleId(role.getId()));
        }
        return role;
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
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "角色管理", note = "启用/禁用角色")
    public boolean updateStatus(String id, boolean enable) {
        Role role = new Role();
        role.setId(id);
        if (enable) {
            role.setStatus(Role.STATUS_NORMAL.id);
        } else {
            role.setStatus(Role.STATUS_DISABLE.id);
        }
        role.setUpdateTime(new Date());
        if (roleMapper.update(role) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public List<Role> queryRolesByIds(String roleIds) {
        if (roleIds == null) {
            return new ArrayList<>();
        }
        String[] roleArr = roleIds.split(",");
        return roleMapper.getByIds(Arrays.asList(roleArr));
    }

    @Override
    public Page<Role> searchRole(int pageNo, int pageSize) {
        Page<Role> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        return roleMapper.search(page);
    }

    @Override
    public List<Role> listAll(Integer status) {
        Conditions conditions = new Conditions();
        if (null != status) {
            conditions.add(Restrictions.eq("status", status));
        } else {
            // 默认查询所有角色(包括非启用的角色)
            conditions.add(Restrictions.ne("status", Role.STATUS_DEL.id));
        }
        return roleMapper.searchByConditions(conditions);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.DELETE, what = "角色管理", note = "角色删除")
    public boolean delete(String id) {
        Role role = new Role();
        role.setId(id);
        role.setStatus(Role.STATUS_DEL.id);
        role.setUpdateTime(new Date());
        if (roleMapper.update(role) == 0) {
            return false;
        }
        return true;
    }

}

