package com.dayi.follow.util;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPUtil {

    public static final String PATTERN_IP = "^[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}$";
    //ip138接口地址
    private static final String ip138ApiUrl = "http://api.ip138.com/query/";
    private static final String ip138Token = "30c036305e6786c70f3edc62aefff27e";
    /**
     * ip138数据类型返回形式（txt|jsonp|xml）
     */
    private static final String ip138Datatype = "text";

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        ip = getTrueIp(ip);

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            ip = getTrueIp(ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            ip = getTrueIp(ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            ip = getTrueIp(ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            ip = getTrueIp(ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            ip = getTrueIp(ip);
        }
        if(ip==null && StringUtils.equalsIgnoreCase("localhost",request.getServerName())){
            ip="127.0.0.1"; //本地运行时IP为NULL,报空指针
        }
        return ip;
    }

    /**
     * 取真实客户端IP，过滤代理IP
     *
     * @param ip IP
     * @return 真实客户端IP
     */
    private static String getTrueIp(String ip) {
        if(StringUtils.isNotBlank(ip)) {
            for (String anIp : StringUtils.split(ip, ",")) {
                anIp = StringUtils.trim(anIp);
                if (isIp(anIp) && !StringUtils.startsWith(anIp, "10.") && !StringUtils.startsWith(anIp, "172.16")) {
                    return anIp;
                }
            }
        }
        return null;
    }

    /**
     * 判断IP是否合法
     *
     * @param ip IP
     * @return 合法返回true, 否则返回false
     */
    private static boolean isIp(String ip) {
        if(StringUtils.isNotBlank(ip)) {
            Pattern p = Pattern.compile(PATTERN_IP, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(ip);
            return m.matches();
        }
        return false;
    }
    
    /**
     * 从ip的字符串形式得到字节数组形式
     * @param ip 字符串形式的ip
     * @return 字节数组形式的ip
     */
    public static byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        //if(ip==null){ip="127.0.0.1";}
        java.util.StringTokenizer st = new java.util.StringTokenizer(ip, ".");
        try {
            ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    
    /**
     * 对原始字符串进行编码转换，如果失败，返回原始的字符串
     * @param s 原始字符串
     * @param srcEncoding 源编码方式
     * @param destEncoding 目标编码方式
     * @return 转换编码后的字符串，失败返回原始字符串
     */
    public static String getString(String s, String srcEncoding, String destEncoding) {
        try {
            return new String(s.getBytes(srcEncoding), destEncoding);
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }
    
    /**
     * 根据某种编码方式将字节数组转换成字符串
     * @param b 字节数组
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, String encoding) {
        try {
            return new String(b, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b);
        }
    }
    
   /**
     * 根据某种编码方式将字节数组转换成字符串
     * @param b 字节数组
     * @param offset 要转换的起始位置
     * @param len 要转换的长度
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }
    
   /**
     * @param ip ip的字节数组形式
     * @return 字符串形式的ip
     */
    public static String getIpStringFromBytes(byte[] ip) {
        StringBuffer sb = new StringBuffer();
        sb.append(ip[0] & 0xFF);
        sb.append('.');   
        sb.append(ip[1] & 0xFF);
        sb.append('.');   
        sb.append(ip[2] & 0xFF);
        sb.append('.');   
        sb.append(ip[3] & 0xFF);
        return sb.toString();
    }

    /**
     * 通过ip获取城市信息
     * @param ip
     * @return
     */
    public static String getAddressByIp(String ip) {
        if(StringUtils.isBlank(ip)) return "";
        //返回格式为（117.25.13.123 中国福建福州电信）
        String ipInfoStr = getAddressByIp138(ip);
        if(StringUtils.isNotBlank(ipInfoStr)) {
            String[] ipInfoArr = ipInfoStr.split("\t");
            if(ipInfoArr.length > 1) {
                return ipInfoArr[1].replaceAll(" ", "");
            }
        }

        return "";
    }

    /**
     * 通过ip138获取ip地址信息
     * @param ip ip地址信息
     * @return
     */
    private static String getAddressByIp138(String ip) {
        String requestUrl = ip138ApiUrl + "?ip=" + ip + "&datatype=" + ip138Datatype;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("token", ip138Token);
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                StringBuilder builder = new StringBuilder();
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
                for (String s = br.readLine(); s != null; s = br
                    .readLine()) {
                    builder.append(s);
                }
                br.close();
                return builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
