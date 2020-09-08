package com.sqx.service;

import com.sqx.pojo.Cart;

/**
 * @author SQX
 * @date 2020/9/6 - 20:51
 */
public interface OrderService {

    public String createOrder(Cart cart, Integer userId);
}
