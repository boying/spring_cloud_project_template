package com.jzw.user.domain;

import java.sql.Timestamp;

/**
 * Created by boying on 2017/7/4.
 */
public class UserSession extends BaseModel{
    private String pcToken;
    private Timestamp pcTokenExpireTime;
    private String androidToken;
    private Timestamp androidTokenExpireTime;
    private String iosToken;
    private Timestamp iosTokenExpireTime;

    public String getPcToken() {
        return pcToken;
    }

    public void setPcToken(String pcToken) {
        this.pcToken = pcToken;
    }

    public Timestamp getPcTokenExpireTime() {
        return pcTokenExpireTime;
    }

    public void setPcTokenExpireTime(Timestamp pcTokenExpireTime) {
        this.pcTokenExpireTime = pcTokenExpireTime;
    }

    public String getAndroidToken() {
        return androidToken;
    }

    public void setAndroidToken(String androidToken) {
        this.androidToken = androidToken;
    }

    public Timestamp getAndroidTokenExpireTime() {
        return androidTokenExpireTime;
    }

    public void setAndroidTokenExpireTime(Timestamp androidTokenExpireTime) {
        this.androidTokenExpireTime = androidTokenExpireTime;
    }

    public String getIosToken() {
        return iosToken;
    }

    public void setIosToken(String iosToken) {
        this.iosToken = iosToken;
    }

    public Timestamp getIosTokenExpireTime() {
        return iosTokenExpireTime;
    }

    public void setIosTokenExpireTime(Timestamp iosTokenExpireTime) {
        this.iosTokenExpireTime = iosTokenExpireTime;
    }
}
