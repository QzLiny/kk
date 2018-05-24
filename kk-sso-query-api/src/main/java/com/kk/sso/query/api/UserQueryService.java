package com.kk.sso.query.api;

import com.kk.sso.query.bean.User;

/**
 * @ClassName UserQueryService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/14 0:34
 */
public interface UserQueryService {
    /**
     * 根据token查询User对象
     * @param token
     * @return
     */
    public User queryUserByToken(String token);
}
