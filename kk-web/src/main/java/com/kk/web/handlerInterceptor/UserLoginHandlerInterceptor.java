package com.kk.web.handlerInterceptor;

import com.kk.common.utils.CookieUtils;
import com.kk.sso.query.bean.User;
import com.kk.web.service.PropertieService;
import com.kk.web.service.UserService;
import com.kk.web.threadlocal.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserLoginHandlerInterceptor
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/10 22:42
 */
public class UserLoginHandlerInterceptor implements HandlerInterceptor {

    public static final String COOKIE_NAME = "KKL_TOKEN";

    @Autowired
    private PropertieService propertieService;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //清空当前线程中的user对象
        UserThreadLocal.set(null);

        String loginUrl = propertieService.KK_SSO_URL + "/user/login.html";
        String token = CookieUtils.getCookieValue(httpServletRequest , COOKIE_NAME);
        if (StringUtils.isEmpty(token)){
            //未登陆状态
            httpServletResponse.sendRedirect(loginUrl);
            return false;
        }
        User user = this.userService.queryUserByToken(token);
        if (null == user){
            httpServletResponse.sendRedirect(loginUrl);
            return false;
        }
        //处于登陆状态
        //将user对象放置到threadlocal中
        UserThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
