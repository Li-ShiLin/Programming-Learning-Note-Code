package com.example.wechat;

import com.example.spi.PaymentService;

public class WechatPayService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.printf("[WeChat Pay] 付款完成: ¥%.2f%n", amount);
    }

    @Override
    public String getProviderName() {
        return "WeChat Pay";
    }
}