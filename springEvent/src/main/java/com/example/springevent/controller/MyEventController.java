package com.example.springevent.controller;

import com.example.springevent.event.MyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenghao
 * @since 2024/5/30 10:52
 */
@RestController
@RequestMapping("/myEvent")
public class MyEventController {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * http://localhost:8080/myEvent/publishEvent?id=11112
     * @param id
     */
    @GetMapping("/publishEvent")
    public void publishEvent(String id) {
        applicationContext.publishEvent(new MyEvent(id));
    }
}
