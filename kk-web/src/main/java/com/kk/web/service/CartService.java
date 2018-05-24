package com.kk.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.common.service.ApiService;
import com.kk.sso.query.bean.User;
import com.kk.web.bean.Cart;
import com.kk.web.threadlocal.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName CartService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/13 13:30
 */
@Service
public class CartService {

    @Autowired
    private ApiService apiService;

    @Value("${KK_CART_URL}")
    private String KK_CART_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public List<Cart> queryCartListByUserId(Long userId) {
        //查询购物车系统提供的接口获取购物车列表
        try {
            User user = UserThreadLocal.get();
            String url = KK_CART_URL + "/com.kk.cart.bean.service/cart?userId=" + user.getId();
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return MAPPER.readValue(jsonData, MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
