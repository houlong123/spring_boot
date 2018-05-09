package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通过配置文件获取属性
 */
@Component
public class BlogConfig {

    @Value("${com.blog.name}")
    private String name;

    @Value("${com.blog.title}")
    private String title;

    @Value("${com.blog.desc}")
    private String desc;

    @Value("${com.blog.number}")
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
