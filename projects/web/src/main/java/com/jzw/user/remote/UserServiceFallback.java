package com.jzw.user.remote;


import com.jzw.api.user.dto.User;
import com.jzw.common.bean.BaseResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by boying on 2018/5/12.
 */
@Component
public class UserServiceFallback implements UserServiceFallbackClient {


    public BaseResponse<User> getUserById(@RequestParam("id") long var1){
        return BaseResponse.fail(null);
    }


    public BaseResponse<Long> unstable(@RequestParam("sleep") long var1){
        return BaseResponse.fail(null);
    }



    public BaseResponse<Long> sleep(@RequestParam("sleep") long var1){
        return BaseResponse.fail(null);
    }
}
