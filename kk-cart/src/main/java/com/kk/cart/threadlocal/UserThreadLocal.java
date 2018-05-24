package com.kk.cart.threadlocal;


import com.kk.sso.query.bean.User;

/**
 * @ClassName UserThreadLocal
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/10 23:41
 */
public class UserThreadLocal {

    private static final ThreadLocal<User> LOCAL = new ThreadLocal<User>();

    public static void set(User user){
        LOCAL.set(user);
    }

    public static User get(){
        return LOCAL.get();
    }
}
