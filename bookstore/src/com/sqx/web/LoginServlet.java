package com.sqx.web;

import com.sqx.pojo.User;
import com.sqx.service.UserService;
import com.sqx.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 和RegistServlet合并为UserServlet
 *
 * @author SQX
 * @date 2020/9/4 - 11:06
 */
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调xxxService.xxx()处理业务
        //userService.login()登录
        User loginUser = userService.login(new User(null, username, password, null));

        //根据login()方法返回结果判断登录是否成功
        //loginUser == null 说明登录失败
        if (loginUser == null) {
            //把错误信心和回显的表单项信息，保存到Request域中
            req.setAttribute("msg", "用户名或密码错误!");
            req.setAttribute("username", username);


            //失败，跳回登录界面
            //System.out.println("登录失败");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //成功，跳转到login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);


        }


    }
}
