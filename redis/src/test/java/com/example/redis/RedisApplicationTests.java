package com.example.redis;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {

        RBucket<String> test = redissonClient.getBucket("key");

        System.out.println(test.get());
    }

}
