package com.kk.cart.service;


import com.kk.sso.query.api.UserQueryService;
import com.kk.sso.query.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/10 22:54
 */
@Service
public class UserService {

    @Autowired
    private UserQueryService userQueryService;

    public User queryUserByToken(String token){
        return this.userQueryService.queryUserByToken(token);
    }

}
