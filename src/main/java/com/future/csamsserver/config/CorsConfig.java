package com.future.csamsserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description
 * @dateTime 2025:03:20 00:18
 * @user Jenming
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
//
//    /// /        registry.addInterceptor(sysMonitorLogInterceptor)
//    /// /                .addPathPatterns("/**") // 拦截所有路径
//    /// /                .excludePathPatterns("/health", "/favicon.ico"); // 排除某些路径
    ////
    ////        /**
    ////         * 前端axios必要的跨域配置
    ////         * 前端使用axios时，会先发送一个options请求，这个请求不会携带cookie，所以后端会返回401，导致后续的请求失败
    ////         * 解决方法：在拦截器中添加一个options请求的处理
    ////         */
    ////        registry.addInterceptor(new HandlerInterceptor() {  //  options跨域问题解决
    ////            @Override
    ////            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    ////                response.setHeader("Access-Control-Allow-Origin", "*");
    ////                response.setHeader("Access-Control-Allow-Credentials", "true");
    ////                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
    ////                response.setHeader("Access-Control-Max-Age", "86400");
    ////                response.setHeader("Access-Control-Allow-Headers", "*");
    ////                // 如果是OPTIONS则结束请求
    ////                if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
    ////                    response.setStatus(HttpStatus.NO_CONTENT.value());
    ////                    return false;
    ////                }
    ////                return true;
    ////            }
    ////        }).addPathPatterns("/**");
    ////    }
    ///
    ///
    ///
    //    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 移除对OPTIONS请求的特殊处理，仅保留必要逻辑
//        registry.addInterceptor(new HandlerInterceptor() {
//            @Override
//            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                return true; // 不拦截任何请求，让CORS框架处理
//            }
//        }).addPathPatterns("/**");
//    }



    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(false)
                //放行哪些原始域
                .allowedOrigins("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}

