package cn.oriki.shiro.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

import cn.oriki.shiro.entity.User;

public class PasswordService {

    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void encryptPassword(User user) {

        user.setSalt("salt");

        String newPassword = new SimpleHash(algorithmName, user.getPassword(), user.getSalt(), hashIterations).toString();

        user.setPassword(newPassword);
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }
}
