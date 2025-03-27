package com.future.csamsserver.utils;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @description
 * @dateTime 2025:03:10 23:59
 * @user Jenming
 */
// 1. 创建 Mapper 重载工具类
//    @Component
public class MybatisMapperReloader {
//    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public void reloadMappers() throws Exception {
        Configuration configuration = sqlSessionFactory.getConfiguration();

        // 清理旧缓存
//        configuration.getMappedStatements().forEach(configuration::clearCache);
        configuration.getResultMaps().clear();
        configuration.getParameterMaps().clear();
        configuration.getSqlFragments().clear();

        // 重新加载 XML（需根据实际路径调整）
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/**/*.xml");
        for (Resource resource : resources) {
            try (InputStream is = resource.getInputStream()) {
                XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(is,
                        configuration, resource.toString(), configuration.getSqlFragments());
                xmlMapperBuilder.parse();
            }
        }
    }
}
