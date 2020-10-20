package com.util;

import org.springframework.util.Base64Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author linxiaobai
 * @Date 2020/9/21 16:04
 * @Description TODO
 * @Version 1.0
 **/
public class SafeUtil_1 {

    /*
     * 使用md5的算法进行加密
     */

    public static String encrypt_md5(String content) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(content.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }


    public final static String encrypt_sha1(String str) {
        byte[] secretBytes = null;
        MessageDigest messageDigest = null;
        try {
            secretBytes = MessageDigest.getInstance("SHA-1").digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String sha_1 = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - sha_1.length(); i++) {
            sha_1 = "0" + sha_1;
        }
        return sha_1;
       /* messageDigest.update(str.getBytes());
        return Base64Utils.encodeToString(messageDigest.digest());*/

    }




    public final static String md5(String str) {
        MessageDigest messageDigest = null;
        try {
            MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(str.getBytes());
        return Base64Utils.encodeToString(messageDigest.digest());
    }




    public final static String sha1(String str) {
        MessageDigest messageDigest = null;
        try {
            MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(str.getBytes());
        return Base64Utils.encodeToString(messageDigest.digest());
    }


    public final static String encode(String str) {
//        return md5(sha1(md5(str)));
        return encrypt_md5(str);
    }

    public static void main(String[] args) {

        System.out.println(encrypt_md5("admin"));

//        System.out.println(encode("123"));

    }
}
