package com.example.demo.service;

import java.util.Map;

/**
 * Created by houlong on 2018/5/8.
 */
public interface JdbcService {

    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create(String name, Integer age);

    /**
     * 根据name删除一个用户高
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取用户
     */
    Map<String, Object> getUser(Integer id);

}
