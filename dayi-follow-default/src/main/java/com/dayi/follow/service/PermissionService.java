package com.dayi.follow.service;

import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.RolePermission;
import com.dayi.follow.vo.PermissionVo;
import com.dayi.mybatis.support.Page;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/23
 */
public interface PermissionService {




    /**
     * 批量插入关系数据
     * @param roleId
     * @param permissionIds
     */
    void addBatch(String roleId, String[] permissionIds);


    /**
     * 添加权限信息
     * @param permission
     * @return
     */
    boolean addPermission(Permission permission);
    /**
     * 批量插入关系数据
     *
     * @param rolePermissions
     */
    void addBatch(List<RolePermission> rolePermissions);

    /**
     * 删除角色权限信息
     *
     * @param roleId
     */
    void deleteRolePermission(String roleId);

    /**
     * 分页
     *
     * @param page
     * @param permission
     * @return
     */
    Page<Permission> searchPermissions(Page<Permission> page, PermissionVo permission);

    /**
     * 绑定功能
     *
     * @param moduleId
     * @param permissionIds
     * @return
     */
    void bindModule(String moduleId, String permissionIds);

    /**
     * 根据ids查询权限列表
     *
     * @param permissionIds
     * @return
     */
    List<Permission> queryPermissionsByIds(String permissionIds);

    /**
     * 更新权限信息
     * @param permission
     * @return
     */
    boolean updatePermission(Permission permission);

    /**
     * 获取权限列表
     * @return
     */
    List<Permission> getPermissions();


    /**
     * 根据角色id,获取权限列表
     *
     * @param roleId
     * @return
     */
    List<Permission> getPermissionsByRoleId(String roleId);


}
