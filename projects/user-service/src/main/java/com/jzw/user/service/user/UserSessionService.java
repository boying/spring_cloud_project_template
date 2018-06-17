package com.jzw.user.service.user;

import com.jzw.api.user.dto.UserAuthResult;
import com.jzw.common.bean.BaseResponse;
import com.jzw.common.redis.RedisUtils;
import com.jzw.user.bean.LoginChannel;
import com.jzw.user.config.AppConfig;
import com.jzw.user.dao.UserSessionDao;
import com.jzw.user.domain.User;
import com.jzw.user.domain.UserSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by boying on 2018/6/16.
 */
@Service
public class UserSessionService {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private UserSessionDao userSessionDao;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;

    public String openSesseion(Long userId, LoginChannel loginChannel) {
        String token = genToken(loginChannel);
        Timestamp expireTime = getExpireTime(loginChannel);

        updateDbToken(token, userId, loginChannel, expireTime);

        try {
            redisUtils.set(token,  userId.toString(),  expireTime.getTime() - System.currentTimeMillis());
        } catch (Exception e){
            //TODO log
        }

        return token;
    }


    private void updateDbToken(String token, Long userId, LoginChannel loginChannel, Timestamp expireTime){
        switch (loginChannel) {
            case PC:
                userSessionDao.updatePcToken(userId, token, expireTime);
                break;
            case ANDROID:
                userSessionDao.updateAndroidToken(userId, token, expireTime);
                break;
            case IOS:
                userSessionDao.updateIosToken(userId, token, expireTime);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    public String genToken(LoginChannel loginChannel) {
        String uuid = UUID.randomUUID().toString();
        return loginChannel.getTokenPrefix() + uuid.replace("-", "");
    }

    public Timestamp getExpireTime(LoginChannel loginChannel) {
        switch (loginChannel) {
            case PC:
                return new Timestamp(System.currentTimeMillis() + appConfig.getPcExpireSeconds());
            case ANDROID:
                return new Timestamp(System.currentTimeMillis() + appConfig.getAndroidExpireSeconds());
            case IOS:
                return new Timestamp(System.currentTimeMillis() + appConfig.getIosExpireSeconds());
        }
        throw new IllegalArgumentException();
    }

    public BaseResponse<UserAuthResult> userAuth(String token){
        Long userId = null;
        boolean hitCache = false;
        try {
            String userIdString = redisUtils.getString(token);
            if(StringUtils.isNotBlank(userIdString)){
                userId = Long.parseLong(userIdString);
                hitCache = true;
            }
        } catch (Exception e){
        }

        if(userId == null){
            UserSession userSessionByToken = getUserSessionByToken(token);
            if(userSessionByToken != null){
                userId = userSessionByToken.getId();
                if(!hitCache){
                    redisUtils.set(token, "" + userId,
                            getExpireTime(userSessionByToken, token).getTime() - System.currentTimeMillis());
                }
            }else {
                return BaseResponse.fail("auth failed", null);
            }
        }

        User user = userService.getUser(userId);
        if(user == null){
            return BaseResponse.fail("auth failed", null);
        }

        UserAuthResult userAuthResult = new UserAuthResult();
        userAuthResult.setUserId(user.getId());
        userAuthResult.setName(user.getName());

        return BaseResponse.success(userAuthResult);
    }

    public UserSession getUserSessionByToken(String token){
        LoginChannel loginChannel = LoginChannel.getByTokenPrefix("" + token.charAt(0));
        if(loginChannel == null){
            return null;
        }

        switch (loginChannel) {
            case PC:
                return userSessionDao.getByPcToken(token);
            case ANDROID:
                return userSessionDao.getByAndroidToken(token);
            case IOS:
                return userSessionDao.getByIosToken(token);
        }

        return null;
    }

    public Timestamp getExpireTime(UserSession userSession, String token){
        if(token.equals(userSession.getAndroidToken())){
            return userSession.getAndroidTokenExpireTime();
        }
        if(token.equals(userSession.getIosToken())){
            return userSession.getIosTokenExpireTime();
        }
        if(token.equals(userSession.getPcToken())){
            return userSession.getPcTokenExpireTime();
        }
        return null;
    }

}
