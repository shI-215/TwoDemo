package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User selectUserById(int id) {
//        redisTemplate.opsForValue().set("userid", userDao.findUserById(id));//将实体写入缓存,在需要用到的地方用
//        redisTemplate.opsForValue().get("userid"); //进行获取数据
        return userDao.findUserById(id);
    }

    @Override
    public List selectUserAll() {
        //清空
        while (redisTemplate.opsForList().size("user") > 0) {
            redisTemplate.opsForList().leftPop("user");
        }
        //向redis的某个key下面的list列表里面插入一个list列表，不会去重。
        redisTemplate.opsForList().rightPushAll("user", userDao.findAllUser());
        return userDao.findAllUser();
    }

    @Override
    public int addUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }

}
