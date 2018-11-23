package com.dayi.follow.dao.follow;


import com.dayi.follow.model.RolePermission;
import com.dayi.mybatis.support.BaseMapper;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/23
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 根据角色id获取角色权限关系列表
     *
     * @param roleid
     * @return
     */
    List<RolePermission> getListRoleId(String roleid);

    /**
     * 根据角色id删除角色
     *
     * @param roleid
     * @return
     */
    String deleteByRoleid(String roleid);

    /**
     * 批量插入关系数据
     * @param list
     */
    void addBatch(List<RolePermission> list);
}
