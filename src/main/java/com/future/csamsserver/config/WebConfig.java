package com.future.csamsserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @dateTime 2025:03:20 00:22
 * @user Jenming
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {


    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(System.getProperty("user.dir"), uploadPath)
                .normalize().toAbsolutePath();
        /*
         * index2.html
         * */
        registry.addResourceHandler(
                        "/" + uploadPath + "/**",
                        /*
                         * 视图获取静态资源
                         * 考虑View控制器、SaToken、Web静态访问
                         * */
                        "index/" + uploadPath + "/**",
                        "index2/" + uploadPath + "/**",
                        "view_club/" + uploadPath + "/**",
                        "view_activity/" + uploadPath + "/**"
                        )
                .addResourceLocations("file:" + uploadDir + "/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .resourceChain(true)
                .addResolver(new EncodedResourceResolver());
    }
}
