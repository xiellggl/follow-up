package com.dayi.follow.util;

import java.util.regex.Pattern;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class StringUtil {
    /**
     * 功能描述：判断是否为整数
     * @param str   传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        return pattern.matcher(str).matches();
    }
}