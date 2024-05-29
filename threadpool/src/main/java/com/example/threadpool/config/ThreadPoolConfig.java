package com.example.threadpool.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chenghao
 * @since 2024/5/29 16:40
 */
@Data
@Component
@ConfigurationProperties(prefix = "thread.pool")
public class ThreadPoolConfig {
    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;
}