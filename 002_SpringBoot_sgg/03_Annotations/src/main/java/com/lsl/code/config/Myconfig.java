package com.lsl.code.config;
import com.lsl.code.bean.MyCar;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@EnableConfigurationProperties(MyCar.class)
//ΪMyCar�������ð�
    //1������Car���ð󶨹���
    //2�������Car�������Զ�ע�ᵽ������
public class Myconfig {
}
