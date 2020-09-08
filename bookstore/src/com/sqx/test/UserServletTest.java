package com.sqx.test;

import java.lang.reflect.Method;

/**
 * @author SQX
 * @date 2020/9/4 - 19:15
 */
public class UserServletTest {
    public void login(){
        System.out.println("这是login()");
    }
    public void regist(){
        System.out.println("这是regist()");
    }
    public void updateUser(){
        System.out.println("updateUser()");
    }

    public static void main(String[] args) {
        String action  = "login";

        try {

            //获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);

            System.out.println(declaredMethod);

            //调用目标业务方法
            declaredMethod.invoke(new UserServletTest());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
