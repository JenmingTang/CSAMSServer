package com.future.csamsserver.domain.sa;

import lombok.Data;

import java.util.List;

/**
 * @description
 * @dateTime 2025:03:05 23:01
 * @user Jenming
 */
@Data
public class SAResult<T> {
    private List<T> records;
    private long current;
    private long size;
    private long total;
}