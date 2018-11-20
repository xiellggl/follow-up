package com.dayi.follow.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Random;

/**
 * Misc 工具类
 * 
 * @author chenzhaoju
 * 
 */
public class Misc {

    /** 日志记录器 */
    public static final Logger _Logger = LoggerFactory.getLogger(Misc.class);

    /** UTF-16的最后一个字符 */
    public static final char UNICODE_REPLACEMENT_CHAR = 0xfffd;
    /** UTF-16的最后一个字符 */
    public static final String UNICODE_REPLACEMENT_STRING = String.valueOf(UNICODE_REPLACEMENT_CHAR);

    /** 用来将字节转换成 16 进制表示的字符表 */
    public static final char _hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /** 随机数表 - 大写字母 */
    public static final String RANDOM_TABLE_UPPERCASE = "ABCDEFGHIJKLNMOPQRSTUVWXYZ";
    /** 随机数表 - 小写字母 */
    public static final String RANDOM_TABLE_LOWERCASE = "abcdefghijklnmopqrstuvwxyz";
    /** 随机数表 - 数字 */
    public static final String RANDOM_TABLE_NUMBER = "0123456789";
    /** 随机数表 - 大小写字母 */
    public static final String RANDOM_TABLE_WORD = RANDOM_TABLE_UPPERCASE + RANDOM_TABLE_LOWERCASE;
    /** 随机数表 - 字母+数字 */
    public static final String RANDOM_TABLE_ALL = RANDOM_TABLE_WORD + RANDOM_TABLE_NUMBER;
    public static final Random _Random = new Random();

    private Misc() {}

    /**
     * 比较两字串是否相同
     * 
     * @param s1 字串1
     * @param s2 字串2
     * @return 相同则返回true
     */
    public static final boolean eq(String s1, String s2) {
        if (s1 == s2) {
            return true;
        }
        return (null != s1 && s1.equals(s2));
    }

    /**
     * 判断两个数字是否相等
     * @param int1
     * @param int2
     * @return
     */
    public static final boolean eq(Integer int1,Integer int2){
        if(int1 == int2){
            return true ;
        }
        if(null == int1 || null == int2){
            return false;
        }
        return int1.intValue() == int2.intValue();
    }

    /**
     * 生成一个随机数
     * 
     * @param len 生成串的长度
     * @param table 串的内容的 种子表
     * @return
     */
    public static String genRondomString(int len, String table) {
        if (null == table || 0 == table.length()) {
            table = RANDOM_TABLE_ALL;
        }
        int size = table.length();
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = table.charAt(_Random.nextInt(size));
        }
        return new String(chars);
    }

    /**
     * HEX编码
     * 
     * @param data 字节数组
     * @return HEX编码串
     */
    public static String encodeHex(byte[] data) {
        if (null == data) {
            return null;
        }
        char[] hex = new char[data.length * 2];
        int k = 0;
        for (int i = 0; i < data.length; i++) {
            // 转换成 16 进制字符的转换
            byte bt = data[i]; // 取第 i 个字节
            hex[k++] = _hexDigits[(bt >> 4) & 0x0F]; // 取字节中高 4 位的数字转换
            hex[k++] = _hexDigits[bt & 0x0F]; // 取字节中低 4 位的数字转换
        }
        return new String(hex);
    }

    /**
     * 判断字符是否为邮箱地址格式
     * 
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        if (Misc.isEmpty(str)) {
            return false;
        }
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,5}$";
        return str.matches(regex);
    }

    /**
     * 判断字符串是否为 "" || null
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || 0 == str.length();
    }

    /**
     *
     * 判断是否为空集合
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection){
        return null == collection || 0 == collection.size();
    }

    /**
     * 是否数字
     * 
     * @param ch 字符
     * @return 是则返回true
     */
    public static final boolean isNumber(char ch) {
        return (ch > 0x30 - 1 && ch < 0x39 + 1);
    }

    public static final boolean isNumber(String str) {
        if (null == str || 0 == str.length()) {
            return false;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (!isNumber(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isNumber(char[] chars, int offset, int count) {
        count += offset;
        for (int i = offset; i < count; i++) {
            if (!isNumber(chars[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /*String str = "sunny";
        String em = "suse33dq@q3qee.comdse";
        _Logger.info(md5Hash(str));
        _Logger.info(md5Hash(md5Hash(str)));
        _Logger.info(shaHash(str));
        _Logger.info("" + isEmail(em));
        _Logger.info(toHex64((System.currentTimeMillis() - 946721219851L) / 4));*/
        // 533c5ba8368075db8f6ef201546bd71a
        // System.out.println();
        // System.out.println(BigDecimal.valueOf(0.008).divide(BigDecimal.valueOf(1000000)).stripTrailingZeros().toPlainString());
        // System.out.println(BigDecimal.valueOf(0.008).multiply(BigDecimal.valueOf(0.000000001)).stripTrailingZeros().toPlainString());
        /*double bailRatio = 0.1; // 默认：代销风险保证金比例
        String[] numbers = new String[]{"CD170819000088","CD170909000185","CD170804000020","CD170806000003","CD170806000069","CD170806000057","CD170804000021","CD170806000029","CD170805000063","CD170806000013","CD170806000028","CD170806000058","CD170806000059","CD170913000011","CD171016000018","CD170919000123","CD170806000070","CD170806000011","CD170724000015","CD170728000016","CD170804000006","CD170806000027","CD170806000040","CD170806000044","CD170806000045","CD170806000066","CD170818000080","CD171018000023","CD170806000052","CD170806000041","CD170814000028","CD171010000278","CD170805000055","CD170806000036","CD170808000116","CD170806000018","CD170805000058","CD170806000026","CD170811000203","CD170908000176","CD170804000036","CD170805000047","CD170805000068","CD170806000004","CD170908000174","CD170806000010","CD170806000067","CD170804000034","CD170806000034","CD170822000117","CD170807000104","CD170806000042","CD170806000047","CD170806000065","CD170807000100","CD170925000076","CD170806000039","CD170806000061","CD170805000054","CD170806000054","CD170806000049","CD170806000032","CD170731000008","CD170803000047","CD170806000053","CD170818000082","CD170817000054","CD170806000021","CD170807000081","CD170817000049","CD170807000108","CD170806000043","CD170806000048","CD170805000064","CD170902000104","CD170803000049","CD170804000037","CD170805000066","CD170817000052","CD170805000052","CD170806000050","CD170805000060","CD170817000047","CD170805000059","CD170806000012","CD170806000056","CD170814000026","CD170814000027","CD170807000099","CD170806000068","CD170806000060","CD171009000276","CD170908000175","CD170915000056","CD170812000208","CD170805000069","CD171018000003","CD170806000031","CD170828000007","CD171010000283","CD170806000025","CD171017000011","CD170918000081","CD170818000065","CD170811000206"};
        double[] qtys = new double[]{0,0,0.01,0.01,0.01,0.017,0.02,0.02,0.025,0.025,0.025,0.025,0.025,0.025,0.025,0.026,0.04,0.043,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.07,0.075,0.075,0.075,0.08,0.08,0.08,0.087,0.089,0.1,0.1,0.1,0.14,0.15,0.15,0.15,0.16,0.17,0.17,0.175,0.18,0.19,0.195,0.2,0.2,0.2,0.2,0.22,0.24,0.24,0.25,0.25,0.26,0.275,0.3,0.3,0.3,0.3,0.306,0.35,0.35,0.357,0.375,0.425,0.425,0.45,0.45,0.48,0.5,0.5,0.525,0.54,0.6,0.675,0.675,0.725,0.8,0.875,0.875,0.875,0.888,0.9,0.925,0.925,0.94,0.95,1.001,1.264,2,2.175,3,4.575,4.6,8,20.05,22.8,27.4};
        for (int i = 0; i < numbers.length; i++) {
            double qty = qtys[i];
            double agentprice = 200;
            double rateQty = Double.valueOf(Misc.formatNumber("0.000######", BigDecimals.multiply(qty, 1000, 9)));  // 重量：代理计量单位
            double ratePrice = BigDecimals.multiply(agentprice, 0.8);
            double rateFund = BigDecimals.multiply(rateQty, ratePrice);
            double minQty = (int) BigDecimals.divide(rateFund, 1000);  // 手数：向下取整（货物数量 * 参考价 * 预付比率 / 匹配单位资金额度）
            BigDecimal prePayment = BigDecimal.valueOf(BigDecimals.multiply(minQty, 1000));
            BigDecimal riskBail = BigDecimal.valueOf(BigDecimals.multiply(prePayment, bailRatio));
            System.out.println("number: " + numbers[i] + ", qty: " + qty + ", user_price: " + (agentprice * 1000) + ", price: " + agentprice
                    + ", min_qty: " + minQty + ", pre_payment: " + prePayment + ", risk_bail: " + riskBail);
        }*/
    }

    /** 格式化金额：显示前 Num 位和小数点位，其余用*标识 */
    public static String parseMoney(Double money, int showNum){
        String r = "";
        if(money == null)  return r;
        // String s = BigDecimal.valueOf(money).stripTrailingZeros().toPlainString();
        String s = new DecimalFormat("0.00").format(money);
        int pot = -1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '.') { pot = i; }
            if(pot != -1){ // 当存在小数时
                if(i > (showNum - 1) && i < pot){
                    r = r + "*";
                }else{
                    r = r + c;
                }
            }else{ // 当不存在小数时
                if(i > (showNum - 1)){
                    r = r + "*";
                }else{
                    r = r + c;
                }
            }
        }
        return r;
    }

    /**
     * MD5散列计算
     * 
     * @param data 要计算散列值的字节数组
     * @return MD5散列值（16进格式串）
     */
    public static String md5Hash(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
            return encodeHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            _Logger.error(e.toString());
        }
        return null;
    }

    /**
     * 按字串的UTF-8编码进行MD5散列计算
     * 
     * @param str 要计算散列值的字串
     * @return MD5散列值（16进格式串）
     */
    public static String md5Hash(String str) {
        if (null == str) {
            return "";
        }
        try {
            return md5Hash(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            _Logger.error(e.toString());
        }
        return null;
    }

    /**
     * SHA散列计算
     * 
     * @param data 要计算散列值的字节数组
     * @return SHA散列值（16进格式串）
     */
    public static String shaHash(byte[] data) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(data);
            return encodeHex(sha.digest());
        } catch (NoSuchAlgorithmException e) {
            _Logger.error(e.toString());
        }
        return null;

    }

    /**
     * 按字串的UTF-8编码进行SHA散列计算
     * 
     * @param str 要计算散列值的字串
     * @return SHA散列值（16进格式串）
     */
    public static String shaHash(String str) {
        if (null == str) {
            return "";
        }
        try {
            return shaHash(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            _Logger.error(e.toString());
        }
        return null;
    }

    public static double toDouble(String str, double defaultDouble) {
        if (isEmpty(str)) {
            return defaultDouble;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            if (_Logger.isDebugEnabled()) {
                _Logger.debug("转换数据失败：" + str, e);
            }
            return defaultDouble;
        }
    }

    /**
     * 转换为16进制格式的字串
     * 
     * @param val 32位数值
     * @return 16进制格式串（如：a1f）
     */
    public static String toHex(int val) {
        return toHex(val, new StringBuilder(8)).toString();
    }

    /**
     * 转为 HEX字串
     * 
     * @param val 32位数值
     * @param sb 转换HEX后的追加字串缓冲区
     * @return 追加后的字串缓冲区
     */
    public static StringBuilder toHex(int val, StringBuilder sb) {
        if (val < 0 || val >= 0x10000000) {
            sb.append(_hexDigits[(val >> 28) & 0xF]);
            sb.append(_hexDigits[(val >> 24) & 0xF]);
            sb.append(_hexDigits[(val >> 20) & 0xF]);
            sb.append(_hexDigits[(val >> 16) & 0xF]);
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x01000000) {
            sb.append(_hexDigits[(val >> 24) & 0xF]);
            sb.append(_hexDigits[(val >> 20) & 0xF]);
            sb.append(_hexDigits[(val >> 16) & 0xF]);
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00100000) {
            sb.append(_hexDigits[(val >> 20) & 0xF]);
            sb.append(_hexDigits[(val >> 16) & 0xF]);
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00010000) {
            sb.append(_hexDigits[(val >> 16) & 0xF]);
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00001000) {
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00000100) {
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00000010) {
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00000001) {
            sb.append(_hexDigits[(val) & 0xF]);
        } else {
            sb.append("0");
            return sb;
        }
        return sb;
    }

    /**
     * 64位整数HEX字串，不足16个字符前端补0
     * 
     * @param val 整数
     * @return hex格式串
     */
    public static String toHex64(long val) {
        if (0 == val) {
            return "0000000000000000";
        }
        return toHexFixed(val, new StringBuilder(16)).toString();
    }

    /**
     * 32位整数HEX字串，不足8个字符前端补0
     * 
     * @param val 32位数字
     * @param sb 字串缓冲区，若为null自动创建新的
     * @return 8字符的HEX编码串
     */
    public static StringBuilder toHexFixed(int val, StringBuilder sb) {
        if (null == sb) {
            sb = new StringBuilder(8);
        }
        if (val < 0 || val >= 0x10000000) {
            sb.append(_hexDigits[(val >> 28) & 0xF]);
            sb.append(_hexDigits[(val >> 24) & 0xF]);
            sb.append(_hexDigits[(val >> 20) & 0xF]);
            sb.append(_hexDigits[(val >> 16) & 0xF]);
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x01000000) {
            sb.append('0');
            sb.append(_hexDigits[(val >> 24) & 0xF]);
            sb.append(_hexDigits[(val >> 20) & 0xF]);
            sb.append(_hexDigits[(val >> 16) & 0xF]);
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00100000) {
            sb.append("00");
            sb.append(_hexDigits[(val >> 20) & 0xF]);
            sb.append(_hexDigits[(val >> 16) & 0xF]);
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00010000) {
            sb.append("000");
            sb.append(_hexDigits[(val >> 16) & 0xF]);
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00001000) {
            sb.append("0000");
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00000100) {
            sb.append("00000");
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00000010) {
            sb.append("000000");
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[(val) & 0xF]);
        } else if (val >= 0x00000001) {
            sb.append("0000000");
            sb.append(_hexDigits[(val) & 0xF]);
        } else {
            sb.append("00000000");
            return sb;
        }
        return sb;
    }

    /**
     * 64位整数HEX字串，不足16个字符前端补0
     * 
     * @param val 64位数值
     * @param sb 字串缓冲区，若为null自动创建新的
     * @return 16字符的HEX编码串
     */
    public static StringBuilder toHexFixed(long val, StringBuilder sb) {
        if (null == sb) {
            sb = new StringBuilder(16);
        }
        // 高32位
        int i32 = (int) ((val >> 32) & 0xFFFFFFFF);
        toHexFixed(i32, sb);
        // 低32位
        i32 = (int) (val & 0xFFFFFFFF);
        toHexFixed(i32, sb);
        return sb;
    }

    /**
     * 16位整数HEX字串，不足4个字符前端补0
     * 
     * @param val
     * @param sb
     * @return
     */
    public static StringBuilder toHexFixed(short val, StringBuilder sb) {
        if (null == sb) sb = new StringBuilder(4);

        if (val < 0 || val >= 0x1000) {
            sb.append(_hexDigits[(val >> 12) & 0xF]);
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[val & 0x0F]);
        } else if (val >= 0x0100) {
            sb.append('0');
            sb.append(_hexDigits[(val >> 8) & 0xF]);
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[val & 0x0F]);
        } else if (val >= 0x0010) {
            sb.append("00");
            sb.append(_hexDigits[(val >> 4) & 0xF]);
            sb.append(_hexDigits[val & 0x0F]);
        } else if (val >= 0x0001) {
            sb.append("000");
            sb.append(_hexDigits[val & 0x0F]);
        } else {
            sb.append("0000");
            return sb;
        }
        return sb;
    }

    public static int toInt(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    /**
     * 字符串转成数字
     * 
     * @param str
     * @param defaultInt 默认值
     * @return
     */
    public static int toInt(String str, int defaultInt) {
        // 如果字串为空时，返回defaultValue
        if (null == str || 0 == str.length()) {
            return defaultInt;
        }

        try {
            char first = str.charAt(0);
            if ('0' == first && str.length() > 2) {
                // 如果是以0x开首表示是十六进制
                first = str.charAt(1);
                if ('x' == first || 'X' == first) {
                    return Integer.parseInt(str.substring(2), 16);
                }
            } else if (str.length() > 1 && ('x' == first || 'X' == first)) {
                // x开首以十六进制
                return Integer.parseInt(str.substring(1), 16);
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            if (_Logger.isDebugEnabled()) {
                _Logger.debug("数字转换失败:" + str, e);
            }
        }
        return defaultInt;
    }

    public static boolean isZero(Integer i){
        return i== null || i.equals(0);
    }
    public static boolean isZero(Float f){
        return f == null || f.equals(0f);
    }
    public static boolean isZero(Double d){
        return d == null || d.equals(0d);
    }
    public static boolean isNotZero(Integer i){
        return !isZero(i);
    }
    public static boolean isNotZero(Float f){
        return ! isZero(f);
    }
    public static boolean isNotZero(Double d){
        return ! isZero(d);
    }

    public static boolean isTrue(Boolean b){return null!=b&&b;}
    public static boolean isFalse(Boolean b){return !isTrue(b);}

    public static String formatNumber(String format,Number number){
        if(null == number)  return null;
        DecimalFormat df = new DecimalFormat(format);
        return df.format(number);
    }

    public static String formatNumber(Number number){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);
    }

    public static String formatNumber(Number number, String format){
        DecimalFormat df = new DecimalFormat(format);
        return df.format(number);
    }

    public static String toString(Object obj) {
        if (null == obj) {
            return "";
        }
        return obj.toString();
    }

    public static String toString(String str) {
        return null == str ? "" : str;
    }

    /* 非空解析BigDecimal */
    public static double parseNumber (Number number) {
        return (null != number) ? number.doubleValue() : 0.00;
    }

    /* 非空解析BigDecimal */
    public static double parseNumber (Number number, double defaultVal) {
        return (null != number) ? number.doubleValue() : defaultVal;
    }
}
