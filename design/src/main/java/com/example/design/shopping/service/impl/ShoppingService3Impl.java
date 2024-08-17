package com.example.design.shopping.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.designpattern.shopping.cache.MerchandiseCache;
import com.example.designpattern.shopping.cache.UserCache;
import com.example.designpattern.shopping.entity.Merchandise;
import com.example.designpattern.shopping.entity.User;
import com.example.designpattern.shopping.service.ShoppingService;
import com.example.designpattern.shopping.strategy.context.ShoppingStrategyAware;
import com.example.designpattern.shopping.strategy.factory.ShoppingFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 工厂 + 策略模式实现
 *
 * @author 寒江雪
 * @createDate 2024-05-29 23:26
 */
@Slf4j
@Service("shoppingService3")
public class ShoppingService3Impl implements ShoppingService {

    @Resource
    private ShoppingFactory shoppingFactory;

    @Resource
    private UserCache userCache;

    @Resource
    private MerchandiseCache merchandiseCache;

    /**
     * 购买
     *
     * @param userId        用户ID
     * @param merchandiseId 商品ID
     * @return 购买结果
     */
    @Override
    public Map<String, Object> buy(Long userId, Long merchandiseId) {
        User user = userCache.get(userId);
        Merchandise merchandise = merchandiseCache.get(merchandiseId);
        log.info("current user: {}", JSON.toJSONString(user));
        log.info("current merchandise: {}", JSON.toJSONString(merchandise));
        ShoppingStrategyAware strategy = shoppingFactory.getStrategy(user.getType());
        return strategy.buy(user, merchandise);
    }
}
