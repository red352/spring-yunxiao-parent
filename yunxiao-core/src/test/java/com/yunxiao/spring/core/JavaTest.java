package com.yunxiao.spring.core;

import com.yunxiao.spring.core.io.ZipUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

/**
 * @author LuoYunXiao
 * @since 2023/9/16 13:54
 */

public class JavaTest {

    @Test
    void zip() {
        Path path = Path.of("D://testZip");
        Path path1 = ZipUtils.zipWithTmp(path);
        System.out.println(path1);
    }
}
