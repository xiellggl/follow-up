package com.dayi.follow.service;

import com.dayi.follow.model.follow.Role;
import com.dayi.mybatis.support.Page;

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
     * 根据id查询角色(包括关联的权限)
     * @param id
     * @return
     */
    Role getById(String id);

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
     * 根据ids查询角色列表
     *
     * @param roleIds
     * @return
     */
    List<Role> queryRolesByIds(String roleIds);

    /**
     * 分页查询角色列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Role> searchRole(int pageNo, int pageSize);

    /**
     * 加载角色菜单
     * @return
     */
    List<Role> listAll(Boolean isOnlyShowEnable);

    /**
     * 删除角色
     * @param id
     */
    boolean delete(String id);

    /**
     * 启用/禁用角色
     * @param id
     * @param enable
     * @return
     */
    boolean updateStatus(String id, boolean enable);

}
