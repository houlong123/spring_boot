package com.example.demo.controller;

import com.example.demo.entity.BlogConfig;
import com.example.demo.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by houlong on 2018/5/4.
 */
@Controller
public class SampleController {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private BlogConfig config;

    @ResponseBody
    @RequestMapping("/")
    public String hello() {
        return "Hello World！我叫 " + config.getName() + ", 我的博客为：" + config.getTitle() + "。备注：" + config.getDesc() + "。这是第" + config.getNumber() + "篇" ;
    }
}
