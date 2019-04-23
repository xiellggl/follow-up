package com.dayi.follow.vo;

import com.dayi.common.util.NameItem;
import com.dayi.common.util.NameItems;
import com.dayi.user.manager.model.Permission;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/23
 */
public class PermissionVo extends Permission {

    /**
     * 权限(筛选)
     */
    private List<String> permissions;
    /**
     * 是否被模块绑定 1=绑定,0=未绑定
     */
    @Transient
    private Integer bindStatus;

    /**
     * 绑定状态 - 无效、禁用
     */
    public final static NameItem BIND_STATUS_DISABLE = NameItem.valueOf("未绑定", 0);
    /**
     * 绑定状态 - 有效、启用
     */
    public final static NameItem BIND_STATUS_NORMAL = NameItem.valueOf("已绑定", 1);
    /**
     * 绑定状态 - 全部状态
     */
    public final static NameItems BIND_STATUS_ALL = NameItems.valueOf(BIND_STATUS_DISABLE, BIND_STATUS_NORMAL);

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
