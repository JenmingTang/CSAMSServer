//package com.future.csamsserver.controller.file;
//
///**
// * @description
// * @dateTime 2025:03:18 15:56
// * @user Jenming
// */
//import cn.hutool.core.io.IoUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
///*
//* 已改为静态资源访问
//* */
//@RestController
//public class FileDownloadController {
//
//
//    @Value("${file.upload.path}")
//    private String uploadPath;
//    @GetMapping("/file/{fileName:.+}")
//    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
//        Path filePath = Paths.get(System.getProperty("user.dir"), uploadPath, fileName).normalize();
//
//        File file = filePath.toFile();
//        if (!file.exists()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        byte[] fileContent = IoUtil.readBytes(new FileInputStream(file));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", fileName);
//
//        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
//    }
//}