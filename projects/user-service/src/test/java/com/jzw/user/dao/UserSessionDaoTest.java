package com.jzw.user.dao;

import com.jzw.user.BaseTest;
import com.jzw.user.domain.UserSession;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * Created by boying on 2018/6/16.
 */
public class UserSessionDaoTest extends BaseTest{
    @Autowired
    private UserSessionDao userSessionDao;

    @Test
    public void addUpdateGetTest(){
        long userId = 1L;
        UserSession userSession = new UserSession();
        userSession.setId(userId);
        userSessionDao.addUserSession(userSession);

        String token = "abc";
        Timestamp expireTime = new Timestamp(System.currentTimeMillis() + 10);
        userSessionDao.updatePcToken(userSession.getId(), token, expireTime);
        userSessionDao.updateAndroidToken(userSession.getId(), token, expireTime);
        userSessionDao.updateIosToken(userSession.getId(), token, expireTime);

        UserSession us = userSessionDao.getById(userId);

        Assert.assertEquals(token, us.getPcToken());
        Assert.assertEquals(token, us.getAndroidToken());
        Assert.assertEquals(token, us.getIosToken());

        System.out.println(us.getAndroidTokenExpireTime());
        // Assert.assertEquals(expireTime, us.getPcTokenExpireTime());


    }
}
