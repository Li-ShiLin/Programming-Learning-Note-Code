package com.atguigu.gulimail.product;


//import com.aliyun.oss.OSSClient;
import com.atguigu.gulimail.product.entity.BrandEntity;
import com.atguigu.gulimail.product.service.BrandService;
import com.atguigu.gulimail.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataBaseCrudTest {

    @Resource
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void contextLoads() {

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("华为");

        brandEntity.setName("华为");
        brandService.save(brandEntity);
        System.out.println("保存成功....");
//        brandService.updateById(brandEntity);


//        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
//        list.forEach((item) -> {
//            System.out.println(item);
//        });

    }


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




//    // 使用`SpringCloud Alibaba-OSS`实现上传功能
//    @Autowired
//    private OSSClient ossClient;
//
//    @Test
//    public void testUpload() throws FileNotFoundException {
//
//        String bucketName = "gulimall-bucket";
//        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        String objectName = "pic.jpg";
//        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
//        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//        String filePath = "D:\\gitee\\pic.jpg";
//        // 创建OSSClient实例。
//
//        //上传文件流。
//        InputStream inputStream = new FileInputStream(filePath);
//        ossClient.putObject(bucketName, objectName, inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//        System.out.println("上传成功.");
//    }


    @Test
    public void testFindPath(){
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("catelogPath:{}", Arrays.asList(catelogPath));

        // 日志打印 ： catelogPath:[2, 34, 225]
    }

}
