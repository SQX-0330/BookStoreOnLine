package com.sqx.web;

import com.sqx.pojo.User;
import com.sqx.service.UserService;
import com.sqx.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author SQX
 * @date 2020/9/4 - 19:36
 */
public abstract class BaseServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决post请求的中文乱码问题
        //一定要在获取请求参数之前调用才有效
        req.setCharacterEncoding("utf-8");
        //解决响应中文乱码
        resp.setContentType("text/html; charset=utf-8");

        String action = req.getParameter("action");

        try {
            //获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

//            System.out.println(method);

            //调用目标业务方法
            method.invoke(this, req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
