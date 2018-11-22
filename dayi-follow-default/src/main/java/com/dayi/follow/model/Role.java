package com.dayi.follow.model;

import com.dayi.mybatis.support.BaseModel;

import javax.persistence.Transient;


/**
 * @author xiell
 * @date 2018/11/12
 */
public class Role extends BaseModel {

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



}
