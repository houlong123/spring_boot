package com.example.demo.controller;

import com.example.demo.exception.JSONException;
import com.example.demo.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring Boot 异常处理
 */
@Controller
public class ErrorController {

    @GetMapping(value = "/hello")
    public String hello() throws Exception {
        System.out.println("来啦");
        throw new Exception("发生异常");
    }

    @GetMapping(value = "/hei")
    public String hei() throws Exception {
        System.out.println("好，来啦");
        throw new MyException("发生异常");
    }

    @GetMapping(value = "/json_error")
    public String jsonError() throws Exception {
        System.out.println("JSON，来啦");
        throw new JSONException("JSON格式异常");
    }
}
