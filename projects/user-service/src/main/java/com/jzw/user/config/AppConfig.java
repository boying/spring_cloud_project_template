package com.jzw.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by boying on 2018/6/16.
 */
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private int pcExpireSeconds;
    private int androidExpireSeconds;
    private int iosExpireSeconds;

    public int getPcExpireSeconds() {
        return pcExpireSeconds;
    }

    public void setPcExpireSeconds(int pcExpireSeconds) {
        this.pcExpireSeconds = pcExpireSeconds;
    }

    public int getAndroidExpireSeconds() {
        return androidExpireSeconds;
    }

    public void setAndroidExpireSeconds(int androidExpireSeconds) {
        this.androidExpireSeconds = androidExpireSeconds;
    }

    public int getIosExpireSeconds() {
        return iosExpireSeconds;
    }

    public void setIosExpireSeconds(int iosExpireSeconds) {
        this.iosExpireSeconds = iosExpireSeconds;
    }
}
