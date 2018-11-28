package com.dayi.follow.util;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 瑱彬.
 */
public class CollectionUtil {

    public static Float sumFloats(List<Float> list) {
        float sum = 0f;
        for (Float f : list) {
            if (f != null) {
                f = f * 100;
                sum += f;
            }
        }
        return new BigDecimal(sum)
                .divide(new BigDecimal(100))
                .floatValue();
    }

    public static Double sumDoubles(List<Double> list) {
        double sum = 0f;
        for (Double f : list) {
            if (f != null) {
                f = f * 100;
                sum += f;
            }
        }
        return new BigDecimal(sum)
                .divide(new BigDecimal(100))
                .doubleValue();
    }

    public static int sumInts(List<Integer> list) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum;
    }

    public static <T> String getStr(List<T> list) {
        StringBuffer sb = new StringBuffer("");
        if (CollectionUtils.isEmpty(list)) return "";
        for (T i : list) {
            sb.append(i);
            sb.append(",");
        }
        if (!list.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static List<Integer> getList(String s) {
        List<Integer> list = Lists.newArrayList();
        if (StringUtils.isNotBlank(s)) {
            String[] arr = s.split(",");
            if (ArrayUtils.isNotEmpty(arr)) {
                for (String i : arr) {
                    try {
                        int ii = Integer.valueOf(i);
                        list.add(ii);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
                return list;
            }
        }
        return list;
    }

    public static List<String> getStringList(String s) {
        List<String> list = Lists.newArrayList();
        if (StringUtils.isNotBlank(s)) {
            String[] arr = s.split(",");
            if (ArrayUtils.isNotEmpty(arr)) {
                for (String i : arr) {
                    list.add(i);
                }
                return list;
            }
        }
        return list;
    }
}
