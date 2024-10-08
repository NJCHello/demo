package com.example.design.shopping.strategy;

import com.alibaba.fastjson2.JSON;
import com.example.designpattern.shopping.entity.Merchandise;
import com.example.designpattern.shopping.entity.User;
import com.example.designpattern.shopping.enums.DiscountEnum;
import com.example.designpattern.shopping.strategy.context.ShoppingStrategyAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 超级vip策略
 *
 * @author 寒江雪
 * @createDate 2024-05-30 12:32
 */
@Slf4j
@Component
public class SVIPStrategy implements ShoppingStrategyAware {
    @Override
    public DiscountEnum getStrategy() {
        log.info("load strategy: {}", JSON.toJSONString(DiscountEnum.SVIP));
        return DiscountEnum.SVIP;
    }

    @Override
    public Map<String, Object> buy(User user, Merchandise merchandise) {
        log.info("current strategy: {}", getStrategy().getType());
        DecimalFormat df = new DecimalFormat("0.00");
        Map<String, Object> result = new HashMap<>();
        result.put("用户ID", user.getId());
        result.put("用户姓名", user.getName());
        result.put("用户性别", user.getGender());
        result.put("用户年龄", user.getAge());
        result.put("用户等级", getStrategy().getType());
        result.put("商品名称", merchandise.getName());
        result.put("商品原价", df.format(merchandise.getPrice()));
        result.put("应享折扣", df.format(getStrategy().getDiscount()));
        result.put("折后价格", df.format(merchandise.getPrice().multiply(getStrategy().getDiscount())));
        return result;
    }
}
