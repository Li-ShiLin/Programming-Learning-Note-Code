package com.atguigu.gulimall.member;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallMemberApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void contextMD5() {

        //e10adc3949ba59abbe56e057f20f883e
        //抗修改性:  彩虹表。123456->xxxx   234567->dddd
//        String s = DigestUtils.md5Hex("123456");

        //MD5不能直接进行密码的加密存储;
        //"123456"+System.currentTimeMillis();

        //盐值加密；随机值 加盐：$1$+8位字符
        //$1$Q4jfb2Xz$jbIxdBvSdYXUQRNjyITL11
        //$1$qqqqqqqq$AZofg3QwurbxV3KEOzwuI1
        //$1$qqqqqqqq$AZofg3QwurbxV3KEOzwuI1 123456
        //验证： 123456进行盐值（去数据库查）加密
//        String s1 = Md5Crypt.md5Crypt("123456".getBytes(),"$1$qqqqqqqq");
//        System.out.println(s1);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //$2a$10$4IP4F/2iFO2gbSvQKyJzGuI3RhU5Qdtr519KsyoXGAy.b7WT4P1RW
        //$2a$10$iv6H6nqQ/NWOMkzgZSJdPeMOBGbn0ayhZ9WAewOk0ssWScSHOgsAW
        String encode = passwordEncoder.encode("123456");

        boolean matches = passwordEncoder.matches("123456", "$2a$10$4IP4F/2iFO2gbSvQKyJzGuI3RhU5Qdtr519KsyoXGAy.b7WT4P1RW");


        System.out.println(encode + "=>" + matches);
    }

    @Test
    public void textMD5() {
        //抗修改性:  彩虹表。123456->xxxx   234567->dddd
        String s = DigestUtils.md5Hex("123456");
        System.out.println(s); // e10adc3949ba59abbe56e057f20f883e
        //MD5不能直接进行密码的加密存储;
    }

    @Test
    public void textMd5Crypt() {
        //抗修改性:  彩虹表。123456->xxxx   234567->dddd
        String s = DigestUtils.md5Hex("123456");
        System.out.println(s); // e10adc3949ba59abbe56e057f20f883e

        //盐值加密：md5Crypt方法
        // md5Crypt方法参数：md5Crypt(MD5字符串) ，默认盐值是$1$+8位字符
        String s1 = Md5Crypt.md5Crypt("123456".getBytes());
        System.out.println(s1);// $1$K9RVSJWw$nY2IL4O2HDch7x4iFi06U/

        // md5Crypt方法参数：md5Crypt(MD5字符串,盐值)，指定盐值
        String s2 = Md5Crypt.md5Crypt("123456".getBytes(), "$1$qqqqqqqq");
        System.out.println(s2);// $1$qqqqqqqq$AZofg3QwurbxV3KEOzwuI1
    }


    @Test
    public void textBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        // 第一次encode：$2a$10$.URsevohNUxedjP/7NRMkuxiUHwKQU9TIhC7oWni42RiWeYgrtDCO
        // 第二次encode：$2a$10$cYdG1Ov3OWv5gtUq.coPyewyLnta/fas/o55JKbZmITcvSUXVP0gu
        // 每次encode都是不同的


        // passwordEncoder.matches(提交的密码，数据库储存的密文)
        boolean matches1 = passwordEncoder.matches("123456", "$2a$10$.URsevohNUxedjP/7NRMkuxiUHwKQU9TIhC7oWni42RiWeYgrtDCO");
        boolean matches2 = passwordEncoder.matches("123456", "$2a$10$cYdG1Ov3OWv5gtUq.coPyewyLnta/fas/o55JKbZmITcvSUXVP0gu");

        // 多次生成的不同密文都能够匹配上密码
        System.out.println(encode + "=>" + matches1);
        // $2a$10$hzGcivxZggHeVUtEPSmE.uR/oB/g4uWQsj4I6INd8hshj84/JuYri=>true
        System.out.println(encode + "=>" + matches2);
        // $2a$10$hzGcivxZggHeVUtEPSmE.uR/oB/g4uWQsj4I6INd8hshj84/JuYri=>true

        // 原因：根据密码生成密文就已经在密文中融合了盐值，在验证时可以从密文中解析出盐值，对密码进行校验
    }


}
