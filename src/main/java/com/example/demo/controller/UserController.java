package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JsonUtil;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final Log log = LogFactory.getLog(UserController.class);
    private JsonUtil jsonUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/user/selectUserById/{id}", method = RequestMethod.GET)
    public User SelectUserById(@PathVariable int id) {
        log.info(id + " UserController SelectUserById");
        return userService.selectUserById(id);
    }

    @RequestMapping(value = "/user/selectUserAll", method = RequestMethod.POST)
    public JsonUtil SelectUserAll() {
        jsonUtil = new JsonUtil();
        try {
            //从redis中取出某一个key下面的list列表，
            // 0表示从列表的第0个元素开始取，
            // -1表示直取到倒数第一个元素，也就是整个列表的所有元素都取出来。
            List<User> list = redisTemplate.opsForList().range("user", 0, -1);
            if (list.size() == 0) {//如果缓存中的数据为空的时候进行重新获取数据
                list = userService.selectUserAll();
            }
            Map<Integer, User> map = list.stream().collect(Collectors.toMap(User::getId, user -> user));
            log.info(list.toString() + " UserController " + map.toString());
            jsonUtil.setSuccess(true);
            jsonUtil.setAttributes(map);
            jsonUtil.setMsg("查询成功！");
        } catch (Exception e) {
            jsonUtil.setSuccess(false);
            jsonUtil.setMsg("查询失败！");
        }
        return jsonUtil;
    }

    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
    public int AddUser(@RequestBody User user) {
        log.info(user.toString() + " UserController AddUser");
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user/updateUser", method = RequestMethod.PUT)
    public int UpdateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/user/deleteUser", method = RequestMethod.DELETE)
    public int DeleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

}