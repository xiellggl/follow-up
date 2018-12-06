package com.dayi.follow.dao.follow;


import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.vo.PermissionVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/23
 */

public interface PermissionMapper extends BaseMapper<Permission> {


    /**
     * 根据权限ids获取权限
     * @param ids
     * @return
     */
     List<Permission> getByIds(List<String> ids);

    /**
     * 获取权限
     * @param id
     * @return
     */
     Permission get(String id);

    /**
     * 获取权限列表
     * @return
     */
    List<Permission> getList();

    /**
     * 根据系统标识获取权限列表
     * @param systemId
     * @return
     */
    List<Permission> getListBySystemId(String systemId);

    /**
     * 根据角色id，在rolePermission关系表中查出权限列表
     * @param roleId
     * @return
     */
    List<Permission> getPermissionByRoleId(String roleId);

    /**
     * 返回列表
     * @param permissionVo
     * @return
     */
    List<Permission> findList(PermissionVo permissionVo);


    /**
     * 取消对应模块下的权限关联
     * @param moduleId
     * @return
     */
    int updateModuleidByMId(String moduleId);
}
