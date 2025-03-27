package com.future.csamsserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executors;

/**
 * @description
 * @dateTime 2025:03:11 20:24
 * @user Jenming
 */
/*
在发送邮件这类阻塞IO操作时，建议采用以下异步方案优化性能（基于JDK17特性）：
方案一：使用Spring Async + 虚拟线程（推荐）
开启异步支持
* */
@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean
    public AsyncTaskExecutor asyncTaskExecutor() {
        // 使用虚拟线程执行器（需JDK21+，若为JDK17可用传统线程池）
//        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
        return new TaskExecutorAdapter(Executors.newFixedThreadPool(2));
    }
}

