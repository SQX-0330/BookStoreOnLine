package com.sqx.web;

import com.google.gson.Gson;
import com.sqx.pojo.Book;
import com.sqx.pojo.Cart;
import com.sqx.pojo.CartItem;
import com.sqx.service.BookService;
import com.sqx.service.impl.BookServiceImpl;
import com.sqx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SQX
 * @date 2020/9/6 - 10:22
 */
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //获取请求参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化成为CartItem的对象
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem) 添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
//        System.out.println(cart);;

//        System.out.println("请求头referer的值" + req.getHeader("Referer"));

        //重定向回商品原来所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        //重定向回原来展示界面
        resp.sendRedirect(req.getHeader("Referer"));

    }


    /**
     * 删除购物车商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //删除了购物车商品项
            cart.deleteItem(id);
            //重定向回原来展示界面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }


    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改商品数量
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数   商品编号和商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * ajax实现商品的提示
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //获取请求参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化成为CartItem的对象
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem) 添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
//        System.out.println(cart);;

//        System.out.println("请求头referer的值" + req.getHeader("Referer"));

//        //重定向回商品原来所在的地址页面
//        resp.sendRedirect(req.getHeader("Referer"));

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        //6.返回购物车总的商品数量和最后一个添加的商品
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());

        Gson gson = new Gson();
        String resultMapToString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapToString);
    }

}

