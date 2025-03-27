package com.future.csamsserver.service;

import cn.hutool.extra.mail.MailUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @description
 * @dateTime 2025:03:11 20:27
 * @user Jenming
 */
@Service
public class AsyncEmailService {
    @Async
    public CompletableFuture<String> asyncSendEmail(String to, String subject, String content) {
        try {
            String result = MailUtil.send(to, subject, content, false);
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }
    @Async
    public CompletableFuture<String> asyncSendEmail(String to, String subject, String content,boolean isHtml) {
        try {
            String result = MailUtil.send(to, subject, content, isHtml);
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }
}
