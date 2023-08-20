package com.atguigu.gulimall;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.atguigu.gulimall.component.SmsComponent;
import com.atguigu.gulimall.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallThirdPartyApplicationTests {

//    // 使用原生的OSS SDK上传文件 ,需要引入如下依赖：
//    /**
//     *         <dependency>
//     *             <groupId>com.aliyun.oss</groupId>
//     *             <artifactId>aliyun-sdk-oss</artifactId>
//     *             <version>3.5.0</version>
//     *         </dependency>
//     */
//
//    @Test
//    public void OSSUpload() throws FileNotFoundException {
//        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
//        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "xxx";
//        String accessKeySecret = "xxx";
//
//        String bucketName = "gulimall-bucket";
//        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        String objectName = "mypic.jpg";
//        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
//        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//        String filePath = "D:\\gitee\\mypic.jpg";
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        //上传文件流。
//        InputStream inputStream = new FileInputStream(filePath);
//        ossClient.putObject(bucketName, objectName, inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//        System.out.println("上传成功.");
//    }




    // 使用`SpringCloud Alibaba-OSS`实现上传功能
    @Resource
    private OSS ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {

        String bucketName = "gulimall-bucket";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "pic.jpg";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        String filePath = "D:\\gitee\\pic.jpg";
        // 创建OSSClient实例。

        //上传文件流。
        InputStream inputStream = new FileInputStream(filePath);
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传成功.");
    }



    @Test
    public void sendSms(){
        String host = "https://smsmsgs.market.alicloudapi.com";
        String path = "/sms/";
        String method = "GET";
        String appcode = "xxx";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("code", "6789");
        querys.put("phone", "19801333953");
        querys.put("skin", "1");
        querys.put("sign", "175622");
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

        try {
            // 注意：
            // httpUtils 请从此处下载： https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Autowired
    SmsComponent smsComponent;

    @Test
    public void testSendCode(){
        smsComponent.sendSmsCode("17512080612","78495");
    }


}
