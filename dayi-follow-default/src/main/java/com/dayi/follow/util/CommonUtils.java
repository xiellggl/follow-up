package com.dayi.follow.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

/**
 * @author xiell
 * @date 2019/4/10
 */
public class CommonUtils {
    public static String getStart(String betweenDate) {
        String s = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = betweenDate.split(" - ");
            s = split[0];
            DateTime parse = DateTime.parse(s, DateTimeFormat.forPattern("yyyy-MM-dd"));
            s = parse.millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        }
        return s;
    }

    public static String getEnd(String betweenDate) {
        String s = "";
        if (!StringUtils.isBlank(betweenDate)) {
            String[] split = betweenDate.split(" - ");
            s = split[1];
            DateTime parse = DateTime.parse(s, DateTimeFormat.forPattern("yyyy-MM-dd"));
            s = parse.millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        }
        return s;
    }

    public static String getHideName(String name, String idCard) {
        String s = "";
        if (!StringUtils.isBlank(name) && !StringUtils.isBlank(idCard)) {
            String substring = name.substring(0, 1);//截取姓氏
            String nameFormat = substring + CheckIdCardUtils.getCnGenderByIdCard(idCard);
            s = nameFormat;
        }
        return s;
    }

}