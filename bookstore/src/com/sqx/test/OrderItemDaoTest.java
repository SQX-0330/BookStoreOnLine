package com.sqx.test;

import com.sqx.dao.OrderItemDao;
import com.sqx.dao.impl.OrderItemDaoImpl;
import com.sqx.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/9/6 - 18:26
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到精通", 1, new BigDecimal(100), new BigDecimal(100), "12345"));
        orderItemDao.saveOrderItem(new OrderItem(null, "javaScript从入门到精通", 1, new BigDecimal(100), new BigDecimal(100), "12345"));
        orderItemDao.saveOrderItem(new OrderItem(null, "mvc", 1, new BigDecimal(100), new BigDecimal(100), "12345"));
    }
}