package com.jzw.user.remote;

import com.jzw.api.user.dto.User;
import com.jzw.api.user.service.IUserService;
import com.jzw.common.bean.BaseResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by boying on 2018/5/6.
 */
@FeignClient(value = "USER-SERVICE", fallback = UserServiceFallback.class)
public interface UserServiceFallbackClient {
    @RequestMapping(
            value = {"/get-user-by-id"},
            method = {RequestMethod.GET}
    )
    BaseResponse<User> getUserById(@RequestParam("id") long var1);

    @RequestMapping(
            value = {"/unstable"},
            method = {RequestMethod.GET}
    )
    BaseResponse<Long> unstable(@RequestParam("sleep") long var1);

    @RequestMapping(
            value = "/user/sleep",
            method = RequestMethod.GET
    )
    BaseResponse<Long> sleep(@RequestParam("sleep") long sleep);
}
