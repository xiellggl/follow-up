package com.dayi.follow.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * 基于Joda的日期转换注册器
 * @author leeton
 */
public class JodaDateEditor extends PropertyEditorSupport {

    private static DateTimeFormatter formatter;

    static {
        DateTimeParser[] parsers = {
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getParser(),
                DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
                DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").getParser(),
                DateTimeFormat.forPattern("yyyy/MM/dd").getParser(),
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss").getParser(),
                DateTimeFormat.forPattern("yyyy/MM/dd'T'HH:mm:ss.SSSZ").getParser(),
                DateTimeFormat.forPattern("yyyyMMddHHmmssZ").getParser(),
                DateTimeFormat.forPattern("EEE, d MMM yyyy HH:mm:ss Z").getParser(),
                DateTimeFormat.forPattern("EEE, MMM d, ''yy").getParser(),
                //可增加新的格式化字符串
        };
        formatter = new DateTimeFormatterBuilder().append( null, parsers ).toFormatter();
    }

    @Override
    public void setAsText(String text)  throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            setValue(null);
        } else {
            try {
                setValue(formatter.parseDateTime(text).toDate());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ?new DateTime(value).toString(formatter) : "");
    }


}
