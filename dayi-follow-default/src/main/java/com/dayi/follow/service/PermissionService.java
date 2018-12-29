package com.dayi.follow.service;

import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.RolePermission;
import com.dayi.follow.vo.PermissionVo;
import com.dayi.follow.vo.sys.PermissionSearchVo;
import com.dayi.mybatis.support.Page;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/23
 */
public interface PermissionService {

    /**
     * 分页条件查询功能列表
     * @param permissionSearchVo
     * @return
     */
    Page<Permission> searchPermissions(PermissionSearchVo permissionSearchVo);

    /**
     * 根据id查询功能
     * @param id
     * @return
     */
    Permission get(String id);

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
    BizResult addPermission(Permission permission);

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
    boolean deleteRolePermission(String roleId);

    /**
     * 绑定功能
     *
     * @param moduleId
     * @param permissionIds
     * @return
     */
    boolean bindModule(String moduleId, String permissionIds);

    /**
     * 解绑功能
     *
     * @param permissionId
     * @return
     */
    boolean untyingModule(String permissionId);

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
    BizResult updatePermission(Permission permission);

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

    /**
     * 显示/隐藏功能
     * @param id
     * @param displace
     * @return
     */
    BizResult updateDisplace(String id, boolean displace);

    /**
     * 删除权限
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 加载所有一级权限
     * @return
     */
    List<Permission> listParent();
}
