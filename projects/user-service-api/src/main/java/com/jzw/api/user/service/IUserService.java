package com.jzw.api.user.service;

import com.jzw.api.user.dto.User;
import com.jzw.common.bean.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface IUserService {
    @RequestMapping(value = "/get-user-by-id", method = RequestMethod.GET)
    BaseResponse<User> getUserById(@RequestParam("id") long id);

    @RequestMapping(value = "/unstable", method = RequestMethod.GET)
    BaseResponse<Long> unstable(@RequestParam("sleep") long milliseconds);
}