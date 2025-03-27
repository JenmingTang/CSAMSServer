package com.future.csamsserver.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @dateTime 2025:03:11 00:00
 * @user Jenming
 */

// 2. Controller 触发入口
//@RestController
public class HotReloadController {
//    @Autowired
    private MybatisMapperReloader reloader;

    @PostMapping("/reload-mapper")
    public String reloadMapper() throws Exception {
        reloader.reloadMappers();
        return "Mapper reloaded!";
    }
}
