package com.jzw.user.remote;

import com.jzw.api.user.service.IUserService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by boying on 2018/5/6.
 */
@FeignClient(value = "USER-SERVICE")
public interface UserServiceClient extends IUserService{
}