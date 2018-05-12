package com.jzw.user.resource;

import com.jzw.api.user.dto.User;
import com.jzw.api.user.service.IUserService;
import com.jzw.common.bean.BaseResponse;
import com.jzw.user.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by boying on 2018/5/6.
 */
@RestController
public class UserResource implements IUserService {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    // TODO 设置序列化方式
    public BaseResponse<User> getUserById(@RequestParam("id") long userId) {
        com.jzw.user.domain.User user = userService.getUser(userId);
        if (user == null) {
            return null;
        }
        User ret = new User();
        BeanUtils.copyProperties(user, ret);
        return BaseResponse.success(ret);
    }

    @Override
    public BaseResponse<Long> unstable(@RequestParam("sleep") long milliseconds) {
        long sleep = Math.round(Math.random() * milliseconds);
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            logger.error("sleep error: ", e);
        }
        return BaseResponse.success(sleep);
    }

    @Override
    public BaseResponse<Long> sleep(@RequestParam("sleep") long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            logger.error("sleep error: ", e);
        }
        return BaseResponse.success(milliseconds);
    }
}
