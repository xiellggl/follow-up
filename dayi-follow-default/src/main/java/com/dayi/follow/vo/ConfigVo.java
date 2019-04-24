package com.dayi.follow.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xiell
 * @date 2019/4/10
 */
public class ConfigVo {
    @NotBlank
    private String mark;

    @NotBlank
    private String value;//部门范围

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}