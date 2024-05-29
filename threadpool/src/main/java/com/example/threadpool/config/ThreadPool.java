package com.example.threadpool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chenghao
 * @since 2024/5/29 16:45
 */
@Configuration
public class ThreadPool {

    @Resource
    private ThreadPoolConfig threadPoolConfig;

    @Bean(name = "threadPoolA")
    public ThreadPoolTaskExecutor threadPoolA() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数目
        executor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
        //指定最大线程数
        executor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
        //队列中最大的数目
        executor.setQueueCapacity(threadPoolConfig.getQueueCapacity());
        //线程名称前缀
        executor.setThreadNamePrefix("ThreadPool_A_");
        //rejection-policy：当pool已经达到max size的时候，如何处理新任务
        //CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(threadPoolConfig.getKeepAliveSeconds());
        //加载
        executor.initialize();
        return executor;
    }

    @Bean(name = "threadPoolB")
    public ThreadPoolTaskExecutor threadPoolB() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数目
        executor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
        //指定最大线程数
        executor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
        //队列中最大的数目
        executor.setQueueCapacity(threadPoolConfig.getQueueCapacity());
        //线程名称前缀
        executor.setThreadNamePrefix("ThreadPool_B_");
        //rejection-policy：当pool已经达到max size的时候，如何处理新任务
        //CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(threadPoolConfig.getKeepAliveSeconds());
        //加载
        executor.initialize();
        return executor;
    }
}