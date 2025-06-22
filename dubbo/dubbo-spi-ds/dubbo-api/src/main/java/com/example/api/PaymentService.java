package com.example.api;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI("alipay") // 指定默认扩展实现为alipay
public interface PaymentService {

    // 普通支付方法
    void pay(double amount);

    // 获取支付提供商名称
    String getProviderName();

    // 自适应方法：根据URL参数动态选择实现
    @Adaptive
    void adaptivePay(double amount, URL url);

    // 支持退款功能（用于演示自动激活扩展）
    boolean supportRefund();

    // 退款方法
    void refund(double amount);
}