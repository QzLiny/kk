package com.kk.web.controller;

import com.kk.sso.query.bean.User;
import com.kk.web.bean.Cart;
import com.kk.web.bean.Item;
import com.kk.web.bean.Order;
import com.kk.web.service.CartService;
import com.kk.web.service.ItemService;
import com.kk.web.service.OrderService;
import com.kk.web.service.UserService;
import com.kk.web.threadlocal.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName OrderController
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/10 21:28
 */
@RequestMapping("order")
@Controller
public class OrderController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    /**
     * 去订单页
     * @return
     */
    @RequestMapping(value = "{itemId}",method = RequestMethod.GET)
    public ModelAndView toOrder(@PathVariable("itemId")Long itemId){
        ModelAndView modelAndView = new ModelAndView("order");
        Item item = this.itemService.queryItemById(itemId);
        modelAndView.addObject("item",item);
        return modelAndView;
    }

    /**
     * 基于购物车下单
     * @return
     */
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public ModelAndView toCartOrder(){
        ModelAndView modelAndView = new ModelAndView("Order-cart");
        User user = UserThreadLocal.get();
        List<Cart> carts = this.cartService.queryCartListByUserId(user.getId());
        modelAndView.addObject("carts",carts);
        return modelAndView;
    }

    @RequestMapping(value = "submit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submitOrder(Order order){
        Map<String,Object> result = new HashMap<String, Object>();

        String orderId = this.orderService.submitOrder(order);

        if (StringUtils.isEmpty(orderId)){
            //提交订单失败
            result.put("status",300);
        }else {
            //提交订单成功
            result.put("status",200);
            result.put("data",orderId);
        }
        return result;
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public ModelAndView success(@RequestParam("id")String orderId){
        ModelAndView modelAndView = new ModelAndView("success");
        Order order = this.orderService.queryOrderById(orderId);
        modelAndView.addObject("order",order);
        modelAndView.addObject("date",new DateTime().plusDays(2).toString("MM月DD日"));
        return modelAndView;
    }

}
