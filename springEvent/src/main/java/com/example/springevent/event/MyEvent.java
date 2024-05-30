package com.example.springevent.event;

import lombok.Data;

/**
 * @author chenghao
 * @since 2024/5/30 10:42
 */
@Data
public class MyEvent {

    private String id;

    public MyEvent(String id) {
        this.id = id;
    }
}
