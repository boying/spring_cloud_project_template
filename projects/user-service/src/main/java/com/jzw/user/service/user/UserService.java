package com.jzw.user.service.user;

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

    @Transactional("userTransactionManager")
    public void addUser(User user){
        userDao.addUser(user);
    }

    public User getUser(long userId){
        return userDao.getUserById(userId);
    }


}
