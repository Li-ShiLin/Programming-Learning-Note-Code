package com.lsl.code.bean;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/*ֻ���������е�������Ż�ӵ��spring boot�ṩ��ǿ����*/
@Data
@ConfigurationProperties(prefix = "mycar")
public class MyCar {
    private String brand;
    private Integer price;
}
