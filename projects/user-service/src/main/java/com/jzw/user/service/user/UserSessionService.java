package com.jzw.user.service.user;

import com.jzw.user.bean.LoginChannel;
import com.jzw.user.config.AppConfig;
import com.jzw.user.dao.UserSessionDao;
import com.jzw.user.domain.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by boying on 2018/6/16.
 */
public class UserSessionService {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private UserSessionDao userSessionDao;

    public String openSesseion(Long userId, LoginChannel loginChannel) {
        String token = genToken(loginChannel);
        Timestamp expireTime = getExpireTime(loginChannel);

        switch (loginChannel) {
            case PC:
                userSessionDao.updatePcToken(userId, token, expireTime);
                break;
            case ANDOIRD:
                userSessionDao.updateAndroidToken(userId, token, expireTime);
                break;
            case IOS:
                userSessionDao.updateIosToken(userId, token, expireTime);
                break;
            default:
                throw new IllegalStateException();
        }

        return token;
    }

    public String genToken(LoginChannel loginChannel) {
        String uuid = UUID.randomUUID().toString();
        return loginChannel.getTokenPrefix() + uuid.replace("-", "");
    }

    public Timestamp getExpireTime(LoginChannel loginChannel) {
        switch (loginChannel) {
            case PC:
                return new Timestamp(System.currentTimeMillis() + appConfig.getPcExpireSeconds());
            case ANDOIRD:
                return new Timestamp(System.currentTimeMillis() + appConfig.getAndroidExpireSeconds());
            case IOS:
                return new Timestamp(System.currentTimeMillis() + appConfig.getIosExpireSeconds());
        }
        throw new IllegalArgumentException();
    }


}
