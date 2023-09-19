package com.yunxiao.spring.core;

import com.yunxiao.spring.core.crypto.RSACrypto;
import com.yunxiao.spring.core.io.ZipUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Objects;

/**
 * @author LuoYunXiao
 * @since 2023/9/16 13:54
 */

public class JavaTest {

    @Test
    void zip() {
        Path path = Path.of("C://Users/lenovo/Desktop/项目文件夹/户用光伏项目/dataql");
        ZipUtils.zip(path, Path.of("C://Users/lenovo/Desktop/" + path.getFileName() + ".zip"));
    }

    @Test
    void crypto() {
        RSACrypto rsaCrypto = RSACrypto.newRandomRSACrypto();
        String encrypted1 = rsaCrypto.encrypt("吼吼吼吼吼吼吼吼吼吼吼吼吼吼吼吼").toBase64String();
        String encrypted2 = rsaCrypto.encrypt("吼吼吼吼吼吼吼吼吼吼吼吼吼吼吼吼").toBase64String();
        System.out.println(encrypted2);
        System.out.println(encrypted1);
        System.out.println("----------------------------------");
        System.out.println(encrypted2.length());
        System.out.println(encrypted1.length());
        String decrypted1 = rsaCrypto.decrypt(encrypted1).toString();
        String decrypted2 = rsaCrypto.decrypt(encrypted2).toString();
        System.out.println("----------------------------------");
        System.out.println(decrypted1);
        System.out.println(decrypted2);
        System.out.println("----------------------------------");
        System.out.println(Objects.equals(decrypted1, decrypted2));
    }
}
