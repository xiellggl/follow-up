package com.dayi.follow.model.follow;

import com.dayi.common.util.NameItem;
import com.dayi.mybatis.support.BaseModel;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xiell
 * @date 2019/4/10
 */
public class Config extends BaseModel {
    private String mark;
    private String value;

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