package com.dayi.follow.model.follow;

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
    private MarkType mark;
    private String value;

    public enum MarkType {
        HS_RANGE("公海范围"),
        PS_NUM("私海上限");

        private String value;

        MarkType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static String getValueByName(String name) {
            if (StringUtils.isBlank(name)) return "";
            MarkType[] values = MarkType.values();
            for (MarkType value : values) {
                if (name.equals(value.name())) {
                    return value.getValue();
                }
            }
            return "";
        }

    }

    public MarkType getMark() {
        return mark;
    }

    public void setMark(MarkType mark) {
        this.mark = mark;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}