package com.sqx.dao.impl;

import com.sqx.dao.BaseDao;
import com.sqx.dao.OrderItemDao;
import com.sqx.pojo.OrderItem;

/**
 * @author SQX
 * @date 2020/9/6 - 18:15
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {


    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`, `count`, `price`, `total_price`, `order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }
}
