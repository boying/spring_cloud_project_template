package com.jzw.api.user.dto;

/**
 * Created by boying on 2018/6/17.
 */
public class UserAuthResult {
    public static final String AUTH_FAILED = "01";

    private Long userId;
    private String name;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
