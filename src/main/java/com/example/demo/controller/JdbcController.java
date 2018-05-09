package com.example.demo.controller;

import com.example.demo.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by houlong on 2018/5/8.
 */
@RestController
@RequestMapping("/orders")
public class JdbcController {

    @Autowired
    private JdbcService jdbcService;

    @PostMapping()
    public String addOrder(HttpServletRequest request) {

        jdbcService.create(request.getParameter("name"), Integer.parseInt(request.getParameter("age")));
        return "success";
    }


    @DeleteMapping(value = "/{id}")
    public String deleteOrder(@PathVariable("id") Integer id) {

        jdbcService.delete(id);
        return "success";
    }

    @GetMapping(value = "/{id}")
    public Map<String, Object> getOrder(@PathVariable("id") Integer id) {

        Map<String, Object> map =  jdbcService.getUser(id);
        return map;
    }

}
