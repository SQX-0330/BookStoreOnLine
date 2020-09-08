package com.sqx.service.impl;

import com.sqx.dao.UserDao;
import com.sqx.dao.impl.UserDaoImpl;
import com.sqx.pojo.User;
import com.sqx.service.UserService;

/**
 * @author SQX
 * @date 2020/8/30 - 10:35
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {

        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username) == null ){
            //等于null，说明没查到，没查到表示可用
            return false;
        }else{
            return true;
        }


    }
}
