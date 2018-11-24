package com.dayi.follow.service.impl;


import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.dao.follow.PermissionMapper;
import com.dayi.follow.dao.follow.RolePermissionMapper;
import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.RolePermission;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.vo.PermissionVo;
import com.dayi.mybatis.support.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 跟进人 业务实现类
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    PermissionMapper permissionMapper;
    @Resource
    RolePermissionMapper rolePermissionMapper;

    @Override
    public void addBatch(String roleId, String[] permissionIds) {
        //然后新建角色权限关系数据
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission(rolePermissionMapper.getNewId(), roleId, permissionId);
            rolePermissions.add(rolePermission);
        }
        addBatch(rolePermissions);
    }

    @Override
    public void addBatch(List<RolePermission> rolePermissions) {
        rolePermissionMapper.addBatch(rolePermissions);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "权限管理", note = "添加权限")
    public boolean addPermission(Permission permission) {
        permission.setStatus(Permission.STATUS_NORMAL.id);
        permission.setDisplayStatus(Permission.DISPLAY_STATUS_DISABLE.id);
        permission.setDelStatus(Permission.DEL_STATUS_NO.id);
        permission.setId(permissionMapper.getNewId());
        permission.setModuleid("");
        return 1 == permissionMapper.add(permission);
    }

    @Override
    public void deleteRolePermission(String roleId) {
        rolePermissionMapper.deleteByRoleid(roleId);
    }

    @Override
    public Page<Permission> searchPermissions(Page<Permission> page, PermissionVo permission) {
        return permissionMapper.findPage(page, permission);
    }

    @Override
    public void bindModule(String moduleId, String permissionIds) {
        List<Permission> permissions = queryPermissionsByIds(permissionIds);
        for (Permission permission : permissions) {
            Permission p = new Permission(permission.getId());
            p.setModuleid(moduleId);
            updatePermission(p);
        }

    }

    @Override
    public List<Permission> queryPermissionsByIds(String permissionIds) {
        if (permissionIds == null) {
            return Collections.emptyList();
        }
        String[] permissionIdArr = permissionIds.split(",");
        return permissionMapper.getByIds(Arrays.asList(permissionIdArr));
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "权限管理", note = "权限更新")
    public boolean updatePermission(Permission permission) {
        permission.setUpdateTime(new Date());
        return 1 == permissionMapper.update(permission);
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionMapper.search();
    }

    @Override
    public List<Permission> getPermissionsByRoleId(String roleId) {
        return permissionMapper.getPermissionByRoleId(roleId);
    }

}

