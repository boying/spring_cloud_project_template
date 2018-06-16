package com.jzw.user.dao;

import com.jzw.user.domain.UserSession;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

/**
 * Created by boying on 2017/7/4.
 */
public interface UserSessionDao {
    UserSession getById(@Param("id") long id);

    void addUserSession(UserSession userSession);

    int updatePcToken(@Param("id") long id, @Param("token") String token, @Param("expireTime")Timestamp expireTime);

    int updateAndroidToken(@Param("id") long id, @Param("token") String token, @Param("expireTime")Timestamp expireTime);

    int updateIosToken(@Param("id") long id, @Param("token") String token, @Param("expireTime")Timestamp expireTime);

    UserSession getByPcToken(@Param("token") String token);

    UserSession getByAndroidToken(@Param("token") String token);

    UserSession getByIosToken(@Param("name") String token);
}
