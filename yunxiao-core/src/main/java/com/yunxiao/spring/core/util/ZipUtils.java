package com.yunxiao.spring.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author LuoYunXiao
 * @since 2023/9/16 13:22
 */
public class ZipUtils {

    public static void zip(Path source, Path target) {
        if (!Files.isDirectory(source)) {
            throw new IllegalArgumentException("source must be a directory");
        }
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(target)))) {
            Files.walkFileTree(source, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    ZipEntry zipEntry = new ZipEntry(source.relativize(file).toString());
                    try {
                        zos.putNextEntry(zipEntry);
                        Files.copy(file, zos);
                        zos.closeEntry();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path zipWithTmp(Path source, boolean isDeleteOnExit) {
        try {
            File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".zip");
            if (isDeleteOnExit) {
                tempFile.deleteOnExit();
            }
            Path tempFilePath = tempFile.toPath();
            ZipUtils.zip(source, tempFilePath);
            return tempFilePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
