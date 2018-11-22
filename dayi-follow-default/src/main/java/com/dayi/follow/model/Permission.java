package com.dayi.follow.model;

import com.dayi.mybatis.support.BaseModel;

/**
 * @author xiell
 * @date 2018/11/12
 */

public class Permission extends BaseModel {

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(Integer displayStatus) {
        this.displayStatus = displayStatus;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
