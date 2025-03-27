///*
// * Licensed to the Apache Software Foundation (ASF) under one
// * or more contributor license agreements.  See the NOTICE file
// * distributed with this work for additional information
// * regarding copyright ownership.  The ASF licenses this file
// * to you under the Apache License, Version 2.0 (the
// * "License"); you may not use this file except in compliance
// * with the License.  You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing,
// * software distributed under the License is distributed on an
// * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// * KIND, either express or implied.  See the License for the
// * specific language governing permissions and limitations
// * under the License.
// */
//
//package com.future.csamsserver.test.oss;
//
//import com.aliyun.oss.ClientException;
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import com.aliyun.oss.OSSException;
//import com.aliyun.oss.model.*;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
///**
// * This sample demonstrates how to get started with basic requests to Aliyun OSS
// * using the OSS SDK for Java.
// */
//public class GetStartedSample {
//    public static void main(String[] args) {
//        /*
//        * Java JDK简单网络请求，访问百度
//        * */
//        try {
//            HttpClient httpClient = HttpClient.newBuilder().build();
//            final String string = httpClient.send(HttpRequest.newBuilder()
//                            .uri(URI.create("https://www.baidu.com"))
//                            .GET()
//                            .build(),
//                    HttpResponse.BodyHandlers.ofString()).toString();
//            System.out.println("string = " + string);
////            string = (GET https://www.baidu.com) 200
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}