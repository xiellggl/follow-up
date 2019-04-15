package com.dayi.follow.vo.highsea;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xiell
 * @date 2019/4/10
 */
public class HSConfigQo {
    @NotNull(message = "{NotNull.id}")
    private String id;

    @NotEmpty(message = "{NotEmpty.HSConfigVo.deptIds}")
    private List deptIds;//部门范围
    @NotNull(message = "{NotNull.HSConfigVo.num}")
    private Integer num;//数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List deptIds) {
        this.deptIds = deptIds;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}