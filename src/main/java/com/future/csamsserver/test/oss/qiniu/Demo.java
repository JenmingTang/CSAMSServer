//package com.future.csamsserver.test.oss.qiniu;
//
//import com.google.gson.Gson;
//import com.qiniu.common.QiniuException;
//import com.qiniu.storage.Configuration;
//import com.qiniu.storage.Region;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.storage.model.DefaultPutRet;
//import com.qiniu.util.Auth;
//
///**
// * @description
// * @dateTime 2025:03:17 14:14
// * @user Jenming
// */
//public class Demo {
//
//    final private static String ACCESS_KEY = "gM7QTmo07MAiIMphgtAKy-A028a6KURzBb7gHWm5";
//    final private static String SECRET_KEY = "cdPRjZDT8sewwQ-tv-OMZm_cUv3q4d_ktu_TOmWX";
//    final private static String BUCKET = "250317csams";
//    public static void main(String[] args) {
//        String accessKey = "gM7QTmo07MAiIMphgtAKy-A028a6KURzBb7gHWm5";
//        String secretKey = "cdPRjZDT8sewwQ-tv-OMZm_cUv3q4d_ktu_TOmWX";
//        String bucket = "250317csams";
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket);
//        /*
//        * gM7QTmo07MAiIMphgtAKy-A028a6KURzBb7gHWm5:_Jj6RzfV42GvUIDrG31HNIvXp8o=:eyJzY29wZSI6IjI1MDMxN2NzYW1zIiwiZGVhZGxpbmUiOjE3NDIyMDY2Njl9
//        * */
//        System.err.println(upToken);
//    }
//    public static void fileUpload(){
//        //构造一个带指定 Region 对象的配置类
//        Configuration cfg = new Configuration(Region.region2());
////        Configuration cfg = new Configuration(Region.huanan());
//        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
////...其他参数参考类注释
//
//        UploadManager uploadManager = new UploadManager(cfg);
////...生成上传凭证，然后准备上传
//        String accessKey = Demo.ACCESS_KEY;
//        String secretKey = Demo.SECRET_KEY;
//        String bucket = Demo.BUCKET;
////如果是Windows情况下，格式是 D:\\qiniu\\test.png
//        String localFilePath = "aa.txt";
////默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = null;
//
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket);
//
//        try {
//            Response response = uploadManager.put(localFilePath, key, upToken);
//            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
//        } catch (QiniuException ex) {
//            ex.printStackTrace();
//            if (ex.response != null) {
//                System.err.println(ex.response);
//
//                try {
//                    String body = ex.response.toString();
//                    System.err.println(body);
//                } catch (Exception ignored) {
//                }
//            }
//        }
//
//    }
//}
