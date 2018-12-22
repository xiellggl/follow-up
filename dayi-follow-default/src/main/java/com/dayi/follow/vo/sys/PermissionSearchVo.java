package com.dayi.follow.vo.sys;

/**
 * 功能列表查询条件封装Vo
 *
 * @author zhoujiakai
 * @date 2018/12/05
 */
public class PermissionSearchVo {

    /**
     * 页码
     */
    private int pageNo;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 功能名称
     */
    private String name;

    /**
     * 是否已绑定
     */
    private Boolean isBinding;

    /**
     * 所属模块id
     */
    private String moduleId;

    /**
     * 父id
     */
    private String parentId;

    public int getPageNo() {
        return pageNo > 0 ? pageNo : 1;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize > 0 ? pageSize : 10;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getBinding() {
        return isBinding;
    }

    public void setBinding(Boolean binding) {
        isBinding = binding;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "PermissionSearchVo{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", name='" + name + '\'' +
                ", isBinding=" + isBinding +
                ", moduleId='" + moduleId + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
