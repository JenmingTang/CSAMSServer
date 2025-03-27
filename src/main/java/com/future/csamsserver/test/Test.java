package com.future.csamsserver.test;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.future.csamsserver.utils.ProgressBarUtil;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;


/**
 * @description
 * @dateTime 2025:03:04 21:27
 * @user Jenming
 */
public class Test {
    @SneakyThrows
    public static void main(String[] args) {

        final String image = "asd.jpg";
        final String substring = image.substring(0, image.lastIndexOf("."));
        System.out.println("substring = " + substring);


//        String email = "625004612@qq.com";
//        byte[] bytes = email.getBytes(StandardCharsets.UTF_8);
//        String hex = HexUtil.encodeHexStr(bytes);
//        System.out.println("Hex: " + hex);
//        java: 3632353030343631324071712e636f6d
//        mysql: 3632353030343631324071712E636F6D
// 输出示例：3632353030343631324071712e636f6d
//        final CustomExpiryMap<String, String> expiryMap = new CustomExpiryMap<>();
//        expiryMap.put("name","tang",3,TimeUnit.SECONDS);
//        System.out.println("expiryMap.get(\"name\") = " + expiryMap.get("name"));
//        ThreadUtil.sleep(4000);
//        System.out.println("expiryMap.get(\"name\") = " + expiryMap.get("name"));
//        System.out.println(expiryMap.get("name")==null);

        System.out.println("============================================");
        // 创建缓存，默认过期时间5秒
        TimedCache<String, String> cache = CacheUtil.newTimedCache(5 * 1000);
        cache.put("key", "value");
// 设置监听器
        cache.setListener((key, value) -> System.out.println(key + "过期"));
// 启动定时清理任务（默认每秒检查一次）
        cache.schedulePrune(1000);
        System.out.println("============================================");

//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        GlobalMailAccount.INSTANCE.getAccount()
//                .setAuth(true)
//                .setSslEnable(true)
//                .setCustomProperty("mail.smtp.ssl.socketFactory", sf);
//        MailUtil.send("625004612@qq.com", "测试2", "邮件来自Hutool测试2", false);
//        sendEmail();
        //定义图形验证码的长和宽
//        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        System.out.println("============================================");
//        encryption();
//        desensitizedUtil();
//        progressBarUtil();
//        UserInfo user = new UserInfo();
//        user.setMobile("13812345678");
//        user.setEmail("zhangsan@alibaba.com");
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(user);
//        System.out.println("json = " + json);
//        json = {"mobile":"138****5678","email":"z****n@alibaba.com"}


// 输出结果：
// {"mobile":"138****5678","email":"z****n@alibaba.com"}

    }

    @SneakyThrows
    private static void sendEmail() {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setAuth(true);
        mailAccount.setSslEnable(true);
//
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        mailAccount.setCustomProperty("mail.smtp.ssl.socketFactory", sf);

        Mail mail = Mail.create(mailAccount)
                .setTos("625004612@qq.com")
                .setTitle("邮箱验证")
                .setContent("您的验证码是：<h3>2333</h3>")
                .setHtml(true);
        mail.send();
    }

    private static void progressBarUtil() {
        // 使用默认参数（总任务量100，长度30，间隔200ms）
        ProgressBarUtil.ProgressController controller = ProgressBarUtil.start();
// 自定义参数（总任务量200，长度50，间隔100ms）
//        ProgressBarUtil.ProgressController controller2 = ProgressBarUtil.start(200, 50, 100);
// 控制方法
//        controller.pause();    // 暂停
//        controller.resume();   // 继续
//        controller.restart();  // 重新开始
//        controller.terminate();// 终止
    }
    private static void desensitizedUtil() {
        // 5***************1X
        final String idCardNum = DesensitizedUtil.idCardNum("51343620000320711X", 1, 2);
        System.out.println("idCardNum = " + idCardNum);
        // 180****1999
        final String mobilePhone = DesensitizedUtil.mobilePhone("18049531999");
        System.out.println("mobilePhone = " + mobilePhone);

        // **********
        final String password = DesensitizedUtil.password("1234567890");
        System.out.println("password = " + password);
        final String email = DesensitizedUtil.email("1234567890@qq.com");
        System.out.println("email = " + email);
    }

    /*
    * 用户密码加密方法
    * */
    private static void encryption() {
        System.out.println("SecureUtil.md5(\"123456\") = " + SecureUtil.md5("123456"));
    }

}
