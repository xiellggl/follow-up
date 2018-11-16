package com.dayi.follow.vo;

import javax.persistence.Table;

/**
 * @author xiell
 * @date 2018/11/14
 */
@Table(name = "Organization")
public class OrgVo {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}