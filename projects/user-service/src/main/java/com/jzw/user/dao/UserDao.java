package com.jzw.user.dao;

import com.jzw.user.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by boying on 2017/7/4.
 */
public interface UserDao {
    void addUser(User user);

    User getUserById(@Param("id") long id);

    User getUserByName(@Param("name") String name);
}
