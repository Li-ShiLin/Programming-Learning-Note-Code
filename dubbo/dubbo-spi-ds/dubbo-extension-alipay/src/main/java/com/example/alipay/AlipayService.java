package com.example.alipay;

import com.example.api.PaymentService;
import com.example.common.LogWrapper;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;

@Activate(group = {"online", "mobile"}, order = 1) // 自动激活扩展
public class AlipayService implements PaymentService {

    private final LogWrapper logger;

    // 无参构造函数，供Dubbo SPI使用
    public AlipayService() {
        this.logger = new LogWrapper();
    }

    // 带参构造函数，用于手动创建实例
    public AlipayService(LogWrapper logger) {
        this.logger = logger;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("[Alipay] 支付成功: ¥%.2f%n", amount);
        logger.log("Alipay支付日志: " + amount);
    }

    @Override
    public String getProviderName() {
        return "alipay";
    }

    @Override
    public void adaptivePay(double amount, URL url) {
        System.out.printf("[Alipay Adaptive] 支付成功: ¥%.2f (场景: %s)%n",
                amount, url.getParameter("scene", "default"));
    }

    @Override
    public boolean supportRefund() {
        return true;
    }

    @Override
    public void refund(double amount) {
        System.out.printf("[Alipay] 退款成功: ¥%.2f%n", amount);
    }
}