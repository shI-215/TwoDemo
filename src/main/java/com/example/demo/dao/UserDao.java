package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    User findUserById(int id);

    List findAllUser();

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
