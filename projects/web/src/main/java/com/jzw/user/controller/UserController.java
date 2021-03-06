package com.jzw.user.controller;

import com.jzw.api.user.dto.User;
import com.jzw.common.bean.BaseResponse;
import com.jzw.user.remote.UserServiceClient;
import com.jzw.user.remote.UserServiceFallbackClient;
import com.jzw.user.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by boying on 2018/5/6.
 */
@RestController
@RequestMapping("/web/user")
public class UserController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceClient userServiceClient;

    @RequestMapping(value = "/{userId}",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponse<User> getUser(@PathVariable("userId") long userId){
        return userService.getUserById(userId);
    }

    @RequestMapping(value = "/unstable",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponse<Long> unstable(@RequestParam("sleep") long millisconds){
        return userServiceClient.unstable(millisconds);
    }

    @RequestMapping(value = "/sleep",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponse<Long> sleep(@RequestParam("sleep") long millisconds){
        try {
            BaseResponse<Long> ret = userServiceClient.sleep(millisconds);
            logger.info("sleep " + millisconds);
            return ret;
        }catch (Exception e){
            logger.error("sleep error: ", e);
            throw e;
        }
    }
}
