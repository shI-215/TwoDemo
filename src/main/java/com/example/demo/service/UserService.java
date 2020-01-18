package com.example.demo.service;


import com.example.demo.bean.User;

import java.util.List;

public interface UserService {

    User selectUserById(int id);

    List selectUserAll();

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
