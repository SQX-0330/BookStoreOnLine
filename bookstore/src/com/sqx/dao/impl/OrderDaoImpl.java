package com.sqx.dao.impl;

import com.sqx.dao.BaseDao;
import com.sqx.dao.OrderDao;
import com.sqx.pojo.Order;

/**
 * @author SQX
 * @date 2020/9/6 - 18:10
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`, `create_time`, `price`, `status`, `user_id`) values(?,?,?,?,?)";

        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }
}
