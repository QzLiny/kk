package com.kk.store.order.dao;

import com.kk.store.order.pojo.Order;
import com.kk.store.order.pojo.PageResult;
import com.kk.store.order.pojo.ResultMsg;

/**
 * 订单DAO接口
 */
public interface IOrder {

    /**
     * 创建订单
     * 
     * @param order
     */
    public void createOrder(Order order);

    /**
     * 根据订单ID查询订单
     * 
     * @param orderId
     * @return
     */
    public Order queryOrderById(String orderId);

    /**
     *
     * 
     * @param buyerNick 买家昵称，用户名
     * @param page 分页起始取数位置
     * @param count 查询数据条数
     * @return 分页结果集
     */
    public PageResult<Order> queryOrderByUserNameAndPage(String buyerNick, Integer page, Integer count);

    /**
     * 更改订单状态，由service层控制修改逻辑
     * 
     * @param order
     * @return
     */
    public ResultMsg changeOrderStatus(Order order);

}
