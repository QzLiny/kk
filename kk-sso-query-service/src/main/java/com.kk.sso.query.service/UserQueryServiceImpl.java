package com.kk.sso.query.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.common.service.RedisService;
import com.kk.sso.query.api.UserQueryService;
import com.kk.sso.query.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserQueryServiceImpl
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/14 0:53
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Integer REDIS_TIME = 60 * 30;

    @Override
    public User queryUserByToken(String token) {
        String key = "TOKEN_" + token;
        String jsonData = this.redisService.get(key);
        if (StringUtils.isEmpty(jsonData)){
            return null;
        }
        try {
            //刷新用户的生存时间
            this.redisService.expire(key,REDIS_TIME);
            return MAPPER.readValue(jsonData,User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
