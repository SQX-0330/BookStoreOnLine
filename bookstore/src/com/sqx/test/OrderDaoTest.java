package com.sqx.test;

import com.sqx.dao.OrderDao;
import com.sqx.dao.impl.OrderDaoImpl;
import com.sqx.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/9/6 - 18:18
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();

        orderDao.saveOrder(new Order("12345", new Date(), new BigDecimal(100), 0, 1));
    }
}