package com.future.csamsserver.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.future.csamsserver.domain.User;
import com.future.csamsserver.domain.dto.ResetPasswordDTO;
import com.future.csamsserver.mapper.UserMapper;
import com.future.csamsserver.service.AsyncEmailService;
import com.future.csamsserver.utils.ExpiryMapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @dateTime 2025:03:11 19:58
 * @user Jenming
 */
@Slf4j
@RestController
@RequestMapping("password")
public class PasswordController {
    public static final String SEND_EMAIL_KEY = "send-email";
    public static final ExpiryMapUtils<String, String> expiryMap = new ExpiryMapUtils<String, String>();
    private final UserMapper userMapper;
    private final AsyncEmailService asyncEmailService;

    public PasswordController(UserMapper userMapper, AsyncEmailService asyncEmailService) {
        this.userMapper = userMapper;
        this.asyncEmailService = asyncEmailService;
    }

    @GetMapping(PasswordController.SEND_EMAIL_KEY)
    public SaResult sendEmail(String email) {
//    @PostMapping(PasswordController.SEND_EMAIL_KEY)
//    public SaResult sendEmail(@RequestBody String email) {
        log.info("sendEmail: email={}", email);
        if (email == null || email.isEmpty()) {
            return SaResult.error("邮箱不得为空！");
        }// 确保处理空格并打印日志
        /*
        * 有毛病 LambdaQueryWrapper todo
        * */
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email.trim());
        User user = userMapper.selectOne(wrapper);
        /*
        * 毛病 todo
        * */
//        final User user = userMapper.selectUserByEmail(email.trim());

        if (user == null) {
            log.error("邮箱不存在：{}", email);
            return SaResult.error("邮箱尚未被注册！");
        }
        final String code = RandomUtil.randomNumbers(6);
        /*
         * 一直报未登录错误，没会话
         * */
//        StpUtil.getSession().set(PasswordController.SEND_EMAIL_KEY, code);
//        final String sent = MailUtil.send(user.getEmail(), "重置密码验证码", code, false);

        /*
         * 验证码3分钟过期
         * */
        expiryMap.put(code, code, 3, TimeUnit.MINUTES);

        // 提交异步任务
        CompletableFuture<String> future = asyncEmailService.asyncSendEmail(
                user.getEmail(),
                "重置密码验证码（有效期3分钟）",
                code
        );

        // 处理异步结果（可选）
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("邮件发送失败", ex);
            } else {
                log.info("邮件发送成功: {}", result);
            }
        });
        return SaResult.ok("验证码发送请求已接收");
    }

    @PostMapping("reset")
    public SaResult reset(@RequestBody ResetPasswordDTO resetPasswordDTO) {

        log.info("reset: resetPasswordDTO={}", resetPasswordDTO);
        /*
         *
         * */
        /*
         * 新旧密码是否一致
         * */
        if (!Objects.equals(resetPasswordDTO.getPassword(), resetPasswordDTO.getConfirmPassword())) {
            return SaResult.error("两次密码不一致！");
        }
        if (!Objects.equals(expiryMap.get(resetPasswordDTO.getCode()), resetPasswordDTO.getCode())) {
            return SaResult.error("验证码错误！");
        }
        final LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(resetPasswordDTO.getEmail() != null, User::getEmail, resetPasswordDTO.getEmail());
        final User user = userMapper.selectOne(wrapper);
        user.setPassword(SecureUtil.md5(resetPasswordDTO.getPassword()));
        final boolean inserted = userMapper.insertOrUpdate(user);
        if (inserted) {
            StpUtil.logout(user.getId());
        }
        return SaResult.data(inserted);
    }
}
