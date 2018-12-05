package com.dayi.follow.service.impl;


import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.dao.follow.PermissionMapper;
import com.dayi.follow.dao.follow.RolePermissionMapper;
import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.RolePermission;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.util.Misc;
import com.dayi.follow.vo.sys.PermissionSearchVo;
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
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionMapper permissionMapper;

    @Resource
    RolePermissionMapper rolePermissionMapper;

    @Override
    public Page<Permission> searchPermissions(PermissionSearchVo permissionSearchVo) {

        Page<Permission> page = new Page<>();
        page.setPageNo(permissionSearchVo.getPageNo());
        page.setPageSize(permissionSearchVo.getPageSize());

        Conditions conditions = new Conditions();
        String name = permissionSearchVo.getName();
        Boolean isBinding = permissionSearchVo.getBinding();
        if (!Misc.isEmpty(name)) {
            conditions.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (null != isBinding) {
            if (isBinding) {
                conditions.add(Restrictions.isNotNull("moduleid"));
            } else {
                conditions.add(Restrictions.isNull("moduleid"));
            }
        }
        conditions.add(Restrictions.eq("del_status", Permission.DEL_STATUS_NO.id));

        return permissionMapper.searchByConditions(page, conditions);
    }

    @Override
    public Permission get(String id) {
        return permissionMapper.get(id);
    }

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
    public boolean deleteRolePermission(String roleId) {
        if (rolePermissionMapper.deleteByRoleid(roleId) == 0) {
            return false;
        }
        return true;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "权限管理", note = "绑定功能")
    @Transactional
    public boolean bindModule(String moduleId, String permissionIds) {
        List<Permission> permissions = queryPermissionsByIds(permissionIds);
        for (Permission permission : permissions) {
            permission.setModuleid(moduleId);
            if (!updatePermission(permission)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "权限管理", note = "解绑功能")
    @Transactional
    public boolean untyingModule(String permissionId) {

        // 权限取消模块关联
        Permission permission = new Permission();
        permission.setId(permissionId);
        permission.setModuleid("");
        permission.setUpdateTime(new Date());
        if (permissionMapper.update(permission) == 0) {
            return false;
        }

        // 删除角色权限关联
        rolePermissionMapper.deleteByPerId(permissionId);

        return true;
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

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "权限管理", note = "显示/隐藏功能")
    public boolean updateDisplace(String id, boolean displace) {
        Permission permission = new Permission();
        permission.setId(id);
        if (displace) {
            permission.setDisplayStatus(Permission.DISPLAY_STATUS_NORMAL.id);
        } else {
            permission.setDisplayStatus(Permission.DISPLAY_STATUS_DISABLE.id);
        }
        permission.setUpdateTime(new Date());
        if (permissionMapper.update(permission) == 0) {
            return false;
        }
        return true;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.DELETE, what = "权限管理", note = "权限删除")
    @Transactional
    public boolean delete(String id) {
        // 删除权限
        Permission permission = new Permission();
        permission.setId(id);
        permission.setUpdateTime(new Date());
        permission.setDelStatus(Permission.DEL_STATUS_IS.id);
        if (permissionMapper.update(permission) == 0) {
            return false;
        }

        // 删除权限角色关联
        rolePermissionMapper.deleteByPerId(id);

        return true;
    }

}

