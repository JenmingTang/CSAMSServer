package com.future.csamsserver.domain.param;

import lombok.Data;

/**
 * @description
 * @dateTime 2025:03:05 22:51
 * @user Jenming
 */
@Data
public class PageParam {
    private Long current = 1L;
    private Long size = 10L;
}
