package com.dayi.follow.util;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;

public class Md5Util {

    public static String md5(String... strs) {
        String str = StringUtils.join(strs, ":");
        return Hashing.md5().hashBytes(str.getBytes()).toString();
    }
}
