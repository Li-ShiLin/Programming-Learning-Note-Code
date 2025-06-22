package com.example.alipay;

import com.example.spi.PaymentService;

public class AlipayService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.printf("[Alipay] 支付成功: ¥%.2f%n", amount);
    }

    @Override
    public String getProviderName() {
        return "Alipay";
    }
}