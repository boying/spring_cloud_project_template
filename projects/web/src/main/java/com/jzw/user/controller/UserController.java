package com.jzw.user.controller;

import com.jzw.api.user.dto.User;
import com.jzw.common.bean.BaseResponse;
import com.jzw.common.gson.GsonUtils;
import com.jzw.user.remote.UserServiceClient;
import com.jzw.user.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by boying on 2018/5/6.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceClient userServiceClient;

    @ResponseBody
    @RequestMapping(value = "/{userId}",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<User> getUser(@PathVariable("userId") long userId){
        return userService.getUserById(userId);
    }

    @RequestMapping("/unstable")
    public BaseResponse<Long> unstable(@PathVariable("sleep") long millisconds){
        return userServiceClient.unstable(millisconds);
    }
}
