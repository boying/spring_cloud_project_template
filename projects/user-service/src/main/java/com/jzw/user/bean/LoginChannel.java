package com.jzw.user.bean;

/**
 * Created by boying on 2018/6/16.
 */
public enum LoginChannel {
    PC(0, "p"),
    ANDOIRD(1, "a"),
    IOS(2, "i");
    int val;
    String tokenPrefix;

    LoginChannel(int val, String tokenPrefix) {
        this.val = val;
        this.tokenPrefix = tokenPrefix;
    }

    public int getVal() {
        return val;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }
}
