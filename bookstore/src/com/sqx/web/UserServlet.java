package com.sqx.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import com.sqx.pojo.User;
import com.sqx.service.UserService;
import com.sqx.service.impl.UserServiceImpl;
import com.sqx.test.UserServletTest;
import com.sqx.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author SQX
 * @date 2020/9/4 - 18:45
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 username
        String username = req.getParameter("username");
        //调用userService.existUsername();
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成Map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }


    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //处理登录的需求
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

            //保存用户登录之后的信息到session域中
            req.getSession().setAttribute("user", loginUser);

            //成功，跳转到login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);


        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //销毁session中用户登录信息（或者销毁Session）
        req.getSession().invalidate();
        //重定向到主页（或登陆界面）
        resp.sendRedirect(req.getContextPath());
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //处理注册的请求
        //1.获取请求的参数

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        User user = new User();
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //2.检查验证码是否正确  === 要求验证码为abcde
        if (token != null && token.equalsIgnoreCase(code)) {
            //3.正确，检查 用户名是否可用
            if (userService.existsUsername(username)) {
                //不可用
                System.out.println("用户名[" + username + "]已存在！");

                //把回显信息，保存到request域中
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //4. 可用， 调用userService保存到数据库
                userService.registUser(new User(null, username, password, email));

                //跳转到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);

            }

        } else {
            //把回显信息，保存到request域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

        }
    }

}
