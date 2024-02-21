package com.yunxiao.spring.core;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.jupiter.api.Test;

/**
 * @author LuoYunXiao
 * @since 2023/9/27 17:25
 */
public class JavaTest {

    @Test
    void test1() {
        RSA rsa = SecureUtil.rsa();

    }
}
