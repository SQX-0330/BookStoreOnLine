package com.sqx.web;

import com.sqx.pojo.Cart;
import com.sqx.pojo.OrderItem;
import com.sqx.pojo.User;
import com.sqx.service.OrderService;
import com.sqx.service.impl.OrderServiceImpl;
import com.sqx.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author SQX
 * @date 2020/9/6 - 21:22
 */
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取cart购物对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userId
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = loginUser.getId();
        //调用orderService.createOrder(Cart, UesrId);生成订单
        String orderId = orderService.createOrder(cart, userId);

//        req.setAttribute("orderId", orderId);
        req.getSession().setAttribute("orderId", orderId);


        //请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
