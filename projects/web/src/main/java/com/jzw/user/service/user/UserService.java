package com.jzw.user.service.user;

import com.jzw.api.user.dto.User;
import com.jzw.common.bean.BaseResponse;
import com.jzw.user.remote.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by boying on 2017/7/4.
 */
@Service
public class UserService {
    @Autowired
    private UserServiceClient userServiceClient;

    public BaseResponse<User> getUserById(long id){
        return userServiceClient.getUserById(id);
    }
}
