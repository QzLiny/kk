package com.kk.cart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.cart.bean.Item;
import com.kk.cart.pojo.Cart;
import com.kk.common.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CartCookieService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/13 11:11
 */
@Service
public class CartCookieService {

    public static final String COOKIE_NAME = "KK_CART";

    public static final Integer COOKIE_TIME = 60 * 60 * 24 * 30 * 12;

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ItemService itemService;

    public void addItemToCar(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<Cart> carts = queryCartList(request);
        //判断该商品在购物车中是否存在
        Cart cart = null;
        for (Cart c : carts){
            if (c.getItemId().longValue() == itemId.longValue()){
                cart = c;
                break;
            }
        }
        if (null == cart){
            //不存在
            Item item = this.itemService.queryItemById(itemId);
            if (null == item){
                return;
            }
            cart = new Cart();
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cart.setItemId(itemId);
            cart.setItemImage(item.getImages()[0]);
            cart.setItemPrice(item.getPrice());
            cart.setItemTitle(item.getTitle());
            cart.setNum(1);
            //加入到购物车
            carts.add(cart);
        }else {
            //存在
            cart.setNum(cart.getNum() + 1);
            cart.setUpdated(new Date());
        }
        //将集合写入到cookie中
        try {
            CookieUtils.setCookie(request,response,COOKIE_NAME,MAPPER.writeValueAsString(carts),COOKIE_TIME,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cart> queryCartList(HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request,COOKIE_NAME,true);
        List<Cart> carts = null;
        if (StringUtils.isEmpty(cookieValue)){
            carts = new ArrayList<Cart>();
        }else {
            try {
                carts = MAPPER.readValue(cookieValue,
                        MAPPER.getTypeFactory().constructCollectionType(List.class,Cart.class));
            } catch (Exception e) {
                e.printStackTrace();
                carts = new ArrayList<Cart>();
            }
        }
        return carts;
    }

    public void updateNum(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        List<Cart> carts = queryCartList(request);
        for (Cart c : carts){
            if (c.getItemId().longValue() == itemId.longValue()){
                c.setNum(num);
                c.setUpdated(new Date());
                break;
            }
        }
        //将集合写入到cookie中
        try {
            CookieUtils.setCookie(request,response,COOKIE_NAME,MAPPER.writeValueAsString(carts),COOKIE_TIME,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<Cart> carts = queryCartList(request);
        for (Cart c : carts){
            if (c.getItemId().longValue() == itemId.longValue()){
                carts.remove(c);
                break;
            }
        }
        //将集合写入到cookie中
        try {
            CookieUtils.setCookie(request,response,COOKIE_NAME,MAPPER.writeValueAsString(carts),COOKIE_TIME,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
