package com.example.demo.service.impl;

import com.example.demo.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by houlong on 2018/5/8.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JdbcServiceImpl implements JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into t_order(name, age) values(?, ?)", name, age);
        System.out.println("插入成功");
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("delete from t_order WHERE id = ?", id);
        System.out.println("删除成功");
    }

    @Override
    public Map<String, Object> getUser(Integer id) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_order where id = ?", id);
        System.out.println("查询成功");
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
