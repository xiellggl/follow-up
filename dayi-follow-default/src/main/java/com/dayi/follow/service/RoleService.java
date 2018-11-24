package com.dayi.follow.service;


import com.dayi.follow.model.follow.Role;

import java.util.List;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface RoleService {
    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    Role getRole(String id);


    /**
     * 添加角色
     * @param role
     * @param permissionIds
     * @return
     */
    boolean addRole(Role role, String[] permissionIds);

    /**
     * 更新角色
     * @param role
     * @param permissionIds
     * @return
     */
    boolean updateRole(Role role, String[] permissionIds);


    /**
     * 删除角色信息
     * @param id
     * @return
     */
    boolean deleteRole(String id);

    /**
     * 根据ids查询角色列表
     *
     * @param roleIds
     * @return
     */
    List<Role> queryRolesByIds(String roleIds);
}
