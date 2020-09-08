package com.sqx.test;

import com.sqx.pojo.User;
import com.sqx.service.UserService;
import com.sqx.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/8/30 - 10:40
 */
public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "hhh123", "12345", "hhh123@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "admin", "admin", null)));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("admin")){
            System.out.println("用户名已存在！");
        }else{
            System.out.println("用户名可用！");
        }
    }
}