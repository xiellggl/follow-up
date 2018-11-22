package com.dayi.follow.model;

import com.dayi.common.util.NameItem;
import com.dayi.common.util.NameItems;
import com.dayi.mybatis.support.BaseModel;

import javax.persistence.Transient;
import java.util.List;
/**
 * @author xiell
 * @date 2018/11/12
 */
public class Module extends BaseModel  {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    private String name;
    /**
     * 父id
     */
    private String parentid;
    /**
     * 跳转地址
     */
    private String url;

    /**
     * 状态（0=禁用，1=启用）
     */
    private Integer status;
    /**
     * 状态（0=正常，1=删除）
     */
    private Integer delStatus;
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
     * 子菜单
     */
    @Transient
    private List<Module> childModules;

    /**
     * 属性parentid-> 关联Module对象
     */
    @Transient
    private Module parentModule;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
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

    public List<Module> getChildModules() {
        return childModules;
    }

    public void setChildModules(List<Module> childModules) {
        this.childModules = childModules;
    }

    public Module getParentModule() {
        return parentModule;
    }

    public void setParentModule(Module parentModule) {
        this.parentModule = parentModule;
    }
}
