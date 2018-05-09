package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Spring Boot构建RESTful API
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final int SEED = 100;
    static ConcurrentMap<Integer, User> map = new ConcurrentHashMap<>();

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping()
    public List<User> getUserList() {
        List<User> users = new ArrayList<>(map.values());
        return users;
    }

    /**
     * 添加用户
     * @return
     */
    @PostMapping()
    public String addUser(@ModelAttribute User user) {
        user.setId((int)(Math.random() * SEED));
        map.put(user.getId(), user);
        return "success";
    }

    /**
     * 获取用户
     * @param uid
     * @return
     */
    @GetMapping(value = "/{id}")
    public User getUserDetail(@PathVariable("id") int uid) {
       User user = map.get(uid);
       return user;
    }

    /**
     * 删除用户
     * @param uid
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable("id") int uid) {
        map.remove(uid);
        return "success";
    }
}
