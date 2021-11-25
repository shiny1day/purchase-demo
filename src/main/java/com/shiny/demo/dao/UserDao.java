package com.shiny.demo.dao;

import com.shiny.demo.entity.User;

public interface UserDao {

    //登录判断
    User login(User user);

    //注册
    int addUser(User user);

}
