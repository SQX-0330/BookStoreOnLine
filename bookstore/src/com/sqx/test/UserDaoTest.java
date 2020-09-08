package com.sqx.test;

import com.sqx.dao.UserDao;
import com.sqx.dao.impl.UserDaoImpl;
import com.sqx.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/8/30 - 10:13
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
//        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户名已存在！");
        }
    }


    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin", "admin") == null){
            System.out.println("用户名或密码错误，登陆失败！");
        }else{
            System.out.println("登陆成功！");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "admin", "admin123", "admin111@qq.com")));
    }
}