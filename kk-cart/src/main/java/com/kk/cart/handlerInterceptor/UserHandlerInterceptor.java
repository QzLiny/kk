package com.kk.cart.handlerInterceptor;

import com.kk.cart.service.UserService;
import com.kk.cart.threadlocal.UserThreadLocal;
import com.kk.common.utils.CookieUtils;
import com.kk.sso.query.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserHandlerInterceptor
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/10 22:42
 */
public class UserHandlerInterceptor implements HandlerInterceptor {

    public static final String COOKIE_NAME = "KKL_TOKEN";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        UserThreadLocal.set(null);

        String token = CookieUtils.getCookieValue(httpServletRequest , COOKIE_NAME);
        if (StringUtils.isEmpty(token)){
            //未登陆状态,放行
            return true;
        }
        User user = this.userService.queryUserByToken(token);
        if (null == user){
            return true;
        }
        //处于登陆状态
        //将user对象放置到threadlocal中
        UserThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

    }
}
