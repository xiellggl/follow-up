package com.dayi.follow.model.follow;

import com.dayi.common.util.NameItem;
import com.dayi.common.util.NameItems;
import com.dayi.mybatis.support.BaseModel;

import javax.persistence.Transient;
import java.util.List;


/**
 * @author xiell
 * @date 2018/11/23
 */
public class Module extends BaseModel implements Comparable<Module> {

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

    public Module() {
    }

    public Module(String id) {
        super(id);
    }

    public Module(String id, String name, String parentid) {
        super(id);
        this.name = name;
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public Module setParentid(String parentid) {
        this.parentid = parentid;
        return this;
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

    public Module setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public Module setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
        return this;
    }

    /**
     * 判断状态
     */
    public boolean isStatus(NameItem nameItem) {
        return this.status == nameItem.id;
    }
    /**
     * 判断删除状态
     */
    public boolean isDelStatus(NameItem nameItem) {
        return this.delStatus == nameItem.id;
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

    @Override
    public int compareTo(Module o) {
        if (o == null || o.getSort() == null) {
            return -1;
        }
        return this.getSort() - o.getSort();
    }
}
