package com.example.demo.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 学习Spring Boot 安全控制
 */
@Controller
@RequestMapping("/security")
public class SecurityController {

    @GetMapping()
    public String index() {
        return "security/index";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "security/hello";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "security/login";
    }

}
