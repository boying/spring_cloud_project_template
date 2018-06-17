package com.jzw.user.config;

import com.jzw.common.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by boying on 2018/6/17.
 */
@Configuration
public class RedisUtilsFactory {
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Bean
    public RedisUtils redisUtils(){
        return new RedisUtils(redisTemplate);
    }
}
