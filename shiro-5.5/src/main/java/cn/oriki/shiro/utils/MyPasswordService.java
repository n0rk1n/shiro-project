package cn.oriki.shiro.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MyPasswordService {

    public static String getNewPassword(String password, String salt) {
        String algorithmName = "MD5";

        Integer hashIterations = 1;

        return new SimpleHash(algorithmName, password, salt, hashIterations).toString();
    }
}
