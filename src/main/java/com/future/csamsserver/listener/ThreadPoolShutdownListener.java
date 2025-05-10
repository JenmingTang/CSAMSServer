package com.future.csamsserver.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @dateTime 2025:05:10 22:50
 * @user Jenming
 */
@Component
public class ThreadPoolShutdownListener {

    @Autowired
    @Qualifier("statisticsThreadPool")
    private ExecutorService threadPool;

    @EventListener(ContextClosedEvent.class)
    public void onContextClosed() {
        System.out.println("ThreadPoolShutdownListener.onContextClosed");
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}