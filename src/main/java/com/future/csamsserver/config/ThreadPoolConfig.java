package com.future.csamsserver.config;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @dateTime 2025:05:10 22:40
 * @user Jenming
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("statisticsThreadPool")
    public ExecutorService statisticsThreadPool() {
        int corePoolSize = 2; // 核心线程数，允许统计慢一点，建议设置为 CPU 核心数的 1/2~1 倍 [[8]]
        int maxPoolSize = 4;  // 最大线程数，避免资源过度占用
        int queueCapacity = 100; // 任务队列容量，缓冲等待执行的任务 [[5]]

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("Statistics-Pool-"); // 线程名前缀，便于监控 [[7]]
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略：由调用线程处理 [[9]]
        executor.initialize();
        return executor.getThreadPoolExecutor();
    }
    /*
    * todo 要是两对象不一致呢，待验证，已在监听器实现优雅关闭
    * */
//    @PreDestroy
//    public void destroyThreadPool() {
//        ExecutorService executor = (ExecutorService) statisticsThreadPool();
//        executor.shutdown(); // 拒绝新任务，等待已提交任务完成 [[6]]
//        try {
//            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) { // 等待超时 [[9]]
//                executor.shutdownNow(); // 超时后强制中断未完成的任务
//            }
//        } catch (InterruptedException e) {
//            executor.shutdownNow();
//            Thread.currentThread().interrupt();
//        }
//    }
}