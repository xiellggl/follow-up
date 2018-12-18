package com.dayi.follow.model.follow;

import com.dayi.common.util.NameItem;
import com.dayi.common.util.NameItems;
import com.dayi.mybatis.support.BaseModel;
import com.dayi.user.authorization.authz.PermissionBase;

import javax.persistence.Transient;


/**
 * @author xiell
 * @date 2018/11/23
 */
public class Permission extends BaseModel implements PermissionBase {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限标识/路径
     */
    private String url;

    /**
     * 模块id
     */
    private String moduleid;

    /**
     * 所属模块名称
     */
    @Transient
    private String moduleName;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 显示状态（0=隐藏，1=显示）
     */
    private Integer displayStatus;
    /**
     * 状态（0=正常，1=删除）
     */
    private Integer delStatus;

    /**
     * 状态（0=禁用，1=启用）
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;


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
    public final static NameItems STATUS_ALL = NameItems.valueOf(STATUS_DISABLE, STATUS_NORMAL);


    /**
     * 删除状态 - 正常
     */
    public final static NameItem DEL_STATUS_NO = NameItem.valueOf("正常", 0);

    /**
     * 删除状态 - 已删除
     */
    public final static NameItem DEL_STATUS_IS = NameItem.valueOf("删除", 1);

    /**
     * 删除状态 - 全部状态
     */
    public final static NameItems DEL_STATUS_ALL = NameItems.valueOf(DEL_STATUS_NO, DEL_STATUS_IS);


    /**
     * 显示状态 - 隐藏
     */
    public final static NameItem DISPLAY_STATUS_DISABLE = NameItem.valueOf("隐藏", 0);
    /**
     * 显示状态 - 显示
     */
    public final static NameItem DISPLAY_STATUS_NORMAL = NameItem.valueOf("显示", 1);
    /**
     * 显示状态 - 全部状态
     */
    public final static NameItems DISPLAY_STATUS_ALL = NameItems.valueOf(DISPLAY_STATUS_DISABLE, DISPLAY_STATUS_NORMAL);

    public Permission() {
    }

    public Permission(String id) {
        super(id);
    }

    public Permission(String id, String name, String url, Integer displayStatus) {
        super(id);
        this.name = name;
        this.url = url;
        this.displayStatus = displayStatus;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String getKey() {
        return url;
    }


    public Integer getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(Integer displayStatus) {
        this.displayStatus = displayStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public Permission setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public Integer getDelStatus() {
        return delStatus;
    }

    public Permission setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
        return this;
    }
    /**
     * 判断状态
     */
    public boolean isStatus(NameItem nameItem) {
        return this.status == nameItem.id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Permission setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Permission setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }
}
