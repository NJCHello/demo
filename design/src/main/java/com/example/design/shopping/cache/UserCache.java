package com.example.design.shopping.cache;

import com.alibaba.fastjson2.JSON;
import com.example.designpattern.shopping.cache.context.AbstractCache;
import com.example.designpattern.shopping.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户缓存
 *
 * @author 寒江雪
 * @createDate 2024-05-29 22:39
 */
@Slf4j
@Component
public class UserCache extends AbstractCache<Long, User> {

    private final Map<Long, User> userMap = new HashMap<>();

    /**
     * 初始化缓存
     */
    @Override
    public void init() {
        this.userMap.put(1L, User.builder().id(1L).name("Lucy").gender("女").age(18).type(0).build());
        this.userMap.put(2L, User.builder().id(2L).name("Jack").gender("男").age(20).type(1).build());
        this.userMap.put(3L, User.builder().id(3L).name("Mick").gender("男").age(22).type(2).build());
        this.userMap.put(4L, User.builder().id(4L).name("Andy").gender("女").age(23).type(3).build());
        this.userMap.put(5L, User.builder().id(5L).name("Pelin").gender("女").age(23).type(4).build());
        log.info("user cache init success: {}", JSON.toJSONString(this.userMap));
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    @Override
    public User get(Long key) {
        return this.userMap.get(key);
    }

    /**
     * 清空缓存
     */
    @Override
    public void clear() {
        this.userMap.clear();
    }
}
