package com.yyh.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Objects;

/**
 * @author yihui.yan
 * @date 2019/11/27/027
 */
public class MD5Util {
    /**
     * 对字符串md5加密(小写字母+数字)
     * @param str 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对字符串md5加密(大写字母+数字)
     * @param s 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public static String MD5(String s) {
        char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {
            byte[] btInput = s.getBytes();

            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int setn(String urlId){
        return (Objects.requireNonNull(urlId).charAt(31))%10;
    }
}
