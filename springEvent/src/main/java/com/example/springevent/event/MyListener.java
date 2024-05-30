package com.example.springevent.event;

import com.example.springevent.annotation.LogStopWatch;
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

    @LogStopWatch(taskName = "消息接收处理", id = "onApplicationEvent")
    @Async("myEventListenerThreadPool")
    @EventListener({MyEvent.class})
    public void onApplicationEvent(MyEvent event) {
        log.info("MyEvent --------------- {}", event.getId());
    }
}
