package com.sqx.test;

import com.sqx.pojo.Cart;
import com.sqx.pojo.CartItem;
import com.sqx.service.OrderService;
import com.sqx.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/9/6 - 21:06
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构", 1,new BigDecimal(1000),new BigDecimal(1000)));

        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是" + orderService.createOrder(cart, 1));
    }
}