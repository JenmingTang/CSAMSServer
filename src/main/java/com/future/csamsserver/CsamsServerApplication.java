package com.future.csamsserver;

import cn.hutool.extra.mail.GlobalMailAccount;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.future.csamsserver.domain.User;
import com.future.csamsserver.mapper.UserMapper;
import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.servlet.ServletContext;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class CsamsServerApplication {

    public static void main(String[] args) {
        init();
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(CsamsServerApplication.class, args);
        final ServletContext servletContext = applicationContext.getBean(ServletContext.class);
//        System.out.println("servletContext.getContextPath() = " + servletContext.getContextPath());
//        ""
//        final List<User> users = applicationContext.getBean(UserMapper.class).selectList(new LambdaQueryWrapper<User>()
//                .select(User::getUsername));
//        System.out.println("users = " + users);
    }

    /*
    * Java上下文初始化
    * */
    @SneakyThrows
    private static void init() {
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        GlobalMailAccount.INSTANCE.getAccount()
                .setAuth(true)
                .setSslEnable(true)
                .setCustomProperty("mail.smtp.ssl.socketFactory", sf);
    }

}
