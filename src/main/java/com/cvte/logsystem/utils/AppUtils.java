package com.cvte.logsystem.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

/**
 * @Description TODO
 * @Classname AppUtils
 * @Date 2023/7/27 4:09 PM
 * @Created by liushenghao
 */
@Component
public class AppUtils {
    // 映射表
    private final static String[] chars = {"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 生成Appid
     * @return  返回appid
     */
    public static String getAppid(){
        StringBuffer sb = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-","");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i*4,i*4 + 4);
            int x = Integer.valueOf(str,16);
            sb.append(chars[x % 0x3E]);
        }
        return sb.toString();
    }

    public static String getAppSecret(String appId,String appName) {
        try {
            String[] array = new String[]{appId, appName};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (String s : array) {
                sb.append(s);
            }
            String str = sb.toString();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (byte b : digest) {
                shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
