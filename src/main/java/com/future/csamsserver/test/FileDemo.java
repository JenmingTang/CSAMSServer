package com.future.csamsserver.test;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * @description
 * @dateTime 2025:03:16 00:15
 * @user Jenming
 */
public class FileDemo {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));
//        System.getProperty("user.dir") = D:\CSAMS\CSAMSServer
//        Files.
        final String file1 = "a.txt";
        final String substring = file1.substring(file1.lastIndexOf(".") + 1);
        System.out.println("substring = " + substring);
        Arrays.stream(File.listRoots()).toList().forEach(System.out::println);
//        C:\
//        D:\
        final File file = new File("oss/a.txt");
        System.out.println("file.isFile() = " + file.isFile());
        System.out.println("file.canExecute() = " + file.canExecute());
        System.out.println("file.canRead() = " + file.canRead());
        System.out.println("file.canWrite() = " + file.canWrite());
        /*
        * canonical典型
        * */
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath());
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file.getName() = " + file.getName());
        file.getName().split("\\.");
    }
}
