//package com.future.csamsserver.controller.file;
//
///**
// * @description
// * @dateTime 2025:03:18 16:22
// * @user Jenming
// */
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@RestController
//public class FileDownloadControllerWithEncoding {
//
//    @Value("${file.upload.path}")
//    private String uploadPath;
////    private final String uploadPath = "your-upload-path"; // 替换为你的上传路径
//
//    @GetMapping("/file/{fileName:.+}")
//    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
//        Path filePath = Paths.get(System.getProperty("user.dir"), uploadPath, fileName).normalize();
//
//        File file = filePath.toFile();
//        if (!file.exists()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // 探测文件的MIME类型
//        String contentType = Files.probeContentType(filePath);
//        if (contentType == null) {
//            // 如果无法探测到MIME类型，默认使用application/octet-stream
//            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(contentType));
//        headers.setContentDispositionFormData("attachment", fileName);
//
//        try (InputStream inputStream = new FileInputStream(file)) {
//            if (contentType.equals(MediaType.TEXT_PLAIN_VALUE) || contentType.equals(MediaType.TEXT_HTML_VALUE)) {
//                // 对于文本文件，确保使用UTF-8编码
//                Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//                StringWriter writer = new StringWriter();
//                char[] buffer = new char[1024];
//                int n;
//                while ((n = reader.read(buffer)) != -1) {
//                    writer.write(buffer, 0, n);
//                }
//                InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(writer.toString().getBytes(StandardCharsets.UTF_8)));
//                return ResponseEntity.ok()
//                        .headers(headers)
//                        .contentType(MediaType.parseMediaType(contentType))
//                        .body(resource);
//            } else {
//                // 对于其他文件类型，直接返回字节流
//                InputStreamResource resource = new InputStreamResource(inputStream);
//                return ResponseEntity.ok()
//                        .headers(headers)
//                        .contentLength(file.length())
//                        .body(resource);
//            }
//        }
//    }
//}
