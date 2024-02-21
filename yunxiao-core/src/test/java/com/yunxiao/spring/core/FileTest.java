package com.yunxiao.spring.core;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.yunxiao.spring.core.util.ZipUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

/**
 * @author LuoYunXiao
 * @since 2023/9/16 13:54
 */

public class FileTest {

    private final Path file = Path.of("D:/Media/Captures/DiRT Rally 2.0 2023-07-29 13-24-10.mp4");

    @Test
    void zip() {
        Path path = Path.of("C://Users/lenovo/Desktop/项目文件夹/户用光伏项目/dataql");
        ZipUtils.zip(path, Path.of("C://Users/lenovo/Desktop/" + path.getFileName() + ".zip"));
    }

    @Test
    void digest() {
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String string = digester.digestHex(new File(file.toUri()));
        System.out.println(string);
    }

}
