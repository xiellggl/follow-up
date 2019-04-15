package com.dayi.follow.vo.highsea;

import com.dayi.follow.model.follow.Config;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiell
 * @date 2019/4/10
 */
public class HSConfigVo {
    private String id;

    //页面
    private String mark;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<String> getValues() {
        List<String> deptIds = new ArrayList();

        if (!StringUtils.isBlank(value)) {
            String[] split = StringUtils.split(value, ",");
            for (String s : split) {
                deptIds.add(s);
            }
        }
        return deptIds;
    }
}