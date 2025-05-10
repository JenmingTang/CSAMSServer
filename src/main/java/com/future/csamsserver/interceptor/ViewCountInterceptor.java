package com.future.csamsserver.interceptor;

import com.future.csamsserver.service.StatisticsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.ExecutorService;

/**
 * @description
 * @dateTime 2025:05:10 22:36
 * @user Jenming
 */
@Component
public class ViewCountInterceptor implements HandlerInterceptor {

    @Autowired
    private StatisticsService statisticsService;

    @Qualifier("statisticsThreadPool") // 注入全局线程池
    @Autowired
    private ExecutorService threadPool;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 忽略静态资源和API请求
//        if (request.getRequestURI().startsWith("/api") ||
//                request.getRequestURI().startsWith("/static")) {
//            return true;
//        }

        String pageUri = request.getRequestURI();
        // 异步执行以避免阻塞请求（需配置线程池）
//        new Thread(() -> {
//            statisticsService.increment("page_views", "counter", pageUri);
//        }).start();

        threadPool.execute(() -> { // 提交异步任务 [[5]]
            statisticsService.increment("viewCount");
        });
        return true;
    }
}
