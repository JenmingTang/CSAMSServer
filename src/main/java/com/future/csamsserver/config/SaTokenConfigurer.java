
package com.future.csamsserver.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.future.csamsserver.controller.PasswordController;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

/**
 * Sa-Token 配置类
 */
@Configuration
@Slf4j
public class SaTokenConfigurer implements WebMvcConfigurer {

    private final Environment environment;

    public SaTokenConfigurer(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        /*
         * 比runner快
         * */
        log.info("SaTokenConfigure.init");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 定义排除的鉴权路径（包括Thymeleaf静态资源路径）
        String[] excludePaths = {
                "/auth/**",          // 认证相关路径
                "/api-docs.yaml",    // API文档
                "/password/**",      // 密码重置等路径
                "/file/**",          // 文件上传/下载路径
                "/static/**",        // 静态资源
                "/public/**",        // Spring Boot默认静态资源目录
                "/resources/**",     // Spring Boot默认静态资源目录
                "/META-INF/resources/**", // WebJars等资源
                "/webjars/**",       // WebJars库
                "/favicon.ico",      // 网站图标


                /*
                * 视图获取静态资源
                * 考虑View控制器、SaToken、Web静态访问
                * */
                "/",                 // 根路径
                "/index",
                "/index2/**",
                "/view_club/**",
                "/view_activity/**",

                "/js/**",        // 静态资源
                "/images/**",        // 静态资源
                "/css/**",        // 静态资源
                "/images2/**",        // 静态资源
                "/css2/**",        // 静态资源
                "/images3/**",        // 静态资源
                "/css3/**",        // 静态资源
                "/images4/**",        // 静态资源
                "/css4/**",        // 静态资源
        };
        registry.addInterceptor(new SaInterceptor(handler -> {
                    // 获取所有的URL并进行检查
                    SaRouter.match("/**")
                            .check(() -> {
                        // 检查是否登录
                        try {
                            StpUtil.checkLogin();
                        } catch (Exception e) {
                            SaRouter.back(SaResult.error("请登录！！"));
                        }
                    });
                    /*
                    * 开启注解鉴权
                    * */
                }).isAnnotation(true))
                .addPathPatterns("/**")  // 拦截所有路径
                .excludePathPatterns(excludePaths);  // 排除指定路径

}
}

