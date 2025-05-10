package com.future.csamsserver.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @dateTime 2025:05:10 22:52
 * @user Jenming
 */
@Component
@Slf4j
public class CustomThreadPool implements DisposableBean {

//    private ExecutorService executor = Executors.newFixedThreadPool(4);

    static {
        System.out.println("CustomThreadPool.static initializer");
    }

    public CustomThreadPool() {
        System.out.println("CustomThreadPool.CustomThreadPool");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("CustomThreadPool.destroy");
//        executor.shutdown();
//        if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
//            executor.shutdownNow();
//        }
    }
}