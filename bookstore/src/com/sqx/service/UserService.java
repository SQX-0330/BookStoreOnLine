package com.sqx.service;

import com.sqx.pojo.User;

/**
 * @author SQX
 * @date 2020/8/30 - 10:30
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登陆
     * @param user
     * @return 如果返回努力了说明登陆失败，返回有值，登陆成功
     */
    public User login(User user);

    /**
     *检查用户名是否可用
     * @param username
     * @return 返回true 表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);
}
