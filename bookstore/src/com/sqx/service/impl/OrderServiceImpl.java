package com.sqx.service.impl;

import com.sqx.dao.BookDao;
import com.sqx.dao.OrderDao;
import com.sqx.dao.OrderItemDao;
import com.sqx.dao.impl.BookDaoImpl;
import com.sqx.dao.impl.OrderDaoImpl;
import com.sqx.dao.impl.OrderItemDaoImpl;
import com.sqx.pojo.*;
import com.sqx.service.BookService;
import com.sqx.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Date;
import java.util.Map;

/**
 * @author SQX
 * @date 2020/9/6 - 20:52
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号唯一
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建一个订单号
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);



        //遍历购物车中每一个商品项转化成订单项保存到数据库
        for(Map.Entry<Integer, CartItem> entry: cart.getItems().entrySet()){
            //获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();

            //转化为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

        }
        //清空购物车
        cart.clear();

        return orderId;
    }
}
