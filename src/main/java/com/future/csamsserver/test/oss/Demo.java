//package com.future.csamsserver.test.oss;
//
///**
// * @description
// * @dateTime 2025:03:15 23:11
// * @user Jenming
// */
//import com.aliyun.oss.*;
//import com.aliyun.oss.common.auth.*;
//import com.aliyun.oss.common.comm.SignVersion;
//import com.aliyun.oss.model.PutObjectRequest;
//import com.aliyun.oss.model.PutObjectResult;
//import java.io.ByteArrayInputStream;
//
//public class Demo {
//
//    public static void main(String[] args) throws Exception {
//        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//        String endpoint = "https://oss-cn-guangzhou.aliyuncs.com";
//        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//        // 填写Bucket名称，例如examplebucket。
//        String bucketName = "csams";
//        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        String objectName = "a.txt";
//        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
//        String region = "cn-guangzhou";
//
//        // 创建OSSClient实例。
////        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
////        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
////        OSS ossClient = OSSClientBuilder.create()
////                .endpoint(endpoint)
////                .credentialsProvider(credentialsProvider)
////                .clientConfiguration(clientBuilderConfiguration)
////                .region(region)
////                .build();
//        // 替换环境变量方式为直接注入凭证
//        String accessKeyId = "LTAI5tAL8KoqoFHfJxZDEKe2";  // 运行时动态获取
//        String accessKeySecret = "w3RoGNQCWqxbQXa86bS9tOYhb6laLR";  // 运行时动态获取
//
//        OSS ossClient = OSSClientBuilder.create()
//                .endpoint(endpoint)
//                .credentialsProvider(new DefaultCredentialProvider(accessKeyId, accessKeySecret))
////                .clientConfiguration(clientBuilderConfiguration)
////                .region(region)
//                .build();
//
//
//        try {
//            // 填写字符串。
//            String content = "Hello OSS，你好世界";
//
//            // 创建PutObjectRequest对象。
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
//
//            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
//            // ObjectMetadata metadata = new ObjectMetadata();
//            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//            // metadata.setObjectAcl(CannedAccessControlList.Private);
//            // putObjectRequest.setMetadata(metadata);
//
//            // 上传字符串。
//            PutObjectResult result = ossClient.putObject(putObjectRequest);
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } catch (ClientException ce) {
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//    }
//}