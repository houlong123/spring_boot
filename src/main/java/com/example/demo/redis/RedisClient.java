package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by houlong on 2018/5/4.
 */
@Component
public class RedisClient {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }


    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            return operations.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setEntity(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加zset集合
     * @param key
     * @param value
     * @return
     */
    public boolean zset(final String key, String ... value) {
        boolean result = false;
        try {
            SetOperations<Serializable, String> operations = redisTemplate.opsForSet();
            Long update = operations.add(key, value);
            if (update == 1L) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取
     * @param key
     * @return
     */
    public Set<String> zget(final String key) {
        SetOperations<Serializable, String> operations = redisTemplate.opsForSet();
        Set<String> values = operations.members(key);
        return values;
    }

    /**
     * 添加list集合
     * @param key
     * @param value
     * @return
     */
    public boolean lpush(final String key, String value) {
        ListOperations<Serializable, String> operations = redisTemplate.opsForList();
        Long result = operations.leftPush(key, value);
        if (result == 1L) {
            return true;
        }
        return false;
    }

    /**
     * 获取list集合
     * @param key
     * @return
     */
    public String rpop(final String key) {
        ListOperations<Serializable, String> operations = redisTemplate.opsForList();
        String result = operations.rightPop(key);
        return result;
    }

    /**
     * 自增
     * @param key
     * @return
     */
    public int inrc(final String key) {
        ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
        Long result = operations.increment(key, 1L);
        return result.intValue();
    }

    /**
     * 自减
     * @param key
     * @return
     */
    public int desc(final String key) {
        ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
        Long result = operations.increment(key, -1L);
        return result.intValue();
    }

    /**
     * 长度
     * @param key
     * @return
     */
    public int llen(final String key) {
        ListOperations<Serializable, String> operations = redisTemplate.opsForList();
        Long result = operations.size(key);
        return result.intValue();
    }

    /**
     * 添加list集合
     * @param key
     * @return
     */
    public List<String> getList(final String key) {
        ListOperations<Serializable, String> operations = redisTemplate.opsForList();
        List<String> result = operations.range(key, 0L, -1L);

        return result;
    }
}
