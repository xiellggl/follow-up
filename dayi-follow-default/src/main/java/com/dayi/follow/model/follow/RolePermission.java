package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;
/**
 * @author xiell
 * @date 2018/11/12
 */
public class RolePermission extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 角色id
     */
    private String roleid;
    /**
     * 权限id
     */
    private String permissionid;

    public RolePermission() {}

    public RolePermission(String id, String roleid, String permissionid) {
        super(id);
        this.roleid = roleid;
        this.permissionid = permissionid;

    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid;
    }
}
