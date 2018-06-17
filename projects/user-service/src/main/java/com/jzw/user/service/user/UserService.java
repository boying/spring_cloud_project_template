package com.jzw.user.service.user;

import com.jzw.common.redis.RedisUtils;
import com.jzw.user.bean.LoginChannel;
import com.jzw.user.dao.UserDao;
import com.jzw.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by boying on 2017/7/4.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtils redisUtils;

    private static final long USER_EXPIRE_MILLS = 60 * 60 * 1000;

    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User getUserByToken(String token) {
        return null;
    }

    public void loginSuccess(Long userId, LoginChannel loginChannel) {
    }

    public User getUser(long userId) {
        User user = null;
        String userKey = "user_" + userId;
        try {
            user = redisUtils.get(userKey, User.class);
        } catch (Exception e) {

        }

        if (user != null) {
            return user;
        }

        user = userDao.getUserById(userId);
        if (user != null) {
            try {
                redisUtils.set(userKey, user, USER_EXPIRE_MILLS);
            } catch (Exception e) {
            }
        }

        return user;
    }

}
