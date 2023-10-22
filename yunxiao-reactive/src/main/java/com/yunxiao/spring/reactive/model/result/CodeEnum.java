package com.yunxiao.spring.reactive.model.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 22:32
 */
@Getter
@AllArgsConstructor
public enum CodeEnum implements CodeAble {

    SUCCESS(0, "success"),
    FAIL(1, "未知系统错误");

    private final int code;
    private final String msg;
}
