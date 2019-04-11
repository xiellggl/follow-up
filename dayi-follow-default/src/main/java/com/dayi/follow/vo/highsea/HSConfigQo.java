package com.dayi.follow.vo.highsea;

import org.hibernate.validator.constraints.NotEmpty;

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
    private List<String> deptIds;//部门范围
    @NotNull(message = "{NotNull.HSConfigVo.num}")
    private Integer num;//数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<String> deptIds) {
        this.deptIds = deptIds;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}