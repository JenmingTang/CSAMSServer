package com.future.csamsserver.domain.param;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description
 * @dateTime 2025:03:05 22:51
 * @user Jenming
 */

@Data
public class TimeRangeParam {
    private LocalDateTime createTimeStart;
    private LocalDateTime createTimeEnd;
}
