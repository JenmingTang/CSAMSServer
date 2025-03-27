package com.future.csamsserver.domain.param;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description
 * @dateTime 2025:03:05 22:53
 * @user Jenming
 */
@Data
public class BasePageQueryParam {
    private Long current = 1L;  // 默认值
    private Long size = 10L;
    private LocalDateTime createTimeStart;
    private LocalDateTime createTimeEnd;
}