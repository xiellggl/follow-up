package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.dao.follow.PermissionMapper;
import com.dayi.follow.dao.follow.RolePermissionMapper;
import com.dayi.follow.model.follow.Module;
import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.RolePermission;
import com.dayi.follow.service.ModuleService;
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
    ModuleService moduleService;

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

        page = permissionMapper.searchByConditions(page, conditions);

        for(Permission permission : page.getResults()) {
            // 设置模块名称
            Module module = moduleService.getModule(permission.getModuleid());
            if (null != module) {
                permission.setModuleName(module.getName());
            }

            // 设置父权限名称
            String parentId = permission.getParentid();
            if (!Misc.isEmpty(parentId)) {
                Permission parent = get(parentId);
                if (null != parent) {
                    permission.setParent(parent.getName());
                }
            }
        }

        return page;
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
        permission.setDelStatus(Permission.DEL_STATUS_NO.id);
        permission.setId(permissionMapper.getNewId());
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
            permission.setUpdateTime(new Date());
            if (permissionMapper.update(permission) == 0) {
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
    @Transactional
    public BizResult updatePermission(Permission permission) {
        Permission findPermission = permissionMapper.get(permission.getId());
        if (null == findPermission) {
            return BizResult.fail("功能模块不存在.");
        }

        // 所属模块若有变更则删除角色权限关联
        if (!Misc.isEmpty(permission.getModuleid()) && !findPermission.getModuleid().equals(permission.getModuleid())) {
            // 删除角色权限关联
            rolePermissionMapper.deleteByPerId(findPermission.getId());
        }

        permission.setUpdateTime(new Date());
        return 1 == permissionMapper.update(permission) ? BizResult.SUCCESS : BizResult.FAIL;
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
    public BizResult updateDisplace(String id, boolean displace) {
        Permission permission = permissionMapper.get(id);
        if (null == permission) {
            return BizResult.fail("功能模块不存在.");
        }

        if (displace) {
            permission.setDisplayStatus(Permission.DISPLAY_STATUS_NORMAL.id);
        } else {
            permission.setDisplayStatus(Permission.DISPLAY_STATUS_DISABLE.id);
        }
        permission.setUpdateTime(new Date());
        if (permissionMapper.update(permission) == 0) {
            return BizResult.FAIL;
        }
        return BizResult.SUCCESS;
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

    @Override
    public List<Permission> listParent() {
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.eq("del_status", Permission.DEL_STATUS_NO.id));
        conditions.add(Restrictions.eq("parentid", ""));
        return permissionMapper.searchByConditions(conditions);
    }

}

