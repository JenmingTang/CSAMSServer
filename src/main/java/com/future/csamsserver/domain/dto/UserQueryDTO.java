package com.future.csamsserver.domain.dto;

import com.future.csamsserver.domain.param.BasePageQueryParam;
import com.future.csamsserver.domain.param.PageParam;
import com.future.csamsserver.domain.param.TimeRangeParam;
import lombok.Data;

/**
 * @description
 * @dateTime 2025:03:05 22:52
 * @user Jenming
 */
@Data
public class UserQueryDTO extends BasePageQueryParam {
//    private PageParam pageParam;
//    private TimeRangeParam timeRangeParam;

    // 其他特有字段
    private String username;
    private Boolean isEnabled;

}