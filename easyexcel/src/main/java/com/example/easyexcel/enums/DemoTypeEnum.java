package com.example.easyexcel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenghao
 * @since 2024/6/4 19:51
 */
@Getter
@AllArgsConstructor
public enum DemoTypeEnum {

    SINGLE(1),
    COVER(2);

    private int code;
}
