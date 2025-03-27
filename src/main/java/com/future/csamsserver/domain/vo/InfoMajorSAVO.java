package com.future.csamsserver.domain.vo;

import com.future.csamsserver.domain.InfoMajor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 专业表
 * @TableName info_major
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InfoMajorSAVO extends InfoMajor implements Serializable {
    private String departmentName;
}