package com.dayi.follow.service;


import com.dayi.follow.model.Menu;
import com.dayi.follow.model.Module;
import com.dayi.follow.model.Permission;
import com.dayi.follow.model.Role;
import com.dayi.follow.vo.PermissionVo;

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
}
