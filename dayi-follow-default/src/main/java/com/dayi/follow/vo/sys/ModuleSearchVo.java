package com.dayi.follow.vo.sys;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 模块列表查询条件封装Vo
 *
 * @author zhoujiakai
 * @date 2018/12/04
 */
public class ModuleSearchVo {

    /** 页码 */
    private int pageNo;

    /** 每页条数 */
    private int pageSize;

    /** 功能名称 */
    private String name;

    /** 状态 */
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ModuleSearchVo{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
