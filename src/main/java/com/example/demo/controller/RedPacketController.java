package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.example.demo.entity.User;
import com.example.demo.redis.RedisClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by houlong on 2018/5/4.
 */
@Controller
@RequestMapping("/red/packet")
public class RedPacketController {

    private final static int num = 1000;

    @Resource
    private RedisClient redisClient;

    @GetMapping("/pool")
    @ResponseBody
    public String redPacketPoolGen(HttpServletRequest request) {

        final CountDownLatch countDownLatch = new CountDownLatch(4);
        ExecutorService service = new ThreadPoolExecutor(4, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        int per = num / 4;

        String value;
        do {
            value = redisClient.rpop("redpacketpoolkey");
            System.out.println(value);
        } while ("" != value && null != value);

        User user = new User();
        user.setId(10);
        user.setAge(20);
        user.setName("houlong");
        boolean result = redisClient.setEntity("user", user);

        System.out.println(((User)redisClient.get("user")).getName());

        for (int index = 0; index < 4; index++) {
            int temp = index;
            service.execute(() -> {
                for (int j = temp * per + 1 ; j <= (temp + 1) * per; j++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", "rid_" + j);
                    jsonObject.put("money", j);
                    redisClient.lpush("redpacketpoolkey", jsonObject.toJSONString());
                }

                countDownLatch.countDown();
            });
        }

        service.shutdown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("执行完毕");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("tip", "红包池生成完毕");

        return jsonObject.toJSONString();
    }

    @ResponseBody
    @GetMapping("/{uid}")
    public String getRedpacket(@PathVariable(name = "uid")Integer uid) {


        String result = getRedpacket(uid + "");
        return result;
    }

    private String getRedpacket(String uid) {

        /**
         * 有红包，并抢占成功
         */
        if (redisClient.llen("redpacketpoolkey") > 0 && redisClient.zset("uidrecord", uid + "")) {
            String value = redisClient.rpop("redpacketpoolkey");
            JSONObject jsonObject = JSONObject.parseObject(value);
            jsonObject.put("uid", uid);
            redisClient.lpush("userRedpacketRecord", jsonObject.toJSONString());
            return jsonObject.toJSONString();
        } else if (redisClient.llen("redpacketpoolkey") <= 0) {
            return "红包已抢完";
        }
        return "用户已抢过，还抢！";
    }

    @ResponseBody
    @GetMapping("/list")
    public List<JSONObject> getUserRedpacketRecord() {
        List<String> values = redisClient.getList("userRedpacketRecord");

        List<JSONObject> list = new ArrayList<>(values.size());
        for (String value : values) {
            list.add(JSONObject.parseObject(value));
        }
        return list;
    }

}
