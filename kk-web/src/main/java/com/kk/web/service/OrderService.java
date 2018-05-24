package com.kk.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.common.httpclient.HttpResult;
import com.kk.common.service.ApiService;
import com.kk.sso.query.bean.User;
import com.kk.web.bean.Order;
import com.kk.web.threadlocal.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.omg.IOP.TAG_ORB_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName OrderService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/10 22:00
 */
@Service
public class OrderService {

    @Autowired
    private ApiService apiService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${KK_ORDER_URL}")
    private String KK_ORDER_URL;

    public String submitOrder(Order order) {
        //从本地线程中获取user对象
        User user = UserThreadLocal.get();
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());
        try {
            String url = KK_ORDER_URL + "/order/create";
            HttpResult httpResult = this.apiService.doPostJson(url,MAPPER.writeValueAsString(order));
            if (httpResult.getCode().intValue() == 200){
                String jsonData = httpResult.getData();
                JsonNode jsonNode = MAPPER.readTree(jsonData);
                if (jsonNode.get("status").intValue() == 200){
                    //订单提交成功,返回订单号
                    return jsonNode.get("data").asText();                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order queryOrderById(String orderId) {
        String url = KK_ORDER_URL + "/order/query/" + orderId;
        try {
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)){
                return MAPPER.readValue(jsonData,Order.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
