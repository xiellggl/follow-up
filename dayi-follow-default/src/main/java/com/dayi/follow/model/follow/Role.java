package com.dayi.follow.model.follow;

import com.dayi.common.util.NameItem;
import com.dayi.common.util.NameItems;
import com.dayi.mybatis.support.BaseModel;
import com.dayi.user.authorization.authz.RoleBase;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;


/**
 * @author xiell
 * @date 2018/11/23
 */
public class Role extends BaseModel implements RoleBase {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String descript;

    /**
     * 状态（-1=已删除，0=禁用，1=启用）
     */
    private Integer status;

    @Transient
    private List<Permission> permissionList;

    /**
     * 状态 - 已删除
     */
    public final static NameItem STATUS_DEL = NameItem.valueOf("已删除", -1);
    /**
     * 状态 - 无效、禁用
     */
    public final static NameItem STATUS_DISABLE = NameItem.valueOf("禁用", 0);
    /**
     * 状态 - 有效、启用
     */
    public final static NameItem STATUS_NORMAL = NameItem.valueOf("启用", 1);
    /**
     * 状态 - 全部状态
     */
    public final static NameItems STATUS_ALL = NameItems.valueOf(STATUS_DEL, STATUS_DISABLE, STATUS_NORMAL);

    public Role() {
    }

    public Role(String id) {
        super(id);
    }

    public Role(String id, String name) {
        super(id);
        this.name = name;

    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public Role setDescript(String descript) {
        this.descript = descript;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Role setStatus(Integer status) {
        this.status = status;
        return this;
    }
    /** 判断状态 */
    public boolean isStatus(NameItem nameItem){
        return this.status == nameItem.id;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
