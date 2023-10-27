package com.yunxiao.spring.reactive.model.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LuoYunXiao
 * @since 2023/10/27 22:00
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private int code;

    private String msg;

    private String tips;

    public BusinessException(CodeAble codeAble, String tips) {
        super(codeAble.getMsg());
        this.code = codeAble.getCode();
        this.msg = codeAble.getMsg();
        this.tips = tips;
    }

    public BusinessException(CodeAble codeAble) {
        super(codeAble.getMsg());
        this.code = codeAble.getCode();
        this.msg = codeAble.getMsg();
    }
}
