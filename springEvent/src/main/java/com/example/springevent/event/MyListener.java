package com.example.springevent.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author chenghao
 * @since 2024/5/30 10:44
 */
@Component
@Slf4j
public class MyListener {

    @Async("myEventListenerThreadPool")
    @EventListener({MyEvent.class})
    public void onApplicationEvent(MyEvent event) {
        log.info("MyEvent --------------- {}", event.getId());
    }
}
